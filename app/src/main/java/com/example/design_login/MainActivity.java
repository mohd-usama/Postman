package com.example.design_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userCode);
        password = findViewById(R.id.userPass);
        login = findViewById(R.id.login_bt);
        signUp = findViewById(R.id.sign_tv);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void postData(String user, String pass)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://beta55.sagarinfotech.com/BrownBirdERPAPP/api/Android/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI apiInterface = retrofit.create(RetrofitAPI.class);
        Call<JsonObject> responseCall = apiInterface.getUserLogin("B12345B8767876ERP565", user, pass, "1.0.02.02.0");

        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call,Response<JsonObject> response){
                if (response.isSuccessful())
                {
                   Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().toString());
                        String status=jsonObject.getString("Status");
                        if(status.equalsIgnoreCase("success"))
                        {

                        }
                        else
                            {
                            Toast.makeText(MainActivity.this, ""+jsonObject.getString("Message"), Toast.LENGTH_SHORT).show();
                            }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Error is found"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}