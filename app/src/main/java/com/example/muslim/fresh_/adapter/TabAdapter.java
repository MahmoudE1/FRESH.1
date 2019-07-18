package com.example.muslim.fresh_.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.muslim.fresh_.fragment.Home;
import com.example.muslim.fresh_.fragment.more;
import com.example.muslim.fresh_.fragment.offer;
import com.example.muslim.fresh_.fragment.Home;
import com.example.muslim.fresh_.fragment.more;
import com.example.muslim.fresh_.fragment.offer;


public class TabAdapter extends FragmentPagerAdapter {
    int numoftabs;

    public TabAdapter(FragmentManager fm, int  mnumoftabs ) {
        super(fm);
        this.numoftabs = mnumoftabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Home tab1 = new Home();
                return tab1;

            case 1:
                offer tab2 = new offer ();
                return tab2;
            case 2:
                more tab3 = new more ();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}