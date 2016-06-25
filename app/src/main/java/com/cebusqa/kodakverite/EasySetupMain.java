package com.cebusqa.kodakverite;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


/**
 * Created by SQA Cebu on 6/8/2016.
 */
public class EasySetupMain extends AppCompatActivity {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easy_setup_main_layout);

        Fragment_ES10_000 frag = new Fragment_ES10_000();
        manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.my_layout, frag, "Arvin");
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount() == 0){
            this.finish();
        }else{
            getFragmentManager().popBackStack();
            //super.onBackPressed();
        }
    }
}
