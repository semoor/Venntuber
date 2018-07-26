package com.semtube.videodowner;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SampleFragmentIns extends Fragment{

    private static final String ARG_POSITION = "position";
    private static final String S = "s";
    String sam=null,titl;
    public int position,kmm;
    RequestQueue queue;
    String ara = "tu";
    RecyclerView recyclerView;
    Button b;
    EditText editText;
    ArrayList<NewsFeeds> feedsList = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> axt = new ArrayList<NewsFeeds>();
    static MyRecyclerAdapter adapter;
    public static SampleFragmentIns newInstance(int position, ArrayList<NewsFeeds> feedsLis, Integer km) {
        SampleFragmentIns f = new SampleFragmentIns();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        b.putInt("km", km);

        b.putParcelableArrayList(S,feedsLis);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        kmm = getArguments().getInt("km");

        feedsList =getArguments().getParcelableArrayList(S);
        View rootView = inflater.inflate(R.layout.pageins, container, false);
        b = (Button) rootView.findViewById(R.id.button);
        editText = (EditText) rootView.findViewById(R.id.editText);
        //((SampleActivity) getActivity()).tbt(titl);
       /* recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        adapter = new MyRecyclerAdapter(getActivity(), feedsList,kmm);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        ((SampleActivity) getActivity()).tbt("Mahnı Yüklə və Dinlə");*/
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

try {

ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);

                String text = clipboard.getText().toString();
                editText.setText(text);

                if(text.contains("https://www.instagram.com/p/"))
                    ((SampleActivity) getActivity()).hj2(getActivity(),text);
                else
                    Toast.makeText(getActivity(),"Instagram URL is not valid",Toast.LENGTH_SHORT).show();

}catch (Exception e) {
    Toast.makeText(getActivity(),"Clipboard is null",Toast.LENGTH_SHORT).show();

}
            }
        });
        return rootView;
    }
    FragmentManager mmeenneecceerr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }



    public static String fix(String response) {
        try {
            byte[] u = response.toString().getBytes(
                    "ISO-8859-1");
            response = new String(u, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        return response;
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
                    getActivity().moveTaskToBack(true);

                    return true;

                }

                return false;
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // TODO Add your menu entries here
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                if (launchIntent != null)
                {
                    try
                    {
                        //launchIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        //startActivity(launchIntent);
                        getActivity().startActivityForResult(launchIntent,1);
                    }
                    catch (ActivityNotFoundException ex) // in case Instagram not installed in your device
                    {
                        Toast.makeText(getActivity(),"Instagram app not found",Toast.LENGTH_SHORT).show();
                    }
                }
                break;


        }
        return true;

    }
}