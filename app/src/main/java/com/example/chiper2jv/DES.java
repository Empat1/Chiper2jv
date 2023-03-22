package com.example.chiper2jv;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {
    public String encrypt(String plainTextPassword){
        String encrypted = "";
        try{
            DESKeySpec keySpec = new DESKeySpec("qwertykey".getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES/ECB/ZeroBytePadding", "BC");
            SecretKey key = keyFactory.generateSecret(keySpec);
            byte[] cleartext = plainTextPassword.getBytes();

            Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread safe
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = Base64.encodeToString(cipher.doFinal(cleartext),Base64.DEFAULT);

        }catch (Exception e){

        }
        return encrypted;
    }
}
