package com.njfarrell.android.sincerely.login.createaccount;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njfarrell.android.sincerely.R;
import com.njfarrell.android.sincerely.databinding.FragmentCreateAccountBinding;

public class CreateAccountFragment extends Fragment
        implements CreateAccountViewModel.CreateAccountListener {

    private FragmentCreateAccountBinding binding;
    private CreateAccountViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_create_account, container, false);

        viewModel = new CreateAccountViewModel(getContext(), this);

        binding.setViewModel(viewModel);
        binding.setHandler(viewModel);

        binding.emailEdittext.addTextChangedListener(emailTextChangeListener);
        binding.passwordEdittext.addTextChangedListener(passwordTextChangeListener);
        binding.reenterPasswordEdittext.addTextChangedListener(reenterPasswordTextChangeListener);

        return binding.getRoot();
    }

    private TextWatcher emailTextChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            viewModel.setEmail(editable.toString());
            viewModel.validateLogin();
        }
    };

    private TextWatcher passwordTextChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            viewModel.setPassword(editable.toString());
            viewModel.validateLogin();
        }
    };

    private TextWatcher reenterPasswordTextChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            viewModel.setReenterPassword(editable.toString());
            viewModel.validateLogin();
        }
    };

    @Override
    public void onSignUpCompleted() {
        //TODO: launch main activity
    }

    @Override
    public void inavlidUsername(String error) {
        binding.emailEdittext.setError(error);
    }

    @Override
    public void invalidPassword(String error) {
        binding.passwordEdittext.setError(error);
    }

    @Override
    public void invalidPasswordMatch() {
        binding.reenterPasswordEdittext.setError(getString(R.string.password_match));
    }
}
