package com.example.moya2;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Onelayout extends Fragment {
    Context mContext;
    View v;
    RadioGroup rg[] = new RadioGroup[35];
    String text[] = new String[35];
    int score[] = new int[35];
    int score_seek, score_avoid, score_sensor, score_regist;
    TextView seek, avoid, sensor, regist;
    String id = "R.id.rg_";
    Button done;
    private View header;

    public Onelayout() {
    }

    @SuppressLint("ValidFragment")
    public Onelayout(Context mContext) {
        this.mContext = mContext;
    }

    public int compare(String source) {
        int get_score;
        if (source.equals("항상")) get_score = 5;
        else if (source.equals("자주")) get_score = 4;
        else if (source.equals("종종")) get_score = 3;
        else if (source.equals("가끔")) get_score = 2;
        else if (source.equals("전혀")) get_score = 1;
        else if (source.equals("전혀")) get_score = 0;
        else if (source.equals("해당없음")) get_score = 0;
        else get_score = 1;
        return get_score;
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.one_layout, container, false);
        header = getLayoutInflater().inflate(R.layout.result, null, false);
        seek = (TextView) header.findViewById(R.id.text_seek);
        avoid = (TextView) header.findViewById(R.id.text_avoid);
        sensor = (TextView) header.findViewById(R.id.text_sensor);
        regist = (TextView) header.findViewById(R.id.text_regist);

        for (int i = 1; i <= 34; i++) {
            int k = getResources().getIdentifier("rg_" + i, "id", mContext.getPackageName());
            rg[i] = v.findViewById(k);
        }
        done = v.findViewById(R.id.btn_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idx = 0;
                for (int i = 1; i <= 34; i++) {
                    RadioButton rb = (RadioButton) v.findViewById(rg[i].getCheckedRadioButtonId());
                    if (rb == null) {
                        Toast.makeText(mContext, "완료되지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    text[i] = rb.getText().toString();
                    score[i] = compare(text[i]);
                    score_seek = score[1] + score[2] + score[3] + score[4] + score[5]
                            + score[6] + score[7];
                    score_avoid = score[8] + score[9] + score[10] + score[11] + score[12] +
                            score[13] + score[14] + score[15] + score[16];
                    score_sensor = score[17] + score[18] + score[19] + score[20] + score[21] +
                            score[22] + score[23] + score[24] + score[25] + score[26];
                    score_regist = score[27] + score[28] + score[29] + score[30] + score[31] +
                            score[32] + score[33] + score[34];
                    seek.setText(String.valueOf(score_seek));
                    avoid.setText(String.valueOf(score_avoid));
                    sensor.setText(String.valueOf(score_sensor));
                    regist.setText(String.valueOf(score_regist));

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    DatabaseReference resultDB = FirebaseDatabase.getInstance().getReference();
                    resultDB.child("USER").child(user.getUid()).child("seek").setValue(String.valueOf(score_seek));
                    resultDB.child("USER").child(user.getUid()).child("avoid").setValue(String.valueOf(score_avoid));
                    resultDB.child("USER").child(user.getUid()).child("sensor").setValue(String.valueOf(score_sensor));
                    resultDB.child("USER").child(user.getUid()).child("regist").setValue(String.valueOf(score_regist));

                    Intent intent = new Intent(getActivity(), result.class);
                    intent.putExtra("seek", score_seek);
                    intent.putExtra("avoid", score_avoid);
                    intent.putExtra("sensor", score_sensor);
                    intent.putExtra("regist", score_regist);
                    intent.putExtra("one","one");
                    startActivity(intent);
                }
            }
        });
        return v;
    }
}


