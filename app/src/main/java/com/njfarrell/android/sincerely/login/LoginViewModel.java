package com.njfarrell.android.sincerely.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.njfarrell.android.sincerely.R;
import com.njfarrell.android.sincerely.utils.InputUtils;

public class LoginViewModel extends BaseLoginViewModel implements LoginHandler {

    public interface LoginListener {
        void onLoginCompleted();

        void inavlidUsername(String error);

        void invalidPassword(String error);
    }

    private Context context;
    private LoginListener loginListener;

    public LoginViewModel(Context context, @NonNull LoginListener loginListener) {
        this.context = context;
        this.loginListener = loginListener;
    }

    @Override
    public void onLoginClicked(View view) {
        if (!InputUtils.isValidEmail(getEmail())) {
            loginListener.inavlidUsername(context.getString(R.string.email_pattern));
        } else if (getPassword().length() <= 5) {
            loginListener.invalidPassword(context.getString(R.string.password_length));
            return;
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(getEmail(), getPassword()).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    try {
                        handleLoginComplete(task);
                    } catch (FirebaseAuthInvalidUserException userAuthException) {
                        loginListener.inavlidUsername(context.getString(R.string.invalid_email));
                    } catch (FirebaseAuthInvalidCredentialsException credentialException) {
                        loginListener.invalidPassword(
                                context.getString(R.string.incorrect_password));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    private void handleLoginComplete(Task<AuthResult> task) throws Exception {
        if (task.isSuccessful()) {
            loginListener.onLoginCompleted();
        } else {
            throw task.getException();
        }
    }
}
