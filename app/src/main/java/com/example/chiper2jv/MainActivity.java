package com.example.chiper2jv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etMyMessage, etMyKey, etMyAnswer;
    Button btnMyCode, btnMyDecode, btnGenerate , btnLoad, btnSave;
    CustomDES customDES;

    EditText etA, etB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMyMessage = findViewById(R.id.etMyMessage);
        etMyKey = findViewById(R.id.etMyKey);
        etMyAnswer = findViewById(R.id.tvMyAnswer);
        btnMyCode = findViewById(R.id.btnMyCode);
        btnMyDecode = findViewById(R.id.btnMyDecode);
        btnGenerate = findViewById(R.id.btnG);

        btnLoad = findViewById(R.id.load);
        btnSave = findViewById(R.id.save);

        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);

        btnMyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = etMyMessage.getText().toString();
                String key = etMyKey.getText().toString();

                customDES = new CustomDES(message , key);

                etMyAnswer.setText(customDES.code(message));
            }
        });

        btnMyDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = etMyAnswer.getText().toString();
                String key = etMyKey.getText().toString();
                etMyMessage.setText(customDES.decode(answer , key));
            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = 1;
                int b = 1;
                try {
                    a = Integer.parseInt(etA.getText().toString());
                    b = Integer.parseInt(etB.getText().toString());
                }catch (Exception e){

                }

                int start = 'a';
                etMyKey.setText(lineGenerationWight(start , a, b , etMyMessage.getText().length()));
            }
        });

        String fileName = "file";
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                sharedPreferences.edit().putString(fileName , etMyKey.getText().toString()).apply();
            }
        });


        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                String l = sharedPreferences.getString(fileName,"");
                etMyKey.setText(l);
            }
        });
    }

    int alphabetSile = 33;
    char startChar = 'а';

    private String lineGenerationWight(int x0, int a, int b, int messageSize){

        StringBuilder out = new StringBuilder((char) x0 );
        int xn = x0;

        for(int i =1 ; i <= messageSize;i++){
            int x = (a*xn*b) % alphabetSile;
            xn = x;
            out.append((char) (x+ startChar));
        }

        return out.toString();
    }

}