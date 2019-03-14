package com.peng.commonlib.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.peng.commonlib.R;
import com.peng.commonlib.routing.RoutingConstants;

@Route(path = RoutingConstants.ROUTING_BINDING_ACTIVITY)
public class BindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
    }
}
