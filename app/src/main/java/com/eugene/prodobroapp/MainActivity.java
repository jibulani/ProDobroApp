package com.eugene.prodobroapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment currFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    TextView currQuestionTextView = QuestionLayoutUtil
//                            .getQuestionTextView((TextView) layout.getViewById(R.id.questionTextView));
//
//                    layout.addView(currQuestionTextView);
                    currFragment = QuestionFragment.newInstance();
                    openFragment(currFragment);

//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    currFragment = SendMessageFragment.newInstance();
                    openFragment(currFragment);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
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

        mTextMessage = (TextView) findViewById(R.id.message);
//        layout = (ConstraintLayout) findViewById(R.id.filling);
//        System.out.println(layout.getViewById(R.id.questionTextView));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.filling, fragment)
                .addToBackStack(null).commit();
    }

}
