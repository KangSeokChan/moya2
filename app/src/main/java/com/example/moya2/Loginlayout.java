package com.example.moya2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class Loginlayout extends AppCompatActivity implements View.OnClickListener, Serializable {

    private EditText edit_id, edit_pass;
    private Button login;
    int bt;
    private TextView text;
    //아이디 비밀번호 로그인 모듈 변수
     FirebaseAuth mAuth;

    //현재 로그인 된 유저 정보를 담을 변수
     FirebaseUser currUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mAuth = FirebaseAuth.getInstance();
        currUser = mAuth.getCurrentUser();
        edit_id = (EditText) findViewById(R.id.idText);
        edit_pass = (EditText) findViewById(R.id.passwordText);
        login = (Button) findViewById(R.id.loginButton);
        text = (TextView) findViewById(R.id.text_id);

        login.setOnClickListener(this);
    }
        private void login(){
                if (edit_id.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    edit_id.requestFocus();
                    return;
                }
                if (edit_pass.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "패스워드를 입력하세요!", Toast.LENGTH_SHORT).show();
                    edit_pass.requestFocus();
                    return;
                }

                loginStart();

        }

        private void loginStart( ){
            final String log_id = edit_id.getText().toString();
            final String log_pass = edit_pass.getText().toString();
            mAuth.signInWithEmailAndPassword(log_id,log_pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Loginlayout.this,"로그인 되었습니다!" , Toast.LENGTH_SHORT).show();
                                Intent result = new Intent(Loginlayout.this, MainActivity.class);
                                result.putExtra("id", log_id);
                                setResult(RESULT_OK, result);
                                finish();
                            }
                            else {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidUserException e) {
                                    Toast.makeText(Loginlayout.this,"존재하지 않는 id 입니다." , Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(Loginlayout.this,"이메일 형식이 맞지 않습니다." , Toast.LENGTH_SHORT).show();
                                } catch (FirebaseNetworkException e) {
                                    Toast.makeText(Loginlayout.this,"Firebase NetworkException" , Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(Loginlayout.this,"Exception" , Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
              }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("RESULT", requestCode + "");
        Log.d("RESULT", resultCode + "");
        Log.d("RESULT", data + "");

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "회원가입을 완료했습니다!", Toast.LENGTH_SHORT).show();
            edit_id.setText(data.getStringExtra("id"));
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginButton){
            login();
        }
        if(v.getId() == R.id.joinButton) {
            Intent intent = new Intent(this, Joinlayout.class);
            startActivityForResult(intent, 1000);
        }

    }

}


