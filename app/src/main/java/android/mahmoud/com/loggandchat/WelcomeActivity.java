package android.mahmoud.com.loggandchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(6000);     //used to sleep 3s at starting app
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent mainIntent=new Intent(WelcomeActivity.this,MainActivity.class);  // use to switchfrom welcome screen to second screen
                    startActivity(mainIntent);   // use to starting switch process

                }

            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
