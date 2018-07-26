package com.semtube.videodowner;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SampleFragmentFeys extends Fragment implements  SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener{

    private static final String ARG_POSITION = "position";
    private static final String S = "s";
    String sam=null,titl;
    public int position,kmm;
    RequestQueue queue;
    String ara = "tu";
    WebView webView;
    ImageButton bgi,bi;
    LinearLayout qq, w1, w2, w3, qq1, w11, w22, w33;
    TextView ay1, ay11, ay2, ay22, ay3, ay33, m1, m11;
    EditText editText;
    ArrayList<NewsFeeds> feedsList = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> axt = new ArrayList<NewsFeeds>();
    static MyRecyclerAdapter adapter;
    FrameLayout mPocketBar;
    public static SampleFragmentFeys newInstance(int position, ArrayList<NewsFeeds> feedsLis, Integer km) {
        SampleFragmentFeys f = new SampleFragmentFeys();
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
        View rootView = inflater.inflate(R.layout.pagefeys, container, false);





        webView = (WebView)rootView.findViewById(R.id.WebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.addJavascriptInterface(this, "FBDownloader");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {

                /*if (swipeLayout.isRefreshing())
                {
                    swipeLayout.setRefreshing(false);
                }*/

                webView.loadUrl("javascript:(function() { "
                        + "var el = document.querySelectorAll('div[data-sigil]');"
                        + "for(var i=0;i<el.length; i++)"
                        + "{"
                        + "var sigil = el[i].dataset.sigil;"
                        + "if(sigil.indexOf('inlineVideo') > -1){"
                        + "delete el[i].dataset.sigil;"
                        + "var jsonData = JSON.parse(el[i].dataset.store);"
                        + "el[i].setAttribute('onClick', 'FBDownloader.processVideo(\"'+jsonData['src']+'\");');"
                        + "}" + "}" + "})()");
            }

            @Override
            public void onLoadResource(WebView view, String url)
            {
                webView.loadUrl("javascript:(function prepareVideo() { "
                        + "var el = document.querySelectorAll('div[data-sigil]');"
                        + "for(var i=0;i<el.length; i++)"
                        + "{"
                        + "var sigil = el[i].dataset.sigil;"
                        + "if(sigil.indexOf('inlineVideo') > -1){"
                        + "delete el[i].dataset.sigil;"
                        + "console.log(i);"
                        + "var jsonData = JSON.parse(el[i].dataset.store);"
                        + "el[i].setAttribute('onClick', 'FBDownloader.processVideo(\"'+jsonData['src']+'\",\"'+jsonData['videoID']+'\");');"
                        + "}" + "}" + "})()");
                webView.loadUrl("javascript:( window.onload=prepareVideo;"
                        + ")()");
            }
        });

        CookieSyncManager.createInstance(getActivity());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        CookieSyncManager.getInstance().startSync();

        webView.loadUrl("https://www.facebook.com");

        bgi = (ImageButton) rootView.findViewById(R.id.button2);

        bgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });
        bi= (ImageButton) rootView.findViewById(R.id.button3);

        bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(webView.canGoForward()) {
                    webView.goForward();
                }
            }
        });
        return rootView;
    }

    @JavascriptInterface
    public void processVideo(final String vidData, final String vidID)
    {
        //Toast.makeText(getActivity(), vidData, Toast.LENGTH_LONG).show();
        Random random = new Random();
        final String gh="Facebook_Video_"+String.valueOf(random.nextInt(50000 - 500 + 1) + 500);

        //((SampleActivity)getActivity()).dove(vidID,vidData,false);
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialogins);
        dialog.setTitle("Download from Instagram");
       mPocketBar = (FrameLayout) dialog.findViewById(R.id.yut);


        ay1 = (TextView) dialog.findViewById(R.id.ay1);
        ay11 = (TextView) dialog.findViewById(R.id.ay11);

        w1 = (LinearLayout) dialog.findViewById(R.id.w1);
        w11 = (LinearLayout) dialog.findViewById(R.id.w11);

     //   imageview = (NetworkImageView) dialog.findViewById(R.id.thumbnail);

        ay1.setText("MP4");
        ay11.setText("MP4");
        w1.setBackground(makeSelector());
        w11.setBackground(makeSelector());
        mPocketBar.setVisibility(View.GONE);
        w1.setVisibility(View.VISIBLE);
        w11.setVisibility(View.VISIBLE);
        w1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jcpl(fl,c,pos,kmm);
                dialog.dismiss();
              //  kl();
                Intent intent = new Intent(getActivity(), Vidp.class);
                intent.putExtra("ur", vidData);
                intent.putExtra("ad", gh);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                // finish();
            }
        });
        w11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jcpl(fl,c,pos,kmm);
                dialog.dismiss();
              //  kl();

                ((SampleActivity)getActivity()).dovem(gh+".mp4",  vidData,false);

                // finish();
            }
        });
        dialog.show();
        int w = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, getResources().getDisplayMetrics());
        Window window = dialog.getWindow();
        window.setLayout(w, WindowManager.LayoutParams.WRAP_CONTENT);



        /*try
        {
            String mBaseFolderPath = android.os.Environment
                    .getExternalStorageDirectory()
                    + File.separator
                    + "FacebookVideos" + File.separator;
            if (!new File(mBaseFolderPath).exists())
            {
                new File(mBaseFolderPath).mkdir();
            }
            String mFilePath = "file://" + mBaseFolderPath + "/" + vidID + ".mp4";

            Uri downloadUri = Uri.parse(vidData);
            DownloadManager.Request req = new DownloadManager.Request(downloadUri);
            req.setDestinationUri(Uri.parse(mFilePath));
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            DownloadManager dm = (DownloadManager) getSystemService(getApplicationContext().DOWNLOAD_SERVICE);
            dm.enqueue(req);
            Toast.makeText(this, "Download Started", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Download Failed: " + e.toString(), Toast.LENGTH_LONG).show();
        }*/
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
                    //webView.goBack();

                    return true;

                }

                return false;
            }
        });
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
    public StateListDrawable makeSelector() {
        StateListDrawable res = new StateListDrawable();
        /*res.setExitFadeDuration(400);
        res.setAlpha(45);*/
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.parseColor("#FA78A5")));
        // res.addState(new int[]{},new ColorDrawable(color));
        res.addState(new int[]{}, ContextCompat.getDrawable(getActivity(), R.drawable.gr));
        return res;
    }
}