package com.example.florian.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String apiPath = "http://10.0.2.2/rest/user/";
    private ProgressDialog processDialog;
    private JSONArray restulJsonArray;
    private int success = 0;
    boolean lock;
    DB_Tables.User Current;

    EditText EDIT_LOGIN;
    EditText EDIT_PW;
    Button BTN_LOGIN;
    Button BTN_REGISTER;
    private final int RESULT_SELECTION = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EDIT_LOGIN = (EditText) findViewById(R.id.Edit_Login);
        EDIT_PW = (EditText) findViewById(R.id.Edit_Password);
        BTN_LOGIN = (Button) findViewById(R.id.Btn_Login);
        BTN_REGISTER = (Button) findViewById(R.id.Btn_Register);
        Current = new DB_Tables.User();
        lock = true;

        BTN_LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ServiceAsyncTask(MainActivity.this, MainActivity.this, EDIT_LOGIN.getText().toString(), EDIT_PW.getText().toString()).execute();
                /*
                while(lock){
                }
                if (success == 2)
                    goToNextActivity(0);
                lock = true;
                */
            }
        });

        BTN_REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity(1);
            }
        });

    }


    public void goToNextActivity(int index){
        Intent intent;
        switch (index){
            case 0:
                intent = new Intent(MainActivity.this, Projects.class);
                intent.putExtra("id" , Current.getIdUser());
                intent.putExtra("login" , Current.getLogin());
                intent.putExtra("password", Current.getPassword());
                startActivityForResult(intent, RESULT_SELECTION);
                break;
            case 1:
                intent = new Intent(MainActivity.this, Register.class);
                startActivityForResult(intent, RESULT_SELECTION);
                break;
            default:
                Toast.makeText(this, "Cannot access another activity", Toast.LENGTH_SHORT).show();
                break;
        }
        //
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Toast.makeText(this, data.getCharSequenceExtra("result"), Toast.LENGTH_SHORT).show();
        if (resultCode == 1) {
            EDIT_LOGIN.setText(data.getStringExtra("new_login"));
            EDIT_PW.setText(data.getStringExtra("new_pw"));

        }
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
            Name = EDIT_LOGIN.getText().toString();
            Password = EDIT_PW.getText().toString();
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

            Log.d("debug","entering PostExecute");

            if (processDialog.isShowing()) {
                processDialog.dismiss();
            }

            if (success == 1) {
                Log.d("debug","success == 1");
                if (null != restulJsonArray) {
                    success = 2;
                    Log.d("debug","success == 1 et restul not null");
                    for (int i = 0; i < restulJsonArray.length(); i++) {
                        try {
                            JSONObject jason = restulJsonArray.getJSONObject(i);
                            Log.d("debug",jason.getString("PASSWORD"));
                            Log.d("debug",Password);

                            if(jason.getString("LOGIN").trim().equalsIgnoreCase(Name.trim())){
                                if(jason.getString("PASSWORD").trim().equalsIgnoreCase(Password.trim())) {
                                    Log.d("Debug", "Matching user detected");
                                    Current.setIdUser(jason.getInt("ID_USER"));
                                    Current.setLogin(jason.getString("LOGIN"));
                                    Current.setPassword(jason.getString("PASSWORD"));

                                    goToNextActivity(0);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Restul is empty !", Toast.LENGTH_SHORT).show();
                    Log.d("debug","Restul is empty !");
                }
            }else{
                Toast.makeText(MainActivity.this, "success is not 1", Toast.LENGTH_SHORT).show();
                Log.d("debug","success is not 1");
            }
            lock = false;

        }
    }

}
