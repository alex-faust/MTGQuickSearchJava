package com.magic.mtgquicksearchjava.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.magic.mtgquicksearchjava.R;

public class MainActivity extends AppCompatActivity
{
    EditText enterCardName;
    Button searchBtn;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterCardName = findViewById(R.id.enterCardName);
        //enterCardName.getText();
        searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(this::getCard);




    }

    public void getCard(View v)
    {
        i = new Intent(MainActivity.this, CardActivity.class);
        i.putExtra("STRING_I_NEED", enterCardName.getText().toString());
        startActivity(i);
    }


}