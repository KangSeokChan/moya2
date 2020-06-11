package com.example.moya2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Navheadermain extends Activity {
    Button loginmove;
    TextView user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);
        user_id = (TextView) findViewById(R.id.text_id);
        loginmove = findViewById(R.id.loginmove);
        loginmove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Navheadermain.this, Loginlayout.class);
                startActivity(intent);

            }


        });


    }


}
