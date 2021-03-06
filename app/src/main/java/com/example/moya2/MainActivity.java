package com.example.moya2;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
     Button bt;
    TextView txid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        bt = (Button)findViewById(R.id.loginmove);
        txid =(TextView)findViewById(R.id.text_id);

        TabHost tabHost1 = (TabHost)findViewById(R.id.host);
        tabHost1.setup();
        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("홈") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("데이터구조론") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("컴파일러 설계") ;
        tabHost1.addTab(ts3) ;
}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.toolbar_btn_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("검색");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.toolbar_btn_search){

        }
        if (id == R.id.toolbar_btn_navbar) {
            DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
            drawer.openDrawer(Gravity.RIGHT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager manager = getFragmentManager();

        if (id == R.id.nav_one_layout) {
            manager.beginTransaction().replace(R.id.content_main, new Onelayout(this)).commit();
        } else if (id == R.id.nav_two_layout) {
            manager.beginTransaction().replace(R.id.content_main,new Twolayout()).commit();
        } else if (id == R.id.nav_three_layout) {
            manager.beginTransaction().replace(R.id.content_main, new Threelayout()).commit();
        } else if (id == R.id.nav_four_layout) {
            manager.beginTransaction().replace(R.id.content_main, new Fourlayout()).commit();
        } else if (id == R.id.nav_five_layout) {
            manager.beginTransaction().replace(R.id.content_main, new Fivelayout()).commit();
        } else if (id == R.id.nav_six_logut){
            FirebaseAuth.getInstance().signOut();
            Intent SigunoutIntent = new Intent(MainActivity.this,Loginlayout.class);
            finish();
            startActivity(SigunoutIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginmove:
                Intent intent = new Intent(this,Loginlayout.class);
                startActivityForResult(intent,2000);
                break;
            case R.id.text_id:
                Intent intent1 = new Intent(this, profile.class);
                startActivity(intent1);
                break;
        }
    }
    //로그인 완료시 버튼 제거 및 사용자 계정 정보 출력
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("RESULT", requestCode + "");
        Log.d("RESULT", resultCode + "");
        Log.d("RESULT", data + "");
        Button loginmove = (Button)findViewById(R.id.loginmove);
        TextView user_id;
        user_id = (TextView)findViewById(R.id.text_id);

        if (requestCode == 2000 && resultCode == RESULT_OK) {
//            Toast.makeText(getApplicationContext(),"receive",Toast.LENGTH_SHORT).show();
            loginmove.setVisibility(View.GONE);
            user_id.setVisibility(View.VISIBLE);
            user_id.setText(data.getStringExtra("id"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
    }
}
