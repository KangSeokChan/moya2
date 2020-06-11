package com.example.moya2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class result extends AppCompatActivity {
    int result_avoid, result_sensor, result_seek, result_resist;
    Button exit;
    TextView seek, avoid, sensor, regist,seek1,seek2,seek3,seek4,seek5;
    TextView avoid1,avoid2,avoid3,avoid4,avoid5;
    TextView sen1,sen2,sen3,sen4,sen5;
    TextView re1,re2,re3,re4,re5;
    int score_avoid,score_sensor,score_regist,score_seek;
    String score_avoid1,score_sensor1,score_regist1,score_seek1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Intent intent = getIntent();
        seek = (TextView)findViewById(R.id.text_seek);
        avoid = (TextView)findViewById(R.id.text_avoid);
        sensor = (TextView)findViewById(R.id.text_sensor);
        regist = (TextView)findViewById(R.id.text_regist);

        String str =getIntent().getStringExtra("one");
        String str1 =getIntent().getStringExtra("pro");

        if (str1 == null && str.equals("one")){
            score_seek = getIntent().getIntExtra("seek",1);
            score_avoid = getIntent().getIntExtra("avoid",1);
            score_sensor = getIntent().getIntExtra("sensor",1);
            score_regist = getIntent().getIntExtra("regist",1);

            seek.setText(String.valueOf(score_seek));
            avoid.setText(String.valueOf(score_avoid));
            sensor.setText(String.valueOf(score_sensor));
            regist.setText(String.valueOf(score_regist));

            if (score_seek == 0){
                seek1 = (TextView)findViewById(R.id.seek1);
                seek1.setText(String.valueOf(score_seek));
                seek1.setTextColor(Color.parseColor("#000000"));
                seek1.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_seek >=1 && score_seek <=5 ){
                seek2 = (TextView)findViewById(R.id.seek2);
                seek2.setText(String.valueOf(score_seek));
                seek2.setTextColor(Color.parseColor("#000000"));
                seek2.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_seek >=6 && score_seek <=17){
                seek3 = (TextView)findViewById(R.id.seek3);
                seek3.setText(String.valueOf(score_seek));
                seek3.setTextColor(Color.parseColor("#000000"));
                seek3.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_seek >=18 && score_seek <=23){
                seek4 = (TextView)findViewById(R.id.seek4);
                seek4.setText(String.valueOf(score_seek));
                seek4.setTextColor(Color.parseColor("#000000"));
                seek4.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else {
                seek5 = (TextView)findViewById(R.id.seek5);
                seek5.setText(String.valueOf(score_seek));
                seek5.setTextColor(Color.parseColor("#000000"));
                seek5.setBackgroundColor(Color.parseColor("#FFD243"));
            }

            if (score_avoid <=1){
                avoid1 = (TextView)findViewById(R.id.avoid1);
                avoid1.setText(String.valueOf(score_avoid));
                avoid1.setTextColor(Color.parseColor("#000000"));
                avoid1.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_avoid >=2 && score_avoid <=8 ){
                avoid2 = (TextView)findViewById(R.id.avoid2);
                avoid2.setText(String.valueOf(score_avoid));
                avoid2.setTextColor(Color.parseColor("#000000"));
                avoid2.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_avoid >=9 && score_avoid <=22){
                avoid3 = (TextView)findViewById(R.id.avoid3);
                avoid3.setText(String.valueOf(score_avoid));
                avoid3.setTextColor(Color.parseColor("#000000"));
                avoid3.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_avoid >=23 && score_avoid <=29){
                avoid4 = (TextView)findViewById(R.id.avoid4);
                avoid4.setText(String.valueOf(score_avoid));
                avoid4.setTextColor(Color.parseColor("#000000"));
                avoid4.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else {
                avoid5 = (TextView)findViewById(R.id.avoid5);
                avoid5.setText(String.valueOf(score_avoid));
                avoid5.setTextColor(Color.parseColor("#000000"));
                avoid5.setBackgroundColor(Color.parseColor("#FFD243"));
            }

            if (score_sensor <= 2){
                sen1 = (TextView)findViewById(R.id.sen1);
                sen1.setText(String.valueOf(score_sensor));
                sen1.setTextColor(Color.parseColor("#000000"));
                sen1.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_sensor >= 3 && score_sensor <= 9 ){
                sen2 = (TextView)findViewById(R.id.sen2);
                sen2.setText(String.valueOf(score_sensor));
                sen2.setTextColor(Color.parseColor("#000000"));
                sen2.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_sensor >=10 && score_sensor <=24){
                sen3 = (TextView)findViewById(R.id.sen3);
                sen3.setText(String.valueOf(score_sensor));
                sen3.setTextColor(Color.parseColor("#000000"));
                sen3.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_sensor >=25 && score_sensor <=31){
                sen4 = (TextView)findViewById(R.id.sen4);
                sen4.setText(String.valueOf(score_sensor));
                sen4.setTextColor(Color.parseColor("#000000"));
                sen4.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else {
                sen5 = (TextView)findViewById(R.id.sen5);
                sen5.setText(String.valueOf(score_sensor));
                sen5.setTextColor(Color.parseColor("#000000"));
                sen5.setBackgroundColor(Color.parseColor("#FFD243"));
            }

            if (score_regist == 0){
                re1 = (TextView)findViewById(R.id.re1);
                re1.setText(String.valueOf(score_regist));
                re1.setTextColor(Color.parseColor("#000000"));
                re1.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_regist >=1 && score_regist <=5 ){
                re2 = (TextView)findViewById(R.id.re2);
                re2.setText(String.valueOf(score_regist));
                re2.setTextColor(Color.parseColor("#000000"));
                re2.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_regist >=6 && score_regist <=17){
                re3 = (TextView)findViewById(R.id.re3);
                re3.setText(String.valueOf(score_regist));
                re3.setTextColor(Color.parseColor("#000000"));
                re3.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (score_regist >=18 && score_regist <=23){
                re4 = (TextView)findViewById(R.id.re4);
                re4.setText(String.valueOf(score_regist));
                re4.setTextColor(Color.parseColor("#000000"));
                re4.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else {
                re5 = (TextView)findViewById(R.id.re5);
                re5.setText(String.valueOf(score_regist));
                re5.setTextColor(Color.parseColor("#000000"));
                re5.setBackgroundColor(Color.parseColor("#FFD243"));
            }

        }


        else if(str == null && str1.equals("pro")){
            score_seek1 = getIntent().getStringExtra("seek_db");
            score_avoid1 = getIntent().getStringExtra("avoid_db");
            score_sensor1 = getIntent().getStringExtra("sensor_db");
            score_regist1 = getIntent().getStringExtra("regist_db");

            seek.setText(score_seek1);
            avoid.setText(score_avoid1);
            sensor.setText(score_sensor1);
            regist.setText(score_regist1);

            int se = Integer.valueOf(score_seek1);
            int av = Integer.valueOf(score_avoid1);
            int sena = Integer.valueOf(score_sensor1);
            int rea = Integer.valueOf(score_regist1);

            if (se == 0){
                seek1 = (TextView)findViewById(R.id.seek1);
                seek1.setText(String.valueOf(se));
                seek1.setTextColor(Color.parseColor("#000000"));
                seek1.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (se >=1 && se <=5 ){
                seek2 = (TextView)findViewById(R.id.seek2);
                seek2.setText(String.valueOf(se));
                seek2.setTextColor(Color.parseColor("#000000"));
                seek2.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (se >=6 && se <=17){
                seek3 = (TextView)findViewById(R.id.seek3);
                seek3.setText(String.valueOf(se));
                seek3.setTextColor(Color.parseColor("#000000"));
                seek3.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (se >=18 && se <=23){
                seek4 = (TextView)findViewById(R.id.seek4);
                seek4.setText(String.valueOf(se));
                seek4.setTextColor(Color.parseColor("#000000"));
                seek4.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else {
                seek5 = (TextView)findViewById(R.id.seek5);
                seek5.setText(String.valueOf(se));
                seek5.setTextColor(Color.parseColor("#000000"));
                seek5.setBackgroundColor(Color.parseColor("#FFD243"));
            }

            if (av <=1){
                avoid1 = (TextView)findViewById(R.id.avoid1);
                avoid1.setText(String.valueOf(av));
                avoid1.setTextColor(Color.parseColor("#000000"));
                avoid1.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (av >=2 && av <=8 ){
                avoid2 = (TextView)findViewById(R.id.avoid2);
                avoid2.setText(String.valueOf(av));
                avoid2.setTextColor(Color.parseColor("#000000"));
                avoid2.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (av >=9 && av <=22){
                avoid3 = (TextView)findViewById(R.id.avoid3);
                avoid3.setText(String.valueOf(av));
                avoid3.setTextColor(Color.parseColor("#000000"));
                avoid3.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (av >=23 && av <=29){
                avoid4 = (TextView)findViewById(R.id.avoid4);
                avoid4.setText(String.valueOf(av));
                avoid4.setTextColor(Color.parseColor("#000000"));
                avoid4.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else {
                avoid5 = (TextView)findViewById(R.id.avoid5);
                avoid5.setText(String.valueOf(av));
                avoid5.setTextColor(Color.parseColor("#000000"));
                avoid5.setBackgroundColor(Color.parseColor("#FFD243"));
            }

            if (sena <= 2){
                sen1 = (TextView)findViewById(R.id.sen1);
                sen1.setText(String.valueOf(sena));
                sen1.setTextColor(Color.parseColor("#000000"));
                sen1.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (sena >= 3 && sena <= 9 ){
                sen2 = (TextView)findViewById(R.id.sen2);
                sen2.setText(String.valueOf(sena));
                sen2.setTextColor(Color.parseColor("#000000"));
                sen2.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (sena >=10 && sena <=24){
                sen3 = (TextView)findViewById(R.id.sen3);
                sen3.setText(String.valueOf(sena));
                sen3.setTextColor(Color.parseColor("#000000"));
                sen3.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (sena >=25 && sena <=31){
                sen4 = (TextView)findViewById(R.id.sen4);
                sen4.setText(String.valueOf(sena));
                sen4.setTextColor(Color.parseColor("#000000"));
                sen4.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else {
                sen5 = (TextView)findViewById(R.id.sen5);
                sen5.setText(String.valueOf(sena));
                sen5.setTextColor(Color.parseColor("#000000"));
                sen5.setBackgroundColor(Color.parseColor("#FFD243"));
            }

            if (rea == 0){
                re1 = (TextView)findViewById(R.id.re1);
                re1.setText(String.valueOf(rea));
                re1.setTextColor(Color.parseColor("#000000"));
                re1.setBackgroundColor(Color.parseColor("#FFD243"));

            }
            else if (rea >=1 && rea <=5 ){
                re2 = (TextView)findViewById(R.id.re2);
                re2.setText(String.valueOf(rea));
                re2.setTextColor(Color.parseColor("#000000"));
                re2.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (rea >=6 && rea <=17){
                re3 = (TextView)findViewById(R.id.re3);
                re3.setText(String.valueOf(rea));
                re3.setTextColor(Color.parseColor("#000000"));
                re3.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else if (rea >=18 && rea <=23){
                re4 = (TextView)findViewById(R.id.re4);
                re4.setText(String.valueOf(rea));
                re4.setTextColor(Color.parseColor("#000000"));
                re4.setBackgroundColor(Color.parseColor("#FFD243"));
            }
            else {
                re5 = (TextView)findViewById(R.id.re5);
                re5.setText(String.valueOf(rea));
                re5.setTextColor(Color.parseColor("#000000"));
                re5.setBackgroundColor(Color.parseColor("#FFD243"));
            }

        }
        exit = findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(result.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();

            }
        });
    }
}
