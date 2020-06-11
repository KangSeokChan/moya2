package com.example.moya2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity implements View.OnClickListener {
    private Button logout,remove,result_bu;
    private TextView email,name,gender,birth,seek,avoid,sensor,regist;
    private String id1;
    private  FirebaseAuth auth;
    private DatabaseReference Ref;
    private DatabaseReference Ref1;
    FirebaseDatabase Date_re;
    FirebaseUser currUser;
    Toolbar toolbar;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        Date_re= FirebaseDatabase.getInstance();
        Ref = Date_re.getReference("USER");
        logout = (Button)findViewById(R.id.Logout);
        remove = (Button)findViewById(R.id.remove);
        email = (TextView)findViewById(R.id.UserEmail);
        name = (TextView)findViewById(R.id.Username);
        gender = (TextView)findViewById(R.id.Usergender);
        birth = (TextView)findViewById(R.id.Userbirth);
        seek = (TextView)findViewById(R.id.seek);
        avoid = (TextView)findViewById(R.id.avoid);
        sensor = (TextView)findViewById(R.id.sensor);
        regist = (TextView)findViewById(R.id.regist);
        result_bu = (Button)findViewById(R.id.result_bu);

        profile();
        logout.setOnClickListener(this);
        remove.setOnClickListener(this);
    }

    public  void  onClick(View view){
        if(view.getId() == R.id.Logout){
            auth.signOut();
            Toast.makeText(this,"로그아웃 되었습니다!" , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivityForResult(intent, 3000);
            finish();
        }

        if(view.getId() == R.id.remove){
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(profile.this);
            alert_confirm.setMessage("정말 계정을 삭제 할까요?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Ref.child(user.getUid()).removeValue();
                                    Toast.makeText(profile.this,"계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            });
                }
            });
            alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(profile.this, "취소", Toast.LENGTH_LONG).show();
                }
            });
            alert_confirm.show();
        }
        if (view.getId() == R.id.result_bu){

            FirebaseUser user = auth.getCurrentUser();
            Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    FirebaseUser user = auth.getCurrentUser();
                    for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                        String id_db = messageData.child("ID").getValue().toString();
                        String seek_db = messageData.child("seek").getValue().toString();
                        String avoid_db = messageData.child("avoid").getValue().toString();
                        String sensor_db = messageData.child("sensor").getValue().toString();
                        String regist_db = messageData.child("regist").getValue().toString();

                        if (user.getEmail().equals(id_db) && !seek_db.equals("no") ) {

                            Intent intent = new Intent(profile.this, result.class);
                            intent.putExtra("pro","pro");
                            intent.putExtra("seek_db",seek_db);
                            intent.putExtra("avoid_db",avoid_db);
                            intent.putExtra("sensor_db",sensor_db);
                            intent.putExtra("regist_db",regist_db);
                            startActivity(intent);
                        }

                        else if (seek_db.equals("no")){
                            Toast.makeText(profile.this,"설문을 통해 결과를 확인해보세요! ^.~ " , Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    public void profile() {
        FirebaseUser user = auth.getCurrentUser();
        if (user.getEmail().equals("admin@ac.kr")) {
            email.setText("반갑습니다 관리자님!");
            FirebaseDatabase.getInstance().getReference("ADMIN").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    FirebaseUser user = auth.getCurrentUser();
                    for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                        String id_db = messageData.child("ID").getValue().toString();
                        String name_db = messageData.child("NAME").getValue().toString();
                        String birth_db = messageData.child("BIRTH").getValue().toString();
                        String gender_db = messageData.child("GENDER").getValue().toString();
                        String seek_db = messageData.child("seek").getValue().toString();

                        if (user.getEmail().equals(id_db)) {

                            name.setText("아이 이름 : " + name_db.toString());
                            gender.setText("아이 성별 : " + gender_db.toString());
                            birth.setText("아이 탄생일 : " + birth_db.toString());

                            if (seek_db.equals("no")) {
                                seek.setText("설문을 통해 아이를 알아보세요!");
                            } else {
                                sumDB();
                            }

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

       else {
            email.setText("반갑습니다. "+user.getEmail()+"님!");
            Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    FirebaseUser user = auth.getCurrentUser();
                    for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                        String id_db = messageData.child("ID").getValue().toString();
                        String name_db = messageData.child("NAME").getValue().toString();
                        String birth_db = messageData.child("BIRTH").getValue().toString();
                        String gender_db = messageData.child("GENDER").getValue().toString();
                        String seek_db = messageData.child("seek").getValue().toString();

                        if (user.getEmail().equals(id_db)) {

                            name.setText("아이 이름 : " + name_db.toString());
                            gender.setText("아이 성별 : " + gender_db.toString());
                            birth.setText("아이 탄생일 : " + birth_db.toString());

                            if (seek_db.equals("no")) {
                                seek.setText("설문을 통해 아이를 알아보세요!");
                            } else {
                                sumDB();
                            }

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }


    public void sumDB(){
        FirebaseUser user = auth.getCurrentUser();
        Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseUser user = auth.getCurrentUser();
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String id_db = messageData.child("ID").getValue().toString();
                    String seek_db = messageData.child("seek").getValue().toString();
                    String avoid_db = messageData.child("avoid").getValue().toString();
                    String sensor_db = messageData.child("sensor").getValue().toString();
                    String regist_db = messageData.child("regist").getValue().toString();

                    if (user.getEmail().equals(id_db)) {

                        seek.setText("감각 추구 : " + seek_db.toString());
                        avoid.setText("감각 회피 : " + avoid_db.toString());
                        sensor.setText("감각 민첩 : " + sensor_db.toString());
                        regist.setText("감각 둔감 : " + regist_db.toString());

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
