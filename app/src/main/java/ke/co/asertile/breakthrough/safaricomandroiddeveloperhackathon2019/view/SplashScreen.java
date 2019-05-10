package ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.view;

import androidx.appcompat.app.AppCompatActivity;
import ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashScreen extends AppCompatActivity {

    private int SPLASH_TIME = 3000;
    private Animation animFadeIn,animFadeOut;
    private View splash_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splash_view= (View) findViewById(R.id.splash_view);

        //Initialize animation xmls
        animFadeIn= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        animFadeOut= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);

        splash_view.startAnimation(animFadeIn);
        splash_view.setVisibility(View.VISIBLE);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent= new Intent(SplashScreen.this, MainActivity.class);
                    SplashScreen.this.startActivity(intent);

                }
            }
        };
        timer.start();

    }
}
