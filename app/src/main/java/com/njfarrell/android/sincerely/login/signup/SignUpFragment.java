package com.njfarrell.android.sincerely.login.signup;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njfarrell.android.sincerely.R;
import com.njfarrell.android.sincerely.databinding.FragmentSignUpBinding;
import com.njfarrell.android.sincerely.login.BaseLoginFragment;

public class SignUpFragment extends BaseLoginFragment
        implements SignUpViewModel.CreateAccountListener {

    private FragmentSignUpBinding binding;
    private SignUpViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sign_up, container, false);

        viewModel = new SignUpViewModel(getContext(), this);

        binding.setViewModel(viewModel);
        binding.setHandler(viewModel);

        binding.emailEdittext.addTextChangedListener(emailTextChangeListener);
        binding.passwordEdittext.addTextChangedListener(passwordTextChangeListener);
        binding.reenterPasswordEdittext.addTextChangedListener(reenterPasswordTextChangeListener);

        return binding.getRoot();
    }

    private TextWatcher reenterPasswordTextChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            handleReenterPasswordTextFieldChange(editable.toString());
        }
    };

    @Override
    public void onSignUpCompleted() {
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
    public void invalidPasswordMatch() {
        binding.reenterPasswordInputLayout.setError(getString(R.string.password_match));
        binding.reenterPasswordInputLayout.setErrorEnabled(true);
    }

    @Override
    public void handleEmailTextFieldChange(String email) {
        viewModel.setEmail(email);
        viewModel.validateLogin();
    }

    @Override
    public void handlePasswordTextFieldChange(String password) {
        viewModel.setPassword(password);
        viewModel.validateLogin();
    }

    private void handleReenterPasswordTextFieldChange(String reenterPassword) {
        viewModel.setReenterPassword(reenterPassword);
        viewModel.validateLogin();
    }
}
