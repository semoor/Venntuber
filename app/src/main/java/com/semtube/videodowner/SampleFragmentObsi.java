package com.semtube.videodowner;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.content.Context.CLIPBOARD_SERVICE;

public class SampleFragmentObsi extends Fragment implements  SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener{
    ListView listView;
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
    ArrayList<String> lf = new ArrayList<String>();
    ArrayList<NewsFeeds> axt = new ArrayList<NewsFeeds>();
    static MyRecyclerAdapter adapter;
    public static SampleFragmentObsi newInstance(int position, ArrayList<NewsFeeds> feedsLis, Integer km) {
        SampleFragmentObsi f = new SampleFragmentObsi();
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
        View rootView = inflater.inflate(R.layout.pageobs, container, false);
        b = (Button) rootView.findViewById(R.id.button);
        editText = (EditText) rootView.findViewById(R.id.editText);
        listView=rootView.findViewById(R.id.lv);
        lv();
        //((SampleActivity) getActivity()).tbt(titl);
       /* recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        adapter = new MyRecyclerAdapter(getActivity(), feedsList,kmm);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        ((SampleActivity) getActivity()).tbt("Mahnı Yüklə və Dinlə");*/
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
               // clipboard.setText("Text to copy");
                //String ff=clipboard.getText().toString();
                try{
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);

                    String text = clipboard.getText().toString();
                    editText.setText(text);
                    if(text.contains("://www."))
                        ((SampleActivity) getActivity()).hj2(getActivity(),text);
                    else
                        Toast.makeText(getActivity(),"URL is not valid",Toast.LENGTH_SHORT).show();


                }catch (Exception e) {
                    Toast.makeText(getActivity(),"Clipboard is null",Toast.LENGTH_SHORT).show();

                }



            }
        });
        return rootView;





    }
    FragmentManager mmeenneecceerr;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search All Videos");
        int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
        ImageView v = (ImageView) searchView.findViewById(searchImgId);
        int padding_in_dp = 50;
        final float scale = getResources().getDisplayMetrics().density;
        int p = (int) (padding_in_dp * scale + 0.5f);
        //v.setPadding(p,p,p,p);
        v.setAdjustViewBounds(true);
        v.setMaxHeight(p);
        v.setMaxWidth(p);
        v.setImageResource(R.drawable.arasearchh);

        searchView.setOnQueryTextListener(this);

        super.onPrepareOptionsMenu(menu);
        super.onCreateOptionsMenu(menu, inflater);
        super.onCreateOptionsMenu(menu, inflater);
    }




    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        //
        String  pl = query;

        if (pl == null)
            Toast.makeText(getActivity(), "bişeyler yazın...", Toast.LENGTH_SHORT).show();
        else {
            try {
                sam = URLEncoder.encode(pl, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ((SampleActivity) getActivity()).tbt(pl);
            ytdan(sam);

        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {

            return false;
        }



        return false;
    }

    public void ytdan(String ul) {
        axt.clear();
        String uli="https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/search.php?q="+ul;
        queue = NetworkController.getInstance(getActivity()).getRequestQueue();
        StringRequest postRequest = new StringRequest(Request.Method.GET,uli,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);



                            Elements nm = document.select(".moviea");
                            Elements im = document.select(".downloadimg[src]");
                            Elements ln =document.select(".moviea[data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+"]");
                            Elements hu;
                            String skanal;
                            hu = document.select(".pull-left.publisher.pub2 a");
                            Elements vv = document.select(".label-views");
                            Elements dr = document.select(".duration");

                            for (int i = 0; i < 20; i++) {

                                skanal=hu.get(i).text();

                                NewsFeeds feeds = new NewsFeeds(fix(nm.get(i).text()),   fix(skanal),vv.get(i).text(),im.get(i).attr("src"),ln.get(i).attr("data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+""),dr.get(i).text(), 0,R.drawable.asd2,false,9);

                                axt.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }finally {
                            SampleFragmentAxt f = new SampleFragmentAxt();
                            mmeenneecceerr =  getActivity().getSupportFragmentManager();
                            Bundle b = new Bundle();
                            b.putString("axtarismetni",sam);
                            b.putParcelableArrayList(S,axt);
                            f.setArguments(b);
                            FragmentTransaction transaction = mmeenneecceerr.beginTransaction();
                            transaction.replace(R.id.fm, f);
                            transaction.addToBackStack(null);
                            transaction.commit();

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh",error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }
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

    public void lv(){

         queue = NetworkController.getInstance(getActivity()).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://qdownloader.net/supported-sites",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Document document = Jsoup.parse(response);

                        Elements elements = document.select("li a");



                        for (int i = 0; i < elements.size(); i++) {

                            lf.add(elements.get(i).text());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.lisvi, lf);
                        listView.setAdapter(adapter);


                       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.hjhfygdlterwq, lista);
                        listView.setAdapter(adapter);*/


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("Hata: " + error.getMessage());
            }
        });

        queue.add(stringRequest);

    }
}