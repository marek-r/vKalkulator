package com.example.vkalkulator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    EditText editText;
    int count=0;
    SpeechRecognizer speechRecognizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageButton=findViewById(R.id.button);
        editText=findViewById(R.id.edittext);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);
        }
        speechRecognizer=SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Stan kliknięcia
                if (count==0){
                    //Ustawia ikonę przycisku
                    imageButton.setImageDrawable(getDrawable(R.drawable.ic_baseline_mic_24));
                    //Rozpoczyna nasłuchiwanie
                    speechRecognizer.startListening(speechRecognizerIntent);
                    count=1;
                }else
                {
                    //Ustawia ikonę przycisku
                    imageButton.setImageDrawable(getDrawable(R.drawable.ic_baseline_mic_off_24));
                    speechRecognizer.stopListening();
                    count=0;
                }
            }
        });

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            // Rezultat rozpoznania mowy
            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);

                // Mikrofon włączony
                // na razie możliwa tylko jedna operacja np (1+1), a nie (1+1*3,itd)
                // przetorzony tekst rozbija po spacjach np 2 + 2
                System.out.println("Przetorzony tekst rozbija po spacjach");
                String[] dane = data.get(0).split("\\s+");
                // Powstaje trzy elementowa tablica [pierwsza cyfra, znak operacji, druga cyfra]
                System.out.println(dane[0]); // pierwsza cyfra
                System.out.println(dane[1]); // znak operacji
                System.out.println(dane[2]); // druga cyfra
                // Przypisuje cyfry do zmiennych
                System.out.println(dane[0]);
                System.out.println(dane[2]);
                // Przypisuje znak do zmiennej
                System.out.println(dane[1]);
                // Parsuje string do int
                double A = Double.valueOf(dane[0]);
                double B = Double.valueOf(dane[2]);
                // Na podstawie rozpoznanego znaku wybiera odpowiednią operację


                if (dane[1].equals("+"))
                {
                    double wynik = A+B;
                    String tekst = data.get(0)+"="+wynik;
                    editText.setText(tekst);
                }
                else if(dane[1].equals("-"))
                {
                    double wynik = A-B;
                    String tekst = data.get(0)+"="+wynik;
                    editText.setText(tekst);
                }else if(dane[1].equals("/"))
                {
                    double wynik = A/B;
                    String tekst = data.get(0)+"="+wynik;
                    editText.setText(tekst);
                }else if(dane[1].equals("*"))
                {
                    double wynik = A*B;
                    String tekst = data.get(0)+"="+wynik;
                    editText.setText(tekst);
                }


                // Ustawia tekst inputa (z indeksu 0 tablicy)
//                for (String str : data)
//                {
//                    System.out.print(str);
//                }

//                for (String str : dane)
//                {
//                    System.out.println(str);
//                }

                // Chyba w tym miejscu powinno nastąpić:
                // - rozbicie po stringach cyfr/liczb/znaku
                // - dodanie logiki kalkulatora, czyli dla operacji 1 + 5
                // - pierwszy znak do zmiennej A= 1
                // - drugi znak do zmiennej B= +
                // - trzeci znak do zmiennej C= 5
                // Na podstawie znaku w zmiennej B, będzie wykonana operacja (dodawanie/odejmowanie/mnożenie/dzielenie)

                // Moja proponowana logika to:

                // Jeśli klikniesz w mikrofon to włącza się rozpoznawanie mowy i:
                    // Jeśli przycisk mikrofonu aktywny to nieaktywne są przyciski kalkulatora

                // Jeśli nie klikniesz w mikrofon to wprowadzasz cyferki z klawiatury
                    // Jeśli przycisk mikrofonu nieaktywny to aktywne są przyciski kalkulatora
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Pozwolenie udzielone", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(this, "Odmowa pozwolenia", Toast.LENGTH_SHORT);
            }
        }
    }
}