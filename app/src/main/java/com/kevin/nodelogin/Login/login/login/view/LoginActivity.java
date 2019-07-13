package com.kevin.nodelogin.Login.login.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.kevin.nodelogin.Login.login.MyApplication;
import com.kevin.nodelogin.Login.login.di.component.ApplicationComponent;
import com.kevin.nodelogin.Login.login.di.component.DaggerLoginComponent;
import com.kevin.nodelogin.Login.login.di.component.LoginComponent;
import com.kevin.nodelogin.Login.login.di.module.LoginContextmodule;
import com.kevin.nodelogin.Login.login.di.module.LoginMvpModule;
import com.kevin.nodelogin.Login.login.home.view.Home;
import com.kevin.nodelogin.Login.login.login.presenter.LoginContract;
import com.kevin.nodelogin.Login.login.login.presenter.LoginPresenter;
import com.kevin.nodelogin.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;

public class LoginActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, LoginContract.View {


    @Inject
    LoginPresenter presenter;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.layout_toggle)
    SegmentedGroup layout_toggle;

    @BindView(R.id.login_layout)
    LinearLayout login_lay;
    @BindView(R.id.signup_layout)
    LinearLayout signup_lay;


    @BindView(R.id.login_btn)
    Button login_btn;
    @BindView(R.id.signup_btn)
    Button signup_btn;


    @BindView(R.id.login_username)
    EditText login_username;
    @BindView(R.id.login_password)
    EditText login_password;

    @BindView(R.id.login_username_wrapper)
    TextInputLayout login_username_wrapper;

    @BindView(R.id.login_password_wrapper)
    TextInputLayout login_password_wrapper;


    @BindView(R.id.signup_username)
    EditText signup_username;
    @BindView(R.id.signup_password)
    EditText signup_password;

    @BindView(R.id.signup_username_wrapper)
    TextInputLayout signup_username_wrapper;

    @BindView(R.id.signup_password_wrapper)
    TextInputLayout signup_password_wrapper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ApplicationComponent applicationComponent = MyApplication.get(this)
                .getApplicationComponent();
        LoginComponent loginComponent;

        loginComponent = DaggerLoginComponent.builder()
                .loginMvpModule(new LoginMvpModule(this))
                .loginContextmodule(new LoginContextmodule(this))
                .applicationComponent(applicationComponent)
                .build();

        loginComponent.injectMainActivity(this);

        layout_toggle.setOnCheckedChangeListener(this);


        login_username.addTextChangedListener(new MyTextWatcher(login_username));
        login_password.addTextChangedListener(new MyTextWatcher(login_password));

        login_btn.setOnClickListener(view -> login());
        signup_btn.setOnClickListener(view -> signup());

    }

    private void signup() {

        if (!validateSignupName()) {
            return;
        }
        if (!validateSignupPassword()) {
            return;
        }
        presenter.signup(signup_username.getText().toString(), signup_password.getText().toString());
    }

    private void login() {
        if (!validateLoginName()) {
            return;
        }
        if (!validateLoginPassword()) {
            return;
        }
        presenter.login(login_username.getText().toString(), login_password.getText().toString());
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int layout) {

        switch (layout) {
            case R.id.login: {
                login_lay.setVisibility(View.VISIBLE);
                signup_lay.setVisibility(View.INVISIBLE);
                break;
            }
            case R.id.signup: {
                login_lay.setVisibility(View.INVISIBLE);
                signup_lay.setVisibility(View.VISIBLE);
                break;
            }
            default: {
                login_lay.setVisibility(View.VISIBLE);
                signup_lay.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onLoginSuccess(String message) {
        clearFileds();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent loginIntent = new Intent(this, Home.class);
        startActivity(loginIntent);

    }

    @Override
    public void onlLoginFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        clearFileds();
    }

    @Override
    public void onSignupSuccess(String message) {
        clearFileds();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void OnSignupFailure(String message) {
        clearFileds();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.login_username:
                    validateLoginName();
                    break;

                case R.id.login_password:
                    validateLoginPassword();
                    break;
                case R.id.signup_username:
                    validateSignupName();
                    break;

                case R.id.signup_password:
                    validateSignupPassword();
                    break;
            }
        }
    }

    private boolean validateLoginName() {
        if (login_username.getText().toString().trim().isEmpty()) {
            login_username_wrapper.setError("Username cannot be empty");
            requestFocus(login_username);
            validateLoginPassword();
            return false;
        } else {
            login_username_wrapper.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLoginPassword() {
        if (login_password.getText().toString().trim().isEmpty() || login_password.getText().toString().length() < 6) {
            login_password_wrapper.setError("Password Should be Minimum 6 Characters");
            requestFocus(login_password);
            return false;
        } else {
            login_password_wrapper.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSignupPassword() {
        if (signup_password.getText().toString().trim().isEmpty() || signup_password.getText().toString().length() < 6) {
            signup_password_wrapper.setError("Password Should be Minimum 6 Characters");
            requestFocus(signup_password);
            return false;
        } else {
            signup_password_wrapper.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateSignupName() {
        if (signup_username.getText().toString().trim().isEmpty()) {
            signup_username_wrapper.setError("Username cannot be empty");
            requestFocus(signup_username);
            validateSignupPassword();
            return false;
        } else {
            signup_username_wrapper.setErrorEnabled(false);
        }

        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    public void clearFileds() {
        login_username.setText("");
        login_password.setText("");
        signup_username.setText("");
        signup_password.setText("");
        login_username_wrapper.setErrorEnabled(false);
        login_password_wrapper.setErrorEnabled(false);
        signup_username_wrapper.setErrorEnabled(false);
        signup_password_wrapper.setErrorEnabled(false);
    }

}
