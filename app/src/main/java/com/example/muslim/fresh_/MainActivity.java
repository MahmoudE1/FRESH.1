package com.example.muslim.fresh_;

import android.content.ClipData;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.example.muslim.fresh_.adapter.TabAdapter;
import com.example.muslim.fresh_.adapter.TabAdapter;
import com.facebook.accountkit.ui.LoginType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public
class MainActivity extends AppCompatActivity {
    ViewPager viewPager1;
    TabLayout tabLayout1;
    TabAdapter adapter;
private DrawerLayout drawerLayout;
private ActionBarDrawerToggle mToggle;
private ImageButton button;
    NavigationView navigationView ;
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

button = (ImageButton) findViewById ( R.id.imageView2 ) ;








        tabLayout1 = findViewById ( R.id.tablayout1 );

        viewPager1 = findViewById ( R.id.viewpager1 );

        tabLayout1.setTabGravity ( TabLayout.GRAVITY_FILL );

        tabLayout1.addTab ( tabLayout1.newTab ( ).setText ( "توصيل" ) );
        tabLayout1.addTab ( tabLayout1.newTab ( ).setText ( "عروض" ) );
        tabLayout1.addTab ( tabLayout1.newTab ( ).setText ( "اي حاجه" ) );


        adapter =new   TabAdapter ( getSupportFragmentManager ( ), tabLayout1.getTabCount ( ) );
        viewPager1.setAdapter ( adapter );
        viewPager1.setOffscreenPageLimit ( 3 );
        viewPager1.addOnPageChangeListener ( new TabLayout.TabLayoutOnPageChangeListener ( tabLayout1 ) );
        tabLayout1.addOnTabSelectedListener ( new TabLayout.OnTabSelectedListener ( ) {

            @Override
            public
            void onTabSelected(TabLayout.Tab tab) {
                viewPager1.setCurrentItem ( tab.getPosition ( ) );

            }

            @Override
            public
            void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public
            void onTabReselected(TabLayout.Tab tab) {
            }
        } );
        button.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public
            void onClick(View v) {

                drawerLayout= (DrawerLayout)findViewById ( R.id.DrawerLayout ) ;
                navigationView = (NavigationView) findViewById (R.id.navigation  );



              if(drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(Gravity.START);
                else drawerLayout.closeDrawer  (Gravity.END);




            }


        });
    }
}

