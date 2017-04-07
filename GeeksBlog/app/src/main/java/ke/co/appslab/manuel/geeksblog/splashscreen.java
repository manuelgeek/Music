package ke.co.appslab.manuel.geeksblog;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splashscreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splashscreen.this, MainActivity.class);
                startActivity(i);
                ImageView image = (ImageView)findViewById(R.id.splashimage);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                image.startAnimation(animation);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
