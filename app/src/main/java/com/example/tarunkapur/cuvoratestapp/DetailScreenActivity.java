package com.example.tarunkapur.cuvoratestapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailScreenActivity extends AppCompatActivity {

    ArrayList<info> object=new ArrayList<info>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        // ToolBar Setting
        Toolbar toolbar=(Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (getSupportActionBar()!=null){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);}


        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryStatus));
        }

        // CollapsingToolbar Settings
        CollapsingToolbarLayout toolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        toolbarLayout.setContentScrimColor(ContextCompat.getColor(this,R.color.colorPrimary));

        ImageView carImage=(ImageView) findViewById(R.id.bgheader);

        // Intent Handling which is recieved from previous Activity

        Intent intent = getIntent();

        Bundle args = intent.getExtras();

        // Extracting List of info objects from the selected Recycler element from prev Activity
        Bundle args2=intent.getBundleExtra("BUNDLE");
         object = (ArrayList<info>) args2.getSerializable("ARRAYLIST");
       Log.i("detailActivity", String.valueOf(object.size()));

       // Extracting content for shareText and ImageUrl
        String imgUrl=args.getString("ImageUrl");
        final String shareText=args.getString("shareText");


        // setting image to the imageView using the imageUrl
        Picasso.with(this).load(imgUrl).into(carImage);


        // Setting list of info recieved into the detailed recyclerView
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.detail_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        DetailsAdapter detailsAdapter=new DetailsAdapter(object, this);
        recyclerView.setAdapter(detailsAdapter);


        // Setting and handling OnClick on floating button to share the text via whatsApp
        FloatingActionButton shareButton =(FloatingActionButton) findViewById(R.id.shareFloat);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Handling WhatsApp sharing of shareText
                /* app will check whatsApp is installed or not then it will send ShareText to whatsApp
                otherwise throw exception and make a toast that whatsApp is not installed
                */

                PackageManager pm=getPackageManager();
                try {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");


                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(getBaseContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });








    }
}
