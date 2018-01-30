package com.pdm.p_62_bd_1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


class MiFragmentPagerAdapter extends FragmentPagerAdapter {
    private final String[] textosTab;

    MiFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        textosTab = context.getResources().getStringArray(R.array.opciones);
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        switch (position) {
            case 0:
                f = new VerFragment();
                break;
            case 1:
                f = new AnadirFragment();
                break;
        }
        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return textosTab[position];
    }

}
