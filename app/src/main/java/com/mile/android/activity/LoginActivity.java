package com.mile.android.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mile.android.R;
import com.mile.android.api.RideAppRESTFulAPI;
import com.mile.android.entities.Group;
import com.mile.android.entities.User;
import com.mile.android.entities.db.FeedReaderContract;
import com.mile.android.entities.db.RideDbHelper;
import com.mile.android.entities.db.UserTable;
import com.mile.android.net.GsonRequest;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextNickName;
    private EditText editTextPhoneNumber;
    private ImageButton imageButtonLogin;
    RideDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDbHelper = new RideDbHelper(this);
        editTextNickName = (EditText) findViewById(R.id.editTextNickName);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        imageButtonLogin = (ImageButton) findViewById(R.id.imageButtonLogin);

        imageButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token = FirebaseInstanceId.getInstance().getToken();
                User user = new User(token,editTextPhoneNumber.getText().toString(),editTextNickName.getText().toString());

                login(user);
            }
        });

        SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("APP", Context.MODE_PRIVATE);

        Integer userid = sharedPref.getInt(UserTable.UserTableEntry.COLUMN_NAME_USER_ID, 0);
        checkUserRegistry(userid);
    }
    private void checkUserRegistry(int userId){
        if (userId>0){
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }

    }
    private void login(final User user){
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String url = String.format("%s/%s", RideAppRESTFulAPI.getURL(),RideAppRESTFulAPI.ACTION_USER);
        GsonRequest<User> request = new GsonRequest<User>(Request.Method.POST, url,User.class,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User createdUser) {
                        try {

                            if (createdUser!=null){
                                SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("APP", Context.MODE_PRIVATE);
                                //SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putInt(UserTable.UserTableEntry.COLUMN_NAME_USER_ID, createdUser.getUserId().intValue());
                                editor.putString(UserTable.UserTableEntry.COLUMN_NAME_PHONENUM, createdUser.getPhoneNum());
                                editor.putString(UserTable.UserTableEntry.COLUMN_NAME_IDFB, createdUser.getIdFB());
                                editor.putString(UserTable.UserTableEntry.COLUMN_NAME_NICKNAME, createdUser.getNickName());
                                if(createdUser.getMileage()!=null)
                                    editor.putInt(UserTable.UserTableEntry.COLUMN_NAME_MILEAGE, createdUser.getMileage().intValue());
                                if(createdUser.getAddress()!=null)
                                    editor.putString(UserTable.UserTableEntry.COLUMN_NAME_ADDRESS, createdUser.getAddress());
                                editor.commit();

                                checkUserRegistry(createdUser.getUserId());

                            }

                        }catch (Exception err){
                            err.printStackTrace();
                        }
                        finally {

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("INTERNET","onErrorResponse");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();

                params.put("IdFB", user.getIdFB());
                params.put("PhoneNum", user.getPhoneNum());
                params.put("NickName", user.getNickName());
                return params;
            }

        };
        Log.i("SYNC","upload " + user.getNickName())  ;
        request.setShouldCache(false);
        queue.add(request);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
