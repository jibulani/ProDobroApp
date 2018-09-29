package com.eugene.prodobroapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.eugene.prodobroapp.CatalogueService.CatalogueFragment;
import com.eugene.prodobroapp.QuestionService.QuestionFragment;
import com.eugene.prodobroapp.SendMessageService.SendMessageFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment currFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    currFragment = QuestionFragment.newInstance();
                    openFragment(currFragment);
                    return true;
                case R.id.navigation_dashboard:
                    currFragment = SendMessageFragment.newInstance();
                    openFragment(currFragment);
                    return true;
                case R.id.navigation_notifications:
                    currFragment = CatalogueFragment.newInstance();
                    openFragment(currFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currFragment = QuestionFragment.newInstance();
        openFragment(currFragment);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.filling, fragment)
                .addToBackStack(null).commit();
    }

}
