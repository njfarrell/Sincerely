package com.njfarrell.android.sincerely.login;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;

public abstract class BaseLoginFragment extends Fragment {

    public interface LoginListener {
        void showSignUp();

        void launchAccountActivity();
    }

    protected LoginListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (LoginListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.getClass().getName() + " must implement "
                    + LoginListener.class.getName());
        }
    }

    protected TextWatcher emailTextChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            handleEmailTextFieldChange(editable.toString());
        }
    };

    protected TextWatcher passwordTextChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            handlePasswordTextFieldChange(editable.toString());
        }
    };

    public abstract void handleEmailTextFieldChange(String email);
    public abstract void handlePasswordTextFieldChange(String password);
}
