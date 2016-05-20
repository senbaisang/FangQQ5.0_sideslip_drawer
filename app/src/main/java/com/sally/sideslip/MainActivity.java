package com.sally.sideslip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sally.sideslip.views.SideSlipMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SideSlipMenu mSideslipMenu;
    private ImageView ivToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSideslipMenu = (SideSlipMenu) findViewById(R.id.id_sideslipmenu);
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
