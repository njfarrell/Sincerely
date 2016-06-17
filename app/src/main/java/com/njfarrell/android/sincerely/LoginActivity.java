package com.njfarrell.android.sincerely;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.njfarrell.android.sincerely.login.BaseLoginFragment;
import com.njfarrell.android.sincerely.login.LoginFragment;
import com.njfarrell.android.sincerely.login.createaccount.CreateAccountFragment;

public class LoginActivity extends AppCompatActivity implements BaseLoginFragment.LoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_login);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }

    @Override
    public void showCreateAccount() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.fragment_container, new CreateAccountFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void launchAccountActivity() {
        // TODO store user UUID in preferences

        Intent accountActivity = new Intent(this, AccountActivity.class);
        startActivity(accountActivity);
    }
}
