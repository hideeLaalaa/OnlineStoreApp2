package com.example.onlinestoreapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.graphics.Color.WHITE;
import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigate;
    public SharedPreferences sp;
    private AllFragment allFragment;
    private  CatOneFragment catOneFragment;
    private  CatTwoFragment catTwoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sp=getSharedPreferences(save,MODE_PRIVATE);
//        FrameLayout frameLayout = findViewById(R.id.frame);
//        String colorBar = sp.getString("colorBar", String.valueOf(WHITE));
//        frameLayout.setBackgroundColor(Color.colorBar);

        allFragment = new AllFragment();
        catOneFragment = new CatOneFragment();
        catTwoFragment = new CatTwoFragment();

        setFragment(allFragment);

        navigate = findViewById(R.id.bottomBar);

        navigate.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.all:
//                        BottomNavigationView view = findViewById(R.id.bottomBar);
//                        GradientDrawable bg = (GradientDrawable) getResources().getDrawable(R.drawable.rounded);
//                        bg.setShape(GradientDrawable.OVAL);
                        getWindow().setStatusBarColor(0xFF004164);
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#416388")));
                        navigate.setItemBackground(null);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                navigate.setBackground(getResources().getDrawable(R.drawable.rounded));
                                navigate.setItemBackground(getResources().getDrawable(R.drawable.nav_style));
                            }

                        },50);
                        setFragment(allFragment);
                        break;

                    case R.id.catOne:
                        getWindow().setStatusBarColor(Color.parseColor("#612637"));
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF8A4659));

                        navigate.setItemBackground(null);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                navigate.setBackground(getResources().getDrawable(R.drawable.rounded_cat1));
                                navigate.setItemBackground(getResources().getDrawable(R.drawable.nav_style_cat1));
                            }

                        },50);
                        setFragment(catOneFragment);

                        break;

                    case R.id.catTwo:
                        getWindow().setStatusBarColor(Color.parseColor("#442661"));
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF71469A));

                        navigate.setItemBackground(null);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                navigate.setBackground(getResources().getDrawable(R.drawable.rounded_cat2));
                                navigate.setItemBackground(getResources().getDrawable(R.drawable.nav_style_cat2));
                            }

                        },50);
                        setFragment(catTwoFragment);
                        break;

                }
                return true;
            }
        });

    }

    public  void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

}