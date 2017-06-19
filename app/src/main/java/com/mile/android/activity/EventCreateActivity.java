package com.mile.android.activity;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mile.android.R;
import com.mile.android.api.RideAppRESTFulAPI;
import com.mile.android.entities.Group;
import com.mile.android.entities.User;
import com.mile.android.entities.db.UserTable;
import com.mile.android.net.GsonRequest;
import com.mile.android.pojo.Contact;
import com.mile.android.utils.DateUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.Calendar.HOUR_OF_DAY;

public class EventCreateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button timeDialog;
    private Button dateDialog;
    private Button buttonInviteUsers;
    private ImageButton imageButtonSend;
    private EditText editTextEventName;
    private EditText editTextPlace;
    private String[] contacts;

    Calendar now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);
        setupActionBar();

        timeDialog = (Button)findViewById(R.id.buttonTime);
        dateDialog = (Button)findViewById(R.id.buttonDate);
        buttonInviteUsers = (Button)findViewById(R.id.buttonInviteUsers);
        imageButtonSend = (ImageButton)findViewById(R.id.imageButtonSend);
        editTextEventName = (EditText)findViewById(R.id.editTextEventName);
        editTextPlace = (EditText)findViewById(R.id.editTextPlace);

        now = Calendar.getInstance();
        setDialogs();
        buttonInviteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventCreateActivity.this, ContactActivity.class);
                startActivityForResult(i, 1);
            }
        });
        imageButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = editTextEventName.getText().toString();
                String place = editTextPlace.getText().toString();
                SharedPreferences sharedPref = EventCreateActivity.this.getSharedPreferences("APP", Context.MODE_PRIVATE);

                Group group = new Group();
                group.setAdminName(sharedPref.getString(UserTable.UserTableEntry.COLUMN_NAME_NICKNAME, ""));
                group.setExpireDate(DateUtil.formatter.format(now.getTime()));
                group.setTo(place);
                group.setType(0);
                group.setName(eventName);
                createEvent(group);

            }
        });


    }
    private void setDialogs(){
        dateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        EventCreateActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        timeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hour = now.get(HOUR_OF_DAY);
                TimePickerDialog dpd = TimePickerDialog.newInstance(
                        EventCreateActivity.this,
                        now.get(HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                dpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });
    }
    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {

        now.set(Calendar.YEAR,i);
        now.set(Calendar.MONTH,i1);
        now.set(Calendar.DAY_OF_MONTH,i2);
        dateDialog.setText(String.format("%s/%s/%s",i2+"",(i1 + 1)+"",i + ""));

    }

    @Override
    public void onTimeSet(TimePickerDialog timePickerDialog, int i, int i1, int i2) {
        now.set(Calendar.HOUR_OF_DAY,i);
        now.set(Calendar.MINUTE,i1);

        timeDialog.setText(String.format("%s:%s",i+"",i1+""));
    }


    private void createEvent(final Group group){
        RequestQueue queue = Volley.newRequestQueue(EventCreateActivity.this);
        String url = String.format("%s/%s", RideAppRESTFulAPI.getURL(),RideAppRESTFulAPI.ACTION_GROUP);
        GsonRequest<Group> request = new GsonRequest<Group>(Request.Method.POST, url,Group.class,
                new Response.Listener<Group>() {
                    @Override
                    public void onResponse(Group newGroup) {
                        try {
                            if (newGroup!=null){

                                addContacts(contacts,newGroup.getGroupId().intValue());

                                //finish();
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

                params.put("AdminName", group.getAdminName());
                params.put("ExpireDate", group.getExpireDate());
                params.put("To", group.getTo());
                params.put("Type", group.getType() + "");
                params.put("Name", group.getName());

                return params;
            }

        };
        Log.i("SYNC","upload " + group.getName())  ;
        request.setShouldCache(false);
        queue.add(request);

    }

    private void addContacts(final String[] contacts,final int groupId){
        RequestQueue queue = Volley.newRequestQueue(EventCreateActivity.this);
        String url = String.format("%s/%s/%s", RideAppRESTFulAPI.getURL(),RideAppRESTFulAPI.ACTION_GROUP_USERS,groupId + "");
        GsonRequest<Integer> request = new GsonRequest<Integer>(Request.Method.POST, url,Integer.class,
                new Response.Listener<Integer>() {
                    @Override
                    public void onResponse(Integer rows) {
                        try {
                            if (rows!=null){
                                finish();
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
                try {
                    JSONArray list = new JSONArray(contacts);
                    params.put("PhoneNumbers", list.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                return params;
            }

        };
        Log.i("SYNC","upload " + contacts)  ;
        request.setShouldCache(false);
        queue.add(request);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                contacts = data.getStringArrayExtra("result");
                SharedPreferences sharedPref = EventCreateActivity.this.getSharedPreferences("APP", Context.MODE_PRIVATE);
                contacts  = ArrayUtils.addAll(contacts,new String[]{sharedPref.getString(UserTable.UserTableEntry.COLUMN_NAME_PHONENUM, "")});
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult


}
