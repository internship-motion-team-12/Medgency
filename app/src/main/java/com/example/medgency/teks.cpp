//
// Created by naufa on 30/06/2019.
//

#include "teks.h"

class teks{
    public string email;
    public string user_id;

    public teks(string a, string b){
        email = a;
        user_id = b;
    }

    private String EmailToUsername(ring email){
        int nCharacter = email.length();
        int i = nCharacter - 1;
        while (i > 0){
            if (email[i] == "."){
                break;
            }
            i--;
        }

        string awal="",akhir="";
        int j = 0;
        while (j < i){
            awal += email[j]
            j++;
        }

        while (j < email.length){
            akhir += email[j]
            j++;
        }

        return awal + "-" + akhir;
    }
}