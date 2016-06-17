package com.njfarrell.android.sincerely.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njfarrell.android.sincerely.R;
import com.njfarrell.android.sincerely.databinding.FragmentLoginBinding;

public class LoginFragment extends BaseLoginFragment implements LoginViewModel.LoginListener,
        View.OnClickListener {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,
                container, false);

        viewModel = new LoginViewModel(getContext(), this);

        binding.setViewModel(viewModel);
        binding.setHandler(viewModel);

        binding.emailEdittext.addTextChangedListener(emailTextChangeListener);
        binding.passwordEdittext.addTextChangedListener(passwordTextChangeListener);
        binding.linkSignup.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onLoginCompleted() {
        listener.launchAccountActivity();
    }

    @Override
    public void inavlidUsername(String error) {
        binding.emailInputLayout.setError(error);
        binding.emailInputLayout.setErrorEnabled(true);
    }

    @Override
    public void invalidPassword(String error) {
        binding.passwordInputLayout.setError(error);
        binding.passwordInputLayout.setErrorEnabled(true);
    }

    @Override
    public void onClick(View view) {
        listener.showSignUp();
    }

    @Override
    public void handleEmailTextFieldChange(String email) {
        binding.emailInputLayout.setError(null);
        binding.emailInputLayout.setErrorEnabled(false);
        viewModel.setEmail(email);
        viewModel.validateLogin();
    }

    @Override
    public void handlePasswordTextFieldChange(String password) {
        binding.passwordInputLayout.setError(null);
        binding.passwordInputLayout.setErrorEnabled(false);
        viewModel.setPassword(password);
        viewModel.validateLogin();
    }
}
