package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private Button getBtn1, getBtn2;
    private TextView textView;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getBtn1 = findViewById(R.id.cmButton);
        getBtn2 = findViewById(R.id.mButton);
        textView = findViewById(R.id.textBox);

        Toast.makeText(MainActivity.this,"Firebase connection success",Toast.LENGTH_LONG).show();
        getBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getBtn1.setBackground(getDrawable(R.drawable.bg_button_on));
                getBtn1.setTextColor(getColor(R.color.white));

                getBtn2.setBackground(getDrawable(R.drawable.bg_button));
                getBtn2.setTextColor(getColor(R.color.red));

                readDb(1);return;
            }
        });
        getBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getBtn2.setBackground(getDrawable(R.drawable.bg_button_on));
                getBtn2.setTextColor(getColor(R.color.white));

                getBtn1.setBackground(getDrawable(R.drawable.bg_button));
                getBtn1.setTextColor(getColor(R.color.red));

                readDb(100);return;
            }
        });
    }
    public void readDb (final int divided){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String value=dataSnapshot.child("HC-SR04").child("jarak").getValue().toString();
                final float distance = Float.parseFloat(value);
                String Value = String.valueOf(distance/divided);
                textView.setText(Value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}



