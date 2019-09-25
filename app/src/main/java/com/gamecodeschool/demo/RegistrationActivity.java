package com.gamecodeschool.demo;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gamecodeschool.demo.REST.ApiClient;
import com.gamecodeschool.demo.REST.ApiInterface;
import com.gamecodeschool.demo.REST.RegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerUser("gayaflo95@gmail.com","123456");
    }

    public void registerUser(String email, String password){
        ApiInterface apiInterface = ApiClient.createRetrofit().create(ApiInterface.class);
        Call<RegistrationResponse>registrationCall = apiInterface.registerUser(email, password);
        registrationCall.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                RegistrationResponse registrationResponse= response.body();
                SharedPreferences mySharedPreps= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor=mySharedPreps.edit();
                editor.putString("AUTH_TOKEN",registrationResponse.getAuthToken());
                editor.putInt("USER_ID",registrationResponse.getUserId());
                editor.apply();;
                Log.d("Registered",response.toString());

            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Log.d("Failed",t.getMessage());

            }
        });
    }
}
