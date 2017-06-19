package com.mile.android.activity;



import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mile.android.fragment.ContactsFragment;
import com.mile.android.R;
import com.mile.android.pojo.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity implements ContactsFragment.OnListFragmentInteractionListener {

    private List<Contact> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setupActionBar();
        contacts = new ArrayList<>();
        /*ContactsFragment contactsFragment = ContactsFragment.newInstance(10) ;

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, contactsFragment,"FMT");
        fragmentTransaction.commit();*/


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

            getContacts();

            finish();



            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getContacts(){
        Fragment fragment = getFragmentManager().findFragmentById(R.id.contact_fragment);
        if (fragment instanceof ContactsFragment){
            contacts = ((ContactsFragment)fragment).getContacts();
            List<String> inGroup = new ArrayList<>();

            for (Contact contact:contacts){
                if (contact.isSelected()){
                    inGroup.add(contact.getPhoneNumber());
                }
            }
            String[] stringArray = inGroup.toArray(new String[0]);;
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",stringArray);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }

    }
    @Override
    public void onListFragmentInteraction(Contact contact) {
        Object o = getFragmentManager().findFragmentById(R.id.contact_fragment);
    }


}
