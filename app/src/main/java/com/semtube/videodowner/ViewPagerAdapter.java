package com.semtube.videodowner;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT =5;
    private String[] titless;
    Context context;
    private ArrayList<NewsFeeds> feedsList;
    private ArrayList<NewsFeeds> feedsList2;
    private ArrayList<NewsFeeds> top20yerli;
    private ArrayList<NewsFeeds> k21;
    private ArrayList<NewsFeeds> k22;
    private ArrayList<NewsFeeds> k31;
    private ArrayList<NewsFeeds> top100yab;
   private ArrayList<NewsFeeds> yabancipop1;
   private ArrayList<NewsFeeds> top100yerli1; private ArrayList<NewsFeeds> eniyiyab1; private ArrayList<NewsFeeds> yerliyenialbumler1;

    public ViewPagerAdapter(Context c,FragmentManager fm, String[] titles2, ArrayList<NewsFeeds> feedsLis, ArrayList<NewsFeeds> feedsLis2, ArrayList<NewsFeeds> top20yerli, ArrayList<NewsFeeds> top100yab, ArrayList<NewsFeeds> yabancipop, ArrayList<NewsFeeds> top100yerli, ArrayList<NewsFeeds> eniyiyab, ArrayList<NewsFeeds> yerliyenialbumler, ArrayList<NewsFeeds> k21, ArrayList<NewsFeeds> k22, ArrayList<NewsFeeds> k31) {
        super(fm);
        titless=titles2;
        feedsList=feedsLis;
        feedsList2=feedsLis2;
        yabancipop1=yabancipop;
        top100yerli1=top100yerli;
        eniyiyab1=eniyiyab;
        yerliyenialbumler1=yerliyenialbumler;
       this.top20yerli=top20yerli;
       this.top100yab=top100yab;
       this.k21=k21;
       this.k22=k22;
       this.k31=k31;
       context=c;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // Open FragmentTab1.java
            case 0:
                return SampleFragment.newInstance(position,feedsList,1);
            case 1:
                return SampleFragmentIns.newInstance(position,k22,2);
            case 2:
//                ((SampleActivity)context).tbt(titles[3]);
                return SampleFragmentFeys.newInstance(position,top20yerli,3);
            case 3:
                return SampleFragmentObsi.newInstance(position,yabancipop1,4);
            case 4:
                return SampleFragment.newInstance(position,yabancipop1,4);

        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titless[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}