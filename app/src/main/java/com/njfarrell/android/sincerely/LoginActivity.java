package com.njfarrell.android.sincerely;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.njfarrell.android.sincerely.login.BaseLoginFragment;
import com.njfarrell.android.sincerely.login.LoginFragment;
import com.njfarrell.android.sincerely.login.signup.SignUpFragment;
import com.njfarrell.android.sincerely.utils.PrefsUtil;

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
    public void showSignUp() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.fragment_container, new SignUpFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void launchAccountActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            PrefsUtil.getInstance(this).storeUserUUID(user.getUid());
            
            Intent accountActivity = new Intent(this, AccountActivity.class);
            startActivity(accountActivity);
        }
    }
}
