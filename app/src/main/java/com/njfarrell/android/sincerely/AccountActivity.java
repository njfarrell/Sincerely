package com.njfarrell.android.sincerely;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.njfarrell.android.sincerely.databinding.ActivityAccountBinding;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAccountBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_account);

        setSupportActionBar(binding.toolbar);
    }
}
