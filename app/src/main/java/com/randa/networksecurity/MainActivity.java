package com.randa.networksecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.randa.networksecurity.databinding.ActivityMainBinding;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private byte[] encryptData,signature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.decryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* try {
                    binding.showPassword.setText(dncryptDataCipher(encryptData,binding.passwordText.getText().toString()));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Wrong Decrypte", Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }*/
            }
        });

        binding.encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  try {
                    binding.showEmail.setText(encryptDataCipher( binding.emailText.getText().toString() ,  binding.passwordText.getText().toString()));
                } catch (Exception e) {
                    e.getMessage();
                }*/
                try {
                    binding.showEmail.setText(encrptSigntuare( binding.emailText.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private String encryptDataCipher(String data, String password) throws Exception {

        SecretKeySpec key=generateKey(password);
        Cipher c= Cipher.getInstance("AES");

            c.init(Cipher.ENCRYPT_MODE,key);
            encryptData=c.doFinal(data.getBytes());
           // String value= Base64.encodeToString(encryptData,Base64.DEFAULT);
              String value= new String(encryptData,"UTF8");
           return value;



    }

    private String dncryptDataCipher(byte[] data, String password) throws Exception {

        SecretKeySpec key=generateKey(password);
        Cipher c= Cipher.getInstance("AES");

        c.init(Cipher.DECRYPT_MODE,key);
        //byte[] devalue= Base64.decode(data,Base64.DEFAULT);

        byte [] dncryptData=c.doFinal(data);
        String newvalue= new String(dncryptData);
        return newvalue;



    }


    private SecretKeySpec generateKey(String password) throws Exception {

            MessageDigest digest=MessageDigest.getInstance("SHA-256");
            byte [] bytes=password.getBytes(StandardCharsets.UTF_8);
            digest.update(bytes,0,bytes.length);
            byte [] key= digest.digest();
            SecretKeySpec secretKeySpec=new SecretKeySpec(key, "AES");
            return secretKeySpec;

    }


    private String encrptSigntuare(String data) throws Exception {
        //Creating KeyPair generator object
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

        //Initializing the key pair generator
        keyPairGen.initialize(2048);

        //Generate the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();

        //Getting the private key from the key pair
        PrivateKey privKey = pair.getPrivate();

        //Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withDSA");

        //Initialize the signature
        sign.initSign(privKey);


        //Adding data to the signature
        sign.update(data.getBytes());

        //Calculating the signature
       signature = sign.sign();
        return new String(signature, "UTF8");
        }


}