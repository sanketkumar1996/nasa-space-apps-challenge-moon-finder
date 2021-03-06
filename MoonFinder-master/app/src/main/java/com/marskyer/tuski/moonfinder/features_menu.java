package com.marskyer.tuski.moonfinder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class features_menu extends Activity {
Button learn,video;
    ImageButton story;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features_menu);

learn = (Button) findViewById(R.id.btn_pic);
story= (ImageButton) findViewById(R.id.story_btn);
        video = (Button) findViewById(R.id.btn_video);


        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(features_menu.this, learn.class);
                startActivity(intent);
            }
        });



        story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(features_menu.this, story_list.class);
                startActivity(intent);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=hWkKSkl3gkU")));
            }
        });







    }

    public void startGalleryActivity(){
        Intent intent = new Intent(features_menu.this, GalleryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);

    }





}
