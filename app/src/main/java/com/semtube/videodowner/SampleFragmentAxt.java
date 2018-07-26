package com.semtube.videodowner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.semtube.videodowner.R;
import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class SampleFragmentAxt extends Fragment {

    private static final String ARG_POSITION = "position";
    private static final String S = "s";


    RecyclerView recyclerView;
    ArrayList<NewsFeeds> feedsList = new ArrayList<NewsFeeds>();
    static MyRecyclerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        feedsList =getArguments().getParcelableArrayList(S);

        View rootView = inflater.inflate(R.layout.page, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        adapter = new MyRecyclerAdapter(getActivity(), feedsList,9);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        return rootView;
    }
    @Override
    public void onResume() {

        super.onResume();
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    if(getFragmentManager().getBackStackEntryCount() > 0) {
                        getFragmentManager().popBackStack();
                    }

                    return true;

                }
                ((SampleActivity) getActivity()).tbt("SemTube");

                return false;
            }
        });
    }
}