package com.example.moya2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Joinlayout extends AppCompatActivity {
    private EditText reg_id, reg_pass, reg_name, reg_birth;
    private Button reg;
    //유저 데이터 저장 변수
    String gender, user_id, user_pass, user_name,no;
    Integer user_birth;

    //아이디 비밀번호 로그인 모듈 변수
    FirebaseAuth Auth;

    //현재 로그인 된 유저 정보를 담을 변수
    FirebaseUser currUser;

    private FirebaseDatabase Data;
    private DatabaseReference  User;

    private String id1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_layout);
        Auth = FirebaseAuth.getInstance();
        Data = FirebaseDatabase.getInstance();
        User = Data.getReference();
        currUser = Auth.getCurrentUser() ;
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.genderGroup);
        reg_id = (EditText) findViewById(R.id.idText);//id 입력
        reg_pass = (EditText) findViewById(R.id.passwordText);//password 입력
        reg_birth = (EditText) findViewById(R.id.birthText);//생일 입력
        reg_name = (EditText) findViewById(R.id.childText);//이름 입력

        reg = (Button) findViewById(R.id.joinButton1);//회원가입 버튼
        reg.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (reg_id.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    reg_id.requestFocus();
                    return;
                }
                if (reg_pass.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    reg_pass.requestFocus();
                    return;
                }

                if (reg_name.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    reg_name.requestFocus();
                    return;
                }
                if (reg_birth.getText().toString().length() >= 0 && reg_birth.getText().toString().length() < 8) {
                    Toast.makeText(getApplicationContext(), "생일을 확인하세요!", Toast.LENGTH_SHORT).show();
                    reg_birth.requestFocus();
                    return;
                }

                int rid = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(rid);
                gender = rb.getText().toString();//성별 데이터 저장
                //회원가입시 작성한 id 로그인 화면으로 값 전송
                Intent result = new Intent();
                result.putExtra("id", reg_id.getText().toString());

                //유저 데이터 변수에 저장
                user_id = reg_id.getText().toString();
                user_pass = reg_pass.getText().toString();
                user_name = reg_name.getText().toString();
                user_birth = Integer.parseInt(reg_birth.getText().toString());

                Auth.createUserWithEmailAndPassword(user_id,user_pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        Toast.makeText(Joinlayout.this, "비밀번호가 간단해요.", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(Joinlayout.this, "email 형식에 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        Toast.makeText(Joinlayout.this, "이미존재하는 email 입니다.", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(Joinlayout.this, "다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    JoinDB();
                                    finish();
                                }
                            }
                        });
                setResult(RESULT_OK, result);
                finish();

            }
        });
    }
    // 가입한 정보 DB로 넘김
    public void JoinDB(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        no = "no";

        User.child("USER").child(user.getUid()).child("ID").setValue(user_id);
        User.child("USER").child(user.getUid()).child("PASS").setValue(user_pass);
        User.child("USER").child(user.getUid()).child("NAME").setValue(user_name);
        User.child("USER").child(user.getUid()).child("GENDER").setValue(gender);
        User.child("USER").child(user.getUid()).child("BIRTH").setValue(user_birth);
        User.child("USER").child(user.getUid()).child("seek").setValue(no);
        User.child("USER").child(user.getUid()).child("avoid").setValue(no);
        User.child("USER").child(user.getUid()).child("sensor").setValue(no);
        User.child("USER").child(user.getUid()).child("regist").setValue(no);



    }


}



