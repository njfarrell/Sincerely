package com.njfarrell.android.sincerely.login;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

public class BaseLoginViewModel extends BaseObservable {

    protected String email;
    protected String password;
    protected boolean isValidLogin;

    @Bindable
    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }

    @Bindable
    public String getPassword() {
        if (password == null) {
            return "";
        }
        return password;
    }

    @Bindable
    public boolean getIsValidLogin() {
        return isValidLogin;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void validateLogin() {
        isValidLogin = getEmail().length() > 0
                && getPassword().length() > 0;
        notifyPropertyChanged(BR.isValidLogin);
    }
}
