package com.example.myapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import fragment.FragmentFive;
import fragment.FragmentFour;
import fragment.FragmentOne;
import fragment.FragmentThree;
import fragment.FragmentTwo;

public class Main2Activity extends AppCompatActivity  {

    private ViewPager viewPager1;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager1.setCurrentItem(0);
                    return true;
                case R.id.navigation_find:
                    viewPager1.setCurrentItem(1);
                    return true;
                case R.id.navigation_trolley:
                    viewPager1.setCurrentItem(2);
                    return true;
                case R.id.navigation_form:
                    viewPager1.setCurrentItem(3);
                    return true;
                case R.id.navigation_mien:
                    viewPager1.setCurrentItem(4);
                    return true;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager1 = findViewById(R.id.viewPager1);

        viewPager1.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new FragmentOne();
                    case 1:
                        return new FragmentTwo();
                    case 2:
                        return new FragmentThree();
                    case 3:
                        return new FragmentFour();
                    case 4:
                        return new FragmentFive();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

        viewPager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_find);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_trolley);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_form);
                        break;
                    case 4:
                        navigation.setSelectedItemId(R.id.navigation_mien);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
