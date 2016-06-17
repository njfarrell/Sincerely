package com.njfarrell.android.sincerely.login.createaccount;

import android.content.Context;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.njfarrell.android.sincerely.R;
import com.njfarrell.android.sincerely.login.BaseLoginViewModel;
import com.njfarrell.android.sincerely.utils.InputUtils;

public class CreateAccountViewModel extends BaseLoginViewModel implements CreateAccountHandler {



    public interface CreateAccountListener {
        void onSignUpCompleted();

        void inavlidUsername(String error);

        void invalidPassword(String error);

        void invalidPasswordMatch();
    }

    private CreateAccountListener createAccountListener;
    private String reenterPassword;
    private Context context;

    public CreateAccountViewModel(Context context, CreateAccountListener listener) {
        this.createAccountListener = listener;
        this.context = context;
    }

    @Bindable
    public String getReenterPassword() {
        if (reenterPassword == null) {
            return "";
        }
        return reenterPassword;
    }

    public void setReenterPassword(String reenterPassword) {
        this.reenterPassword = reenterPassword;
        notifyPropertyChanged(BR.reenterPassword);
    }

    @Override
    public void onSignUpClicked(View view) {
        if (!InputUtils.isValidEmail(getEmail())) {
            createAccountListener.inavlidUsername(context.getString(R.string.email_pattern));
        } else if (getPassword().length() <= 5) {
            createAccountListener.invalidPassword(context.getString(R.string.password_length));
            return;
        } else if (!getPassword().equals(getReenterPassword())) {
            createAccountListener.invalidPasswordMatch();
            return;
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(getEmail(), getPassword()).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            handleSignupComplete(task);
                        } catch (Exception exception) {
                            // Handle different necessary firebase exceptions
                            exception.printStackTrace();
                        }
                    }
                });
    }

    private void handleSignupComplete(Task<AuthResult> task) throws Exception {
        if (task.isSuccessful()) {
            createAccountListener.onSignUpCompleted();
        } else {
            throw task.getException();
        }
    }
}
