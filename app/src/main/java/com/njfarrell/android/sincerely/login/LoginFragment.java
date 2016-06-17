package com.njfarrell.android.sincerely.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njfarrell.android.sincerely.AccountActivity;
import com.njfarrell.android.sincerely.R;
import com.njfarrell.android.sincerely.databinding.FragmentLoginBinding;
import com.njfarrell.android.sincerely.login.createaccount.CreateAccountFragment;

public class LoginFragment extends Fragment implements LoginViewModel.LoginListener,
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

    private TextWatcher emailTextChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence sequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            binding.emailInputLayout.setError(null);
            binding.emailInputLayout.setErrorEnabled(false);
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
            binding.passwordInputLayout.setError(null);
            binding.passwordInputLayout.setErrorEnabled(false);
            viewModel.setPassword(editable.toString());
            viewModel.validateLogin();
        }
    };

    @Override
    public void onLoginCompleted() {
        // TODO store user UUID in preferences
        Intent accountActivity = new Intent(getContext(), AccountActivity.class);
        getActivity().startActivity(accountActivity);
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
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.fragment_container, new CreateAccountFragment())
                .addToBackStack(null)
                .commit();
    }
}
