package com.blue_b.rest.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.blue_b.rest.functions.RetrofitFunctions;
import com.blue_b.rest.model.UserInfo;
import com.blue_b.rest.presnter.JsonPlaceHolderApi;
import com.blue_b.rest.presnter.TestLogin;
import com.blue_b.rest.sharedPreferences.LoginInfo;
import com.blue_b.rest.view.activity.registration.NameActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.blue_b.rest.R;
import com.blue_b.rest.view.activity.mainScreem.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.blue_b.rest.presenter.LoginAndUpdateProfile.whenLoginCompleteSuccess;
import static com.blue_b.rest.view.activity.mainScreem.MainActivity.setLocale;

import java.util.List;

public class Login extends AppCompatActivity {

    RelativeLayout g,create_account,login;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    String[] titleArray,desArray,optionalArray,optionalAndDesArray;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    LoginButton loginButtonFB;
    EditText email_edt,pass_edt;

    String from;
    private CallbackManager callbackManager;

    JsonPlaceHolderApi jsonPlaceHolderApi;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale(this);
        setContentView(R.layout.activity_login);

        statusBarColor();
        inti();

        //setBackgroundColor();

//        Log.i("TAG:Token: ",getTokenFromSP(this));
        actionListenerToLogin();
        //actionListenerToFB();
        actionListenerToG();
        actionListenerToCreateAccount();
        //checkIfUserComFromSplashScreenOrFromLoginButton();
    }

    private void actionListenerToLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intiRet();

                //whenLoginCompleteSuccess("raza@gmail.com","123456");
            }
        });
    }

    private void actionListenerToCreateAccount() {
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NameActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
            }
        });
    }

    private void intiRet() {
        retrofit = com.blue_b.rest.presnter.Login.LoginFun();


    }

    private void loginTest() {
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        TestLogin testLogin = new TestLogin("raza@gmail.com","123456");
        Call<String> call = jsonPlaceHolderApi.testLoginRetrofit(testLogin,"application/json","application/json");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful())
                { return;
                }
                Log.w("TAG", "Response " + response);


            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("TAG Error",t.getMessage());
            }
        });
    }

    private void registerLogin() {
        callbackManager = CallbackManager.Factory.create();
        loginButtonFB.setPermissions("email", "public_profile");

        loginButtonFB.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //String usrPhoto = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?type=normal";
                        //Log.i("TAG",usrPhoto);
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken != null) {

                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(final JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("first_name") + " "+ object.getString("last_name");
                    String email = object.getString("email");
                    String photo = "https://graph.facebook.com/" + object.getString("id") + "/picture?type=normal";

                    updateUserInfoOnServer(name,photo);
                    LoginInfo.saveLoginInSP(getApplicationContext(),sharedPreferences,editor,"1");
                    LoginInfo.saveLoginInfoInSP(getApplicationContext(),name,email,photo,"fb");
                    moveToMainScreen();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("TAG ERORR", ": "+e.toString());
                }
            }
        });


        Bundle parmeters = new Bundle();
        parmeters.putString("fields", "first_name,last_name,email,id,birthday,gender,location");
        graphRequest.setParameters(parmeters);
        graphRequest.executeAsync();
    }


    private String getInfoFromCat() {
        from = getIntent().getStringExtra("from");
        return from;
    }

    private void actionListenerToG() {
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createReq();
                signIn();
            }
        });
    }

    private void createReq() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void moveToMainScreen() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
        finish();
    }

    private void inti() {
        //login = (Button) findViewById(R.id.login);
        loginButtonFB = findViewById(R.id.login_button);
        g = (RelativeLayout) findViewById(R.id.g);
        create_account = (RelativeLayout) findViewById(R.id.create_account);
        login = (RelativeLayout) findViewById(R.id.login);
        email_edt = (EditText) findViewById(R.id.email_edt);
        pass_edt = (EditText) findViewById(R.id.password_edt);
    }

    private void statusBarColor() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void moveBack() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                String name = account.getGivenName() + " "+ account.getFamilyName();
                String email = account.getEmail();
                String photo = String.valueOf(account.getPhotoUrl());
                updateUserInfoOnServer(name,photo);

                LoginInfo.saveLoginInSP(getApplicationContext(),sharedPreferences,editor,"1");
                LoginInfo.saveLoginInfoInSP(getApplicationContext(),name,email,photo,"google");
                moveToMainScreen();
            }
            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }

    private void updateUserInfoOnServer(String name, String photo) {
        UserInfo userInfo = new UserInfo(LoginInfo.getUser_IDFromSP(getApplicationContext()), LoginInfo.getTokenFromSP(getApplicationContext()),name,photo);
        Call<UserInfo> call = jsonPlaceHolderApi.replaceUserInfo(LoginInfo.getUser_IDFromSP(getApplicationContext()),userInfo);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
            }
            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
            }
        });
    }
}