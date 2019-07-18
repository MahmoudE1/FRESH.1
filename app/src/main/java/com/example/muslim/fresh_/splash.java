package com.example.muslim.fresh_;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public
class splash extends AppCompatActivity{

    ConstraintLayout constraintLayout;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.splash );
        constraintLayout = (ConstraintLayout) findViewById ( R.id.logo );

        Animation mm = AnimationUtils.loadAnimation ( this, R.anim.mysplash );

        constraintLayout.setAnimation ( mm );


        final Intent  mmmm = new Intent( this, login_one.class );

        Thread timer = new Thread ( ) {
            public void run (){
                try {
                    sleep (10000 );

                } catch (InterruptedException e) {
                    e.printStackTrace ( );
                }
                finally {
                   startActivity ( mmmm );
                   finish ();
                }
            }

        };
        timer.start ();
    }


}
