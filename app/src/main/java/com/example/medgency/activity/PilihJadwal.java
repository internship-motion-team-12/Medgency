package com.example.medgency.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.medgency.FungsiFungsi.DateFunction;
import com.example.medgency.FungsiFungsi.StringFunction;
import com.example.medgency.R;
import com.example.medgency.adapter.WaktuBerobatAdapter;
import com.example.medgency.model.Ticket;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PilihJadwal extends AppCompatActivity {
    private static final String STATE_RESULT_JENIS_LAYANAN = "STATE_RESULT_JENIS_LAYANAN";
    private static final String STATE_RESULT_PENYEDIA_LAYANAN = "STATE_RESULT_PENYEDIA_LAYANAN";
    String email_key = "";
    String email_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_jadwal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolbarPilihJadwal);
        final Button BtnToTiketReservasi = findViewById(R.id.BtnToTiketReservasi);
        TextView toolbar_text = findViewById(R.id.toolbar_text);
        toolbar_text.setText("Pilih Jadwal");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent before = getIntent();

        final String[] jns_layanan = new String[1];
        final String[] penyedia_layanan = new String[1];

        getUsernameLocal();

        if (before.getExtras() != null){
            jns_layanan[0] = before.getStringExtra(getString(R.string.JenisLayanan));
            setSharedPreferenceLocal(getString(R.string.JenisLayanan),jns_layanan[0]);
            if (jns_layanan[0].equals(getString(R.string.RumahSakit))){
                penyedia_layanan[0] = before.getStringExtra(getString(R.string.NamaRS));
            }
            else{
                penyedia_layanan[0] = before.getStringExtra(getString(R.string.NamaDokter));
            }
            setSharedPreferenceLocal(getString(R.string.PenyediaLayanan),penyedia_layanan[0]);
        }
        else{
            jns_layanan[0] = getSharedPreferenceLocal(getString(R.string.JenisLayanan));
            penyedia_layanan[0] = getSharedPreferenceLocal(getString(R.string.PenyediaLayanan));
        }

        if(jns_layanan[0].equals(getString(R.string.Dokter))){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(getString(R.string.Tampungan)).child("users").child(email_key_new).child(getString(R.string.Layanan));
            reference.setValue("Pemeriksaan fisik");
        }

        final int tanggal;
        final String hari;
        final String bulan;


        final String[] tanggalTemp = new String[1];
        final String finalPenyedia_layanan = penyedia_layanan[0];
        //Toast.makeText(getApplicationContext(),penyedia_layanan,Toast.LENGTH_LONG).show();

        final Calendar newCalendar = Calendar.getInstance();
        RelativeLayout RLTanggalBerobat = findViewById(R.id.RLTanggalBerobat);
        RLTanggalBerobat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PilihJadwal.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

                        tanggalTemp[0] = dateFormatter.format(newDate.getTime());
                        TextView TVtanggal = findViewById(R.id.tanggal);

                        getUsernameLocal();

                        DateFunction dateFunction = new DateFunction();
                        int tanggal = dateFunction.getTanggalFromDate(tanggalTemp[0]),tahun = dateFunction.getTahunFromDate(tanggalTemp[0]);
                        String bulan = dateFunction.getBulanFromDate(tanggalTemp[0]);

                        String hari = dateFunction.ConvertDateToDay(tanggal,bulan,tahun);
                        TVtanggal.setText(tanggalTemp[0]);
                        final String teks = hari+" "+String.valueOf(tanggal)+bulan+String.valueOf(tahun);

                        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Tampungan").child("users").child(email_key_new);
                        reference1.child("Hari").setValue(hari);
                        reference1.child("JenisLayanan").setValue(jns_layanan[0]);
                        reference1.child(getString(R.string.Tanggal)).setValue(tanggalTemp[0]);
                        if (jns_layanan[0].equals(before.getStringExtra(getString(R.string.RumahSakit)))){
                            reference1.child(getString(R.string.NamaRS)).setValue(penyedia_layanan[0]);
                        }
                        else{
                            reference1.child(getString(R.string.NamaDokter)).setValue(penyedia_layanan[0]);
                        }

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("JadwalDokterPraktek").child(hari).child(StringFunction.DeleteDotFromString(penyedia_layanan[0]));
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                ArrayList<String> dataList = StringFunction.UraiWaktuBerobat(dataSnapshot.getValue().toString());
                                RecyclerView RVPilihJadwal = findViewById(R.id.RVPilihJadwal);
                                RVPilihJadwal.setLayoutManager(new GridLayoutManager(PilihJadwal.this, 3));
                                WaktuBerobatAdapter waktuBerobatAdapter = new WaktuBerobatAdapter(getApplicationContext(),dataList);

                                RVPilihJadwal.setAdapter(waktuBerobatAdapter);
                                String string = String.valueOf(dataList.size());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        BtnToTiketReservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnToTiketReservasi.setEnabled(false);
                BtnToTiketReservasi.setText("Membuat Tiket...");
                getUsernameLocal();
                final Intent goToTicketReservation = new Intent(getApplicationContext(),TiketReservasi.class);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(getApplicationContext().getString(R.string.Tampungan)).child("users").child(email_key_new);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String JenisLayanan = dataSnapshot.child(getString(R.string.JenisLayanan)).getValue().toString();
                        goToTicketReservation.putExtra(getString(R.string.JenisLayanan),JenisLayanan);
                        if (JenisLayanan.equals(getString(R.string.RumahSakit))){
                            goToTicketReservation.putExtra(getString(R.string.PenyediaLayanan),dataSnapshot.child(getString(R.string.NamaRS)).getValue().toString());
                            goToTicketReservation.putExtra(getString(R.string.WaktuBerobat),dataSnapshot.child(getString(R.string.WaktuBerobat)).getValue().toString());
                            goToTicketReservation.putExtra(getString(R.string.Tanggal),dataSnapshot.child(getString(R.string.Tanggal)).getValue().toString());
                            goToTicketReservation.putExtra(getString(R.string.Layanan),dataSnapshot.child(getString(R.string.Layanan)).getValue().toString());
                        }
                        else{
                            goToTicketReservation.putExtra(getString(R.string.PenyediaLayanan),dataSnapshot.child(getString(R.string.NamaDokter)).getValue().toString());
                            final String NamaDokter = dataSnapshot.child(getString(R.string.NamaDokter)).getValue().toString();
                            goToTicketReservation.putExtra(getString(R.string.WaktuBerobat),dataSnapshot.child(getString(R.string.WaktuBerobat)).getValue().toString());
                            final String WaktuBerobat = dataSnapshot.child(getString(R.string.WaktuBerobat)).getValue().toString();
                            goToTicketReservation.putExtra(getString(R.string.Tanggal),dataSnapshot.child(getString(R.string.Tanggal)).getValue().toString());
                            final String Tanggal = dataSnapshot.child(getString(R.string.Tanggal)).getValue().toString();
                            goToTicketReservation.putExtra(getString(R.string.Layanan),dataSnapshot.child(getString(R.string.Layanan)).getValue().toString());
                            final String Layanan = dataSnapshot.child(getString(R.string.Layanan)).getValue().toString();
                            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child(getString(R.string.Dokter)).child("TarifDokterPraktek").child(StringFunction.DeleteDotFromString(NamaDokter)).child(Layanan);
                            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String harga = dataSnapshot.getValue().toString();
                                    Date c = Calendar.getInstance().getTime();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                                    String currentDateandTime = sdf.format(new Date());
                                    String IdTicket = email_key_new+currentDateandTime;
                                    IdTicket = StringFunction.DeleteDotFromString(IdTicket);
                                    DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Tiket").child(email_key_new).child(IdTicket);
                                    Ticket ticket = new Ticket(Layanan,NamaDokter,harga,Tanggal,WaktuBerobat,IdTicket);
                                    reference2.setValue(ticket);
                                    goToTicketReservation.putExtra(getString(R.string.IdTicket),IdTicket);
                                    startActivity(goToTicketReservation);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Tidak dapat terhubung ke server, periksa koneksi internet anda",Toast.LENGTH_LONG).show();
                    }
                });
                //goToTicketReservation.putExtra("WaktuPelayanan",);
            }
        });
    }

    private void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences("id", MODE_PRIVATE);
        email_key_new = sharedPreferences.getString(email_key,"");
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    private void setSharedPreferenceLocal(String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.Tampungan),MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    private String getSharedPreferenceLocal(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.Tampungan),MODE_PRIVATE);
        return sharedPreferences.getString(key,"Belum terisi");
    }
}
