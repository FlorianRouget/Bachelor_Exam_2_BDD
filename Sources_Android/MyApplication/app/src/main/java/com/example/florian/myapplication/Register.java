package com.example.florian.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static java.security.AccessController.getContext;

/**
 * Created by florian on 22/03/2017.
 */

public class Register extends AppCompatActivity {

    private String apiPath = "http://10.0.2.2/rest/user/add/";
    private ProgressDialog processDialog;
    private JSONArray restulJsonArray;
    private int success = 0;

    EditText EDIT_LOGIN_REGISTER;
    EditText EDIT_PW_REGISTER;
    EditText EDIT_MAIL_REGISTER;
    Button BTN_VALID;
    Button BTN_BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        EDIT_LOGIN_REGISTER = (EditText) findViewById(R.id.Edit_Login_Register);
        EDIT_PW_REGISTER = (EditText) findViewById(R.id.Edit_Password_Register);
        EDIT_MAIL_REGISTER = (EditText) findViewById(R.id.Edit_Mail_Register);
        BTN_VALID = (Button) findViewById(R.id.Btn_Register_Confirm);
        BTN_BACK = (Button) findViewById(R.id.Btn_Register_Back);


        BTN_VALID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ServiceAsyncTask(Register.this, Register.this, EDIT_LOGIN_REGISTER.getText().toString(), EDIT_PW_REGISTER.getText().toString()).execute();
                /*
                Intent data = new Intent();
                data.putExtra("new_login", EDIT_LOGIN_REGISTER.getText().toString());
                data.putExtra("new_pw", EDIT_PW_REGISTER.getText().toString());
                setResult(1, data);
                finish();
                */
            }
        });

        BTN_BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private class ServiceAsyncTask extends AsyncTask<Void, Void, Void> {

        private Context mContext;
        private Activity mActivity;
        String response = "";
        HashMap<String, String> postDataParams;
        String Name;
        String Password;

        public ServiceAsyncTask(Context context, Activity activity, String name, String pass) {
            mContext = context;
            mActivity = activity;
            Name = name;
            Password = pass;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            processDialog = new ProgressDialog(mContext);
            processDialog.setMessage("Please  Wait ...");
            processDialog.setCancelable(false);
            processDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            postDataParams = new HashMap<String, String>();
            postDataParams.put("HTTP_ACCEPT", "application/json");
            postDataParams.put("add_login", Name);
            postDataParams.put("add_pw", Password);

            HttpConnectionService service = new HttpConnectionService();
            response = service.sendRequest(apiPath, postDataParams);
            try {
                success = 1;
                JSONObject resultJsonObject = new JSONObject(response);
                restulJsonArray = resultJsonObject.getJSONArray("output");
            } catch (JSONException e) {
                success = 0;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (processDialog.isShowing()) {
                processDialog.dismiss();
            }

        }
    }

}
