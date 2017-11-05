package com.example.leeon.sweettoothv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.leeon.sweettoothv1.R.id.textView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        counter(101);
    }

    protected void counter(int a){
        String stringy = " ahsdas";
        TextView help = (TextView) findViewById(textView);
        help.setText(stringy);

    }




    public void oldPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
