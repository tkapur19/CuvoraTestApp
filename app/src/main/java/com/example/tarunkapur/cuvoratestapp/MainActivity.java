package com.example.tarunkapur.cuvoratestapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.Toast;

import static android.util.Log.*;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ModalClass>> {


    String apiUrl="https://cuvora.com/car/utils/trending/celebs";
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<ModalClass> items=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolBar);
        setSupportActionBar(toolbar);

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        // Setting recyclerView
         recyclerView=(RecyclerView) findViewById(R.id.MainrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        // Initialising LoadManager
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);

        }

        // Implementing LoadManager methods

    @Override
    public Loader<List<ModalClass>> onCreateLoader(int id, Bundle args) {
        Log.i("myMesssage", "onCreateLoader: ");
        // Initialising AsyncLoader and passing url of API
        return new AsyncClass(this,apiUrl);

    }

    @Override
    public void onLoadFinished(Loader<List<ModalClass>> loader, List<ModalClass> data) {

       Log.i("myMesssage", "onLoadFinished: ");

       // Recieved Data and setting data to the RecyclerView
        myAdapter=new MyAdapter(data,getApplicationContext());
        recyclerView.setAdapter(myAdapter);


    }

    @Override
    public void onLoaderReset(Loader<List<ModalClass>> loader) {

    }
}
