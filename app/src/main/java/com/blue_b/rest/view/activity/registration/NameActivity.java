package com.blue_b.rest.view.activity.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blue_b.rest.R;
import com.blue_b.rest.functions.RegistrationFunction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NameActivity extends AppCompatActivity {

    TextView nameQuestionTV,errorMessageTV;
    @BindView(R.id.ur_first_name_edt)
    EditText firstNameEdt;
    @BindView(R.id.ur_last_name_edt) EditText lastNameEdt;
    String firstNameStr,lastNameStr,titleStr;
    Button nextBtn;
    private static final String USER_INFO = "UserInfo";
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        statusBarColor();
        ButterKnife.bind(this);
        castActivityComponent();
        actionListenerToNextBtn();
        actionListenerToFirstNameEdtToCheckNumber();
        actionListenerToLastNameEdtToCheckNumber();
    }

    private void finalFunctions() {
        moveToNextActivity();
        resetDefault();
        saveUserInfoInSharedPreference();
    }

    private void moveToNextActivity() {
        Intent intent = new Intent(NameActivity.this,Gender.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }

    private void actionListenerToLastNameEdtToCheckNumber() {
        lastNameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lastNameEdtUseNumberError();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void checkIfLastNameRealName() {
        String lastNameRealStatus = RegistrationFunction.checkIfNameRealName(lastNameStr);
        switch (lastNameRealStatus){
            case "notR":
                errorMessage(getResources().getString(R.string.ur_real_name_error));
                break;
            case "done":
                checkIfUserInsertNumberToFN();
                break;
        }
    }

    private void checkIfUserInsertNumberToFN() {
        String checkFNHaveNumber = RegistrationFunction.checkIfNameHaveNumber(firstNameStr);
        switch (checkFNHaveNumber){
            case "haveN":
                errorMessage(getResources().getString(R.string.ur_number_not_allowed_message_str));
                break;
            case "done":
                checkIfUserInsertNumberToLN();
                break;
        }
    }

    private void checkIfUserInsertNumberToLN() {
        String checkLNHaveNumber = RegistrationFunction.checkIfNameHaveNumber(lastNameStr);
        switch (checkLNHaveNumber){
            case "haveN":
                errorMessage(getResources().getString(R.string.ur_number_not_allowed_message_str));
                break;
            case "done":
                finalFunctions();
        }
    }

    private void checkLengthFN() {
        String length = RegistrationFunction.testLength(firstNameStr);
        switch (length){
            case "small":
                errorMessage(getResources().getString(R.string.ur_first_name_error_to_short_str));
                break;
            case "big":
                errorMessage(getResources().getString(R.string.ur_first_name_error_to_long_str));
                break;
            case "done":
                checkLengthLN();
                break;
        }
    }

    private void checkLengthLN() {
        String length = RegistrationFunction.testLength(lastNameStr);
        switch (length){
            case "small":
                errorMessage(getResources().getString(R.string.ur_last_name_error_to_short_str));
                break;
            case "big":
                errorMessage(getResources().getString(R.string.ur_last_name_error_to_long_str));
                break;
            case "done":
                checkIfFirstNameRealName();
                break;
        }
    }

    private void lastNameEdtUseNumberError() {
        if (lastNameEdt.getText().toString().matches(".*\\d+.*")) {
            errorMessage(getResources().getString(R.string.ur_number_not_allowed_message_str));
        }else
        {
            resetDefault();
            errorMessageTV.setVisibility(View.GONE);
        }
    }

    private void actionListenerToFirstNameEdtToCheckNumber() {
        firstNameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                firstNameEdtUseNumberError();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void checkIfFirstNameRealName() {
        String firstNameRealStatus = RegistrationFunction.checkIfNameRealName(firstNameStr);
        switch (firstNameRealStatus){
            case "notR":
                errorMessage(getResources().getString(R.string.ur_real_name_error));
                break;
            case "done":
                checkIfLastNameRealName();
                break;
        }
    }


    private void firstNameEdtUseNumberError() {
        if (firstNameEdt.getText().toString().matches(".*\\d+.*")) {
            errorMessage(getResources().getString(R.string.ur_number_not_allowed_message_str));
        }else
        {
            resetDefault();
            errorMessageTV.setVisibility(View.GONE);
        }
    }

    private void castActivityComponent() {
        nameQuestionTV =(TextView) findViewById(R.id.ur_name_question_tv);
        errorMessageTV =(TextView) findViewById(R.id.ur_error_message_tv);
        nextBtn = (Button) findViewById(R.id.ur_next_btn);
    }

    private void actionBarBackground() {
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#039776")));
    }

    private void actionListenerToNextBtn() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataInSaidEdt();
                checkFNAndLN();
            }
        });
    }

    private void checkFNAndLN() {
        String status = RegistrationFunction.checkIfFirstNameAndLastNameEmpty(
                firstNameStr,lastNameStr);
        switch (status){
            case "fnAndLnE":
                errorMessage(getResources().getString(R.string.ur_fn_and_ln_is_empty_str));
                break;
            case "fnE":
                errorMessage(getResources().getString(R.string.ur_first_name_error_empty_str));
                break;
            case "lnE":
                errorMessage(getResources().getString(R.string.ur_last_name_error_empty_str));
                break;
            case "done":
                checkLengthFN();
                break;
        }
    }

    private void saveUserInfoInSharedPreference() {
        sharedPreferences = getSharedPreferences(USER_INFO, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("firstName",firstNameEdt.getText().toString());
        editor.putString("lastName",lastNameEdt.getText().toString());
        editor.commit();
    }

    private void resetDefault() {
        changeNameQPaddingToDefault();
        makeErrorMessageGone();
    }

    private void makeErrorMessageGone() {
        errorMessageTV.setVisibility(View.GONE);
    }

    private void changeNameQPaddingToDefault() {
        nameQuestionTV.setPadding(0, 40, 0, 20);
        nameQuestionTV.setTextSize(22);
    }

    private void errorMessage(String errorMessage) {
        changeNameQPadding();
        makeErrorVisibleAndSetErrorMessage(errorMessage);
    }

    private void makeErrorVisibleAndSetErrorMessage(String messageErrorStr) {
        errorMessageTV.setVisibility(View.VISIBLE);
        errorMessageTV.setText(messageErrorStr);
    }

    private void changeNameQPadding() {
        nameQuestionTV.setPadding(0, 1, 0, 20);
        nameQuestionTV.setTextSize(19);
    }

    private String getStatusFirstNameEdt() {
        return RegistrationFunction.testFirstName(firstNameStr);
    }

    private void getDataInSaidEdt() {
        firstNameStr = firstNameEdt.getText().toString();
        lastNameStr = lastNameEdt.getText().toString();
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}