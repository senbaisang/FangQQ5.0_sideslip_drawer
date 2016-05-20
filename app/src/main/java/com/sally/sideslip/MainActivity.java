package com.sally.sideslip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.sally.sideslip.views.SideslipMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SideslipMenu mSideslipMenu;
    private ImageView ivToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSideslipMenu = (SideslipMenu) findViewById(R.id.id_sideslipmenu);
        ivToggle = (ImageView) findViewById(R.id.id_avatar_menu);
        ivToggle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_avatar_menu:
                mSideslipMenu.menuToggle();
                break;
        }
    }
}
