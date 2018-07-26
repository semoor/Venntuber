package com.semtube.videodowner;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.toolbox.NetworkImageView;
import com.liulishuo.okdownload.core.Util;
import com.semtube.videodowner.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.jean.jcplayer.JcAudio;
import com.example.jean.jcplayer.JcPlayerView;
import com.example.jean.jcplayer.JcStatus;
import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.kobakei.ratethisapp.RateThisApp;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.UnifiedListenerManager;
import com.semtube.videodowner.util.UNotificationEsas;
import com.semtube.videodowner.util.UYukle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.getIntentOld;

public class SampleActivity extends AppCompatActivity implements JcPlayerView.OnInvalidPathListener, JcPlayerView.JcPlayerViewStatusListener, EasyPermissions.PermissionCallbacks {
    RequestQueue queue;
   public static  Integer say=0;
   public static Intent inten;
    String w720, w360, webm, p3;
    private FFmpeg ffmpeg;
    String hek="mur_ask";
    String ara="tu";
    String hem="progco";
    String ht="e/172655";
    ArrayList<NewsFeeds> feedsList1 = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> feedsList2 = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> top20yerli = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> top100yabanci = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> yabanci2018 = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> top100yerli = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> eniyiyabanci = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> yerliyenialbumlar = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> k21 = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> k22 = new ArrayList<NewsFeeds>();
    ArrayList<NewsFeeds> k31 = new ArrayList<NewsFeeds>();


    static ArrayList<Nfc> nl = new ArrayList<Nfc>();
    Integer km = 0, z = 1000;
    Netwcont qr;
    String ol="P";
    String mub="M"+ol+"3";
    Boolean id1=false,id2=false,id3=false,id4=false,id5=false,id6=false,id7=false,id8=false,id21=false,id22=false,id31=false,hamsi=false;
    String tit="Loading...";
    private InterstitialAd mInterstitialAd;
    LinearLayout qq, w1, w2, w3, qq1, w11, w22, w33;
    String inim,invi,invisi;
    JcPlayerView jcplayerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
   TextView content, title,ce,d;
    NetworkImageView imageview;
    ImageView pl;
    static NotificationManager managera;
    static Integer ida;
    static NotificationCompat.Builder buildere;
    ArrayList<JcAudio> jcAudios1 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios2 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios3 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios4 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios5 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios6 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios7 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios8 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios9 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios21 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios22 = new ArrayList<>();
    ArrayList<JcAudio> jcAudios31 = new ArrayList<>();
    private ListView mDrawerList;
    private DownloadTask gorev;
    private CancelReceiver legv;
    private UNotificationEsas dinleyici;
    ViewPager pager;
    private String[] titles = new String[]{"Trending","Instagram", "Facebook" , "Other Sites","Top Music", "Top 100\nXARİCİ"
           , "Top 100\nTÜRK", "En YAXŞI\nXARİCİ", "YENİ TÜRK\nMAHNILARI", "Trend\nvİdeolar","MARŞUT\nMAHNILARI"};
    private Toolbar toolbar;
    String jp="p3";
    ArrayList<String> tm = new ArrayList<String>();
    SlidingTabLayout slidingTabLayout;
    FrameLayout mPocketBar;
    TextView ay1, ay11, ay2, ay22, ay3, ay33, m1, m11;
    String izle = "watch",muzka="m"+jp;
    private AdView mAdView;

    public static final String C= "com.semtube.videodowner.SampleActivity";
    public static Boolean qur=false;
    static Handler handler = new Handler();

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("");

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }
            @Override
            public void onAdClosed() {

                mInterstitialAd = newInterstitialAd();
                loadInterstitial();

            }

        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
           // mInterstitialAd.show();
        } else {
            mInterstitialAd = newInterstitialAd();
            loadInterstitial();

        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .addTestDevice("12189A14DC002169B13523D633BEAF7F")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }
    public void kl()
    {
        Boolean z=false;
        try {
            jcplayerView.setVisibility(View.GONE);
            jcplayerView.pause();
        }catch (Exception e){
            z=true;
        }
        if(z){
            try {
                jcplayerView.kill();
            }catch (Exception e){
                z=false;
            }
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        // ButterKnife.inject(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
        }
        getSupportActionBar().setTitle("SemTube");
        getSupportActionBar().getThemedContext();
        toolbar.setTitleTextColor(0xFFFFFFFF);
        jcplayerView = (JcPlayerView) findViewById(R.id.jcplayer);
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
      //  downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
     //  testo();

      //  mView = new CatLoadingView();

        //ytdan(true, "https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/channel.php?id=UCR5wZcXtOUka8jTA57flzMg");
        yabpop(false, "https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/cat.php?id=10");


        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);
        String[] values = new String[]{
                "Follow in Instagram",
                "Like in Facebook",
                "Rate App",
                "Share App"
        };
        try {

            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            qr = new Netwcont();
            registerReceiver(qr, filter);
        }catch (Exception e){

        }

        LottieAnimationView animationView = findViewById(R.id.animation_view);
        TextView y=findViewById(R.id.textView3);
        //Random random = new Random();
        //Integer i=random.nextInt(tit.length - 0 + 1) + 0;
        y.setText(tit);
        animationView.setAnimation(R.raw.pink_drink_machine);
        animationView.playAnimation();



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        Uri uri = Uri.parse("https://www.instagram.com/_u/sey"+hek+"aroff/");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                        likeIng.setPackage("com.instagram.android");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://instagram.com/sey"+hek+"aroff/")));
                        }
                        break;
                    case 1:
                        try {
                            getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                           startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://pag"+ht+"4884274516")));
                        } catch (Exception e) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/az"+hem+"mpany/")));
                        }

                        break;
                    case 2:
                        Uri urim = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, urim);
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" +  getApplicationContext().getPackageName())));
                        }
                        break;
                    case 3:
                      /*  startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                      */
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, "http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                        i.putExtra(Intent.EXTRA_TEXT, "Uygulamayı Paylaşın");
                        startActivity(Intent.createChooser(i, "http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName()));
                      break;
                }

            }
        });

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                    izin();

            }
        }, 1000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void ytdan(final Boolean kanal, String uli) {


        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        StringRequest postRequest = new StringRequest(Request.Method.GET, uli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".moviea");
                            Elements im = document.select(".downloadimg[src]");
                            Elements ln = document.select(".moviea[data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+"]");
                            //  Elements hu = document.select(".pull-left.publisher.pub2 a");
                            Elements hu;
                            String skanal;
                            if (kanal) {
                                hu = document.select(".popular");
                            } else
                                hu = document.select(".pull-left.publisher.pub2 a");
                            Elements vv = document.select(".label-views");
                            Elements dr = document.select(".duration");

                            for (int i = 0; i < nm.size(); i++) {
                                if (kanal)
                                    skanal = hu.text();
                                else
                                    skanal = hu.get(i).text();

                                NewsFeeds feeds = new NewsFeeds(fix(nm.get(i).text()), fix(skanal), vv.get(i).text(), im.get(i).attr("src"), ln.get(i).attr("data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+""), dr.get(i).text(), 0, R.drawable.asd2, false,2);

                                feedsList2.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                             top20yer(false, "https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/search.php?q=netd");


                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }

    public void lk(Context c){
        Log.e("geldi","brattt");

    }
    public void top20yer(final Boolean kanal, String uli) {
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, uli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".moviea");
                            Elements im = document.select(".downloadimg[src]");
                            Elements ln = document.select(".moviea[data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+"]");
                            Elements hu;
                            String skanal;
                            if (kanal) {
                                hu = document.select(".popular");
                            } else
                                hu = document.select(".pull-left.publisher.pub2 a");
                            Elements vv = document.select(".label-views");
                            Elements dr = document.select(".duration");

                            for (int i = 0; i < nm.size(); i++) {
                                if (kanal)
                                    skanal = hu.text();
                                else
                                    skanal = hu.get(i).text();

                                NewsFeeds feeds = new NewsFeeds(fix(nm.get(i).text()), fix(skanal), vv.get(i).text(), im.get(i).attr("src"), ln.get(i).attr("data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+""), dr.get(i).text(), 0, R.drawable.asd2, false,3);

                                top20yerli.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {

                            eniyiyab(false, "https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/cat.php?id=10&sort=p");


                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }

    public void eniyiyab(final Boolean kanal, String uli) {
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, uli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);

                            Elements nm = document.select(".moviea");
                            Elements im = document.select(".downloadimg[src]");
                            Elements ln = document.select(".moviea[data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+"]");
                            Elements hu;
                            String skanal;
                            if (kanal) {
                                hu = document.select(".popular");
                            } else
                                hu = document.select(".pull-left.publisher.pub2 a");
                            Elements vv = document.select(".label-views");
                            Elements dr = document.select(".duration");

                            for (int i = 0; i < nm.size(); i++) {
                                if (kanal)
                                    skanal = hu.text();
                                else
                                    skanal = hu.get(i).text();

                                NewsFeeds feeds = new NewsFeeds(fix(nm.get(i).text()), fix(skanal), vv.get(i).text(), im.get(i).attr("src"), ln.get(i).attr("data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+""), dr.get(i).text(), 0, R.drawable.asd2, false,7);

                                eniyiyabanci.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {

                            yabpop(false, "https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/cat.php?id=10");


                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }

    public void yabpop(final Boolean kanal, String uli) {
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, uli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".moviea");
                            Elements im = document.select(".downloadimg[src]");
                            Elements ln = document.select(".moviea[data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+"]");
                            Elements hu;
                            String skanal;
                            if (kanal) {
                                hu = document.select(".popular");
                            } else
                                hu = document.select(".pull-left.publisher.pub2 a");
                            Elements vv = document.select(".label-views");
                            Elements dr = document.select(".duration");

                            for (int i = 0; i < nm.size(); i++) {
                                if (kanal)
                                    skanal = hu.text();
                                else
                                    skanal = hu.get(i).text();

                                NewsFeeds feeds = new NewsFeeds(fix(nm.get(i).text()), fix(skanal), vv.get(i).text(), im.get(i).attr("src"), ln.get(i).attr("data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+""), dr.get(i).text(), 0, R.drawable.asd2, false,5);

                                yabanci2018.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            //top100yab();

                            trenn(false, "https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/trends.php?geo="+ Locale.getDefault().getCountry());

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }

    public void top100yab() {
        final ArrayList<String> t1 = new ArrayList<>();
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, "http://mp"+getResources().getString(R.string.ic)+"rco.info/yabanci-"+muzka+"-indir-1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".Sag_Blok2_Icerik .Sag_Blok2_Mp3");

                            Elements ln = document.select(".Sag_Blok2_Icerik .Sag_Blok2_Mp3 a");


                            for (int i = 0; i < ln.size(); i++) {
                                if (i % 2 == 1)
                                    t1.add("http://mp"+getResources().getString(R.string.ic)+"rco.info/" + ln.get(i).attr("href"));
                            }


                            for (int i = 0; i < nm.size(); i++) {

                                String lnk;
                                Random random = new Random();


                                NewsFeeds feeds = new NewsFeeds(nm.get(i).text(), "Top 100 yabancı şarkılar", String.valueOf(random.nextInt(5000 - 500 + 1) + 500), "http://en.apps4chromecast.com/wp-content/uploads/sites/3/2016/03/Rocket_icon.png", t1.get(i), "", 0, R.drawable.asd2, false,4);

                                top100yabanci.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            k21();


                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }
    public void k22(final Boolean kanal, String uli) {
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, uli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".moviea");
                            Elements im = document.select(".downloadimg[src]");
                            Elements ln = document.select(".moviea[data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+"]");
                            Elements hu;
                            String skanal;
                            if (kanal) {
                                hu = document.select(".popular");
                            } else
                                hu = document.select(".pull-left.publisher.pub2 a");
                            Elements vv = document.select(".label-views");
                            Elements dr = document.select(".duration");

                            for (int i = 0; i < nm.size(); i++) {
                                if (kanal)
                                    skanal = hu.text();
                                else
                                    skanal = hu.get(i).text();

                                NewsFeeds feeds = new NewsFeeds(fix(nm.get(i).text()), fix(skanal), vv.get(i).text(), im.get(i).attr("src"), ln.get(i).attr("data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+""), dr.get(i).text(), 0, R.drawable.asd2, false,22);

                                k22.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                           k31(true,"https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/channel.php?id=UCKNPnSEATVWyo5NPuCNtH6w");
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }

    public void k31(final Boolean kanal, String uli) {
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, uli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".moviea");
                            Elements im = document.select(".downloadimg[src]");
                            Elements ln = document.select(".moviea[data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+"]");
                            Elements hu;
                            String skanal;
                            if (kanal) {
                                hu = document.select(".popular");
                            } else
                                hu = document.select(".pull-left.publisher.pub2 a");
                            Elements vv = document.select(".label-views");
                            Elements dr = document.select(".duration");

                            for (int i = 0; i < nm.size(); i++) {
                                if (kanal)
                                    skanal = hu.text();
                                else
                                    skanal = hu.get(i).text();

                                NewsFeeds feeds = new NewsFeeds(fix(nm.get(i).text()), fix(skanal), vv.get(i).text(), im.get(i).attr("src"), ln.get(i).attr("data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+""), dr.get(i).text(), 0, R.drawable.asd2, false,31);

                                k31.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            top100yerli();


                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }

    public void k21() {
        String fg="ow.a";
        final ArrayList<String> t1 = new ArrayList<>();
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, "http://sl"+fg+"z",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".playlist-name");

                            Elements ln = document.select("a.playlist-down.no-ajax");
                            Elements ys = document.select(".playlist-right");


                            for (int i = 0; i < ln.size(); i++) {
                                    t1.add(ln.get(i).attr("href"));
                            }


                            for (int i = 0; i < nm.size(); i++) {




                                NewsFeeds feeds = new NewsFeeds(nm.get(i).text(), "Yeni Mahnılar", ys.get(i).text(), "http://en.apps4chromecast.com/wp-content/uploads/sites/3/2016/03/Rocket_icon.png", t1.get(i), "", 0, R.drawable.asd2, false,21);

                                k21.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            k22(true,"https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/channel.php?id=UC8K4d9NYv7UFrV7MXbrU1BA");


                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }

    public void top100yerli() {
        final ArrayList<String> t1 = new ArrayList<>();
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, "http://mp"+getResources().getString(R.string.ic)+"rco.info/hit-"+muzka+"ler",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".Mp3Listele .Baslik4");

                            Elements ln = document.select(".Mp3Listele .Baslik4 a");


                            for (int i = 0; i < ln.size(); i++) {
                                if (i % 2 == 1)
                                    t1.add("http://mp"+getResources().getString(R.string.ic)+"rco.info/" + ln.get(i).attr("href"));
                            }


                            for (int i = 0; i < nm.size(); i++) {

                                String lnk;
                                Random random = new Random();


                                NewsFeeds feeds = new NewsFeeds(nm.get(i).text(), "Top 100 yerli şarkılar", String.valueOf(random.nextInt(5000 - 500 + 1) + 500), "http://en.apps4chromecast.com/wp-content/uploads/sites/3/2016/03/Rocket_icon.png", t1.get(i), "", 0, R.drawable.asd2, false,6);

                                top100yerli.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            yenicikanyerlialbumler();

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }

    public void yenicikanyerlialbumler() {
        final ArrayList<String> t1 = new ArrayList<>();
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, "http://mp"+getResources().getString(R.string.ic)+"rco.info/yeni-cikan-"+muzka+"ler",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".Orta_Icerik .Baslik4");

                            Elements ln = document.select(".Orta_Icerik .Baslik4 a");


                            for (int i = 0; i < ln.size(); i++) {
                                if (i % 2 == 1)
                                    t1.add("http://mp"+getResources().getString(R.string.ic)+"rco.info/" + ln.get(i).attr("href"));
                            }


                            for (int i = 0; i < nm.size(); i++) {

                                String lnk;
                                Random random = new Random();


                                NewsFeeds feeds = new NewsFeeds(nm.get(i).text(), "yerli yeni albümler", String.valueOf(random.nextInt(5000 - 500 + 1) + 500), "http://en.apps4chromecast.com/wp-content/uploads/sites/3/2016/03/Rocket_icon.png", t1.get(i), "", 0, R.drawable.asd2, false,8);

                                yerliyenialbumlar.add(feeds);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            trenn(false, "https://www.gen"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".net/trends.php?geo="+ Locale.getDefault().getCountry());

                            Log.e("kkk",Locale.getDefault().getCountry());
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }

    public void trenn(final Boolean kanal, String uli) {
        queue = NetworkController.getInstance(getApplicationContext()).getRequestQueue();
        //Volley's inbuilt class to make Json array request
        StringRequest postRequest = new StringRequest(Request.Method.GET, uli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Document document = Jsoup.parse(response);


                            Elements nm = document.select(".moviea");
                            Elements im = document.select(".downloadimg[src]");
                            Elements ln = document.select(".moviea[data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+"]");
                            Elements hu;
                            String skanal;
                            if (kanal) {
                                hu = document.select(".popular");
                            } else
                                hu = document.select(".trenddata a");
                            Elements vv = document.select(".divtrendstat");
                            Elements dr = document.select(".duration");

                            for (int i = 0; i < nm.size(); i++) {
                                if(i!=6 && i!=4){

                                if (kanal)
                                    skanal = hu.text();
                                else
                                    skanal = hu.get(i).text();

                                NewsFeeds feeds = new NewsFeeds(fix(nm.get(i).text()), fix(skanal), (new StringBuffer(vv.get(i).text().replace("global", "")).replace(0, 22, " ")).toString(), im.get(i).attr("src"), ln.get(i).attr("data-"+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+""), dr.get(i).text(), 0, R.drawable.asd2, false,1);

                                feedsList1.add(feeds);

                                }
                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            ConstraintLayout an=findViewById(R.id.an);
                            an.setVisibility(View.GONE);
                            mDrawerLayout.setVisibility(View.VISIBLE);


                            pager = (ViewPager) findViewById(R.id.viewpager);
                            slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);

                            pager.setAdapter(new ViewPagerAdapter(getApplicationContext(), getSupportFragmentManager(), titles, feedsList1, feedsList2, top20yerli, top100yabanci, yabanci2018, top100yerli, eniyiyabanci, yerliyenialbumlar,k21,k22,k31));
                            slidingTabLayout.setViewPager(pager);
                            slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                                @Override
                                public int getIndicatorColor(int position) {
                                    return Color.WHITE;
                                }

                            });
                            pl();
                            mAdView = findViewById(R.id.adView);
                            AdRequest adRequest = new AdRequest.Builder().addTestDevice("12189A14DC002169B13523D633BEAF7F").build();
                            mAdView.loadAd(adRequest);
                            RateThisApp.onCreate(SampleActivity.this);
                            RateThisApp.Config config = new RateThisApp.Config(2,4);
                            config.setTitle(R.string.my_own_title);
                            config.setMessage(R.string.my_own_message);
                            config.setYesButtonText(R.string.rate_dialog_ok);
                            config.setNoButtonText(R.string.rate_dialog_no);
                            config.setCancelButtonText(R.string.rate_dialog_cancel);
                            RateThisApp.init(config);
                            // If the condition is satisfied, "Rate this app" dialog will be shown
                            RateThisApp.showRateDialogIfNeeded(SampleActivity.this);

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });

        //Adding JsonArrayRequest to Request Queue
        queue.add(postRequest);
        // Toast.makeText(this,String.valueOf(feedsList.size()),Toast.LENGTH_LONG);

    }
    public void pl(){
        for (int ii = 0; ii < feedsList1.size(); ii++)
            jcAudios1.add(JcAudio.createFromURL(feedsList1.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+feedsList1.get(ii).getUrl()));
        id1=true;
        for (int ii = 0; ii < feedsList2.size(); ii++)
            jcAudios2.add(JcAudio.createFromURL(feedsList2.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+feedsList2.get(ii).getUrl()));
        id2=true;
        for (int ii = 0; ii < top20yerli.size(); ii++)
            jcAudios3.add(JcAudio.createFromURL(top20yerli.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+top20yerli.get(ii).getUrl()));
        id3=true;
        for (int ii = 0; ii < top100yabanci.size(); ii++)
            jcAudios4.add(JcAudio.createFromURL(top100yabanci.get(ii).getFeedName(), top100yabanci.get(ii).getUrl()));
        id4=true;
        for (int ii = 0; ii < yabanci2018.size(); ii++)
            jcAudios5.add(JcAudio.createFromURL(yabanci2018.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+yabanci2018.get(ii).getUrl()));
        id5=true;
        for (int ii = 0; ii < top100yerli.size(); ii++)
            jcAudios6.add(JcAudio.createFromURL(top100yerli.get(ii).getFeedName(), top100yerli.get(ii).getUrl()));
        id6=true;
        for (int ii = 0; ii < eniyiyabanci.size(); ii++)
            jcAudios7.add(JcAudio.createFromURL(eniyiyabanci.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+eniyiyabanci.get(ii).getUrl()));
        id7=true;
        for (int ii = 0; ii < yerliyenialbumlar.size(); ii++)
            jcAudios8.add(JcAudio.createFromURL(yerliyenialbumlar.get(ii).getFeedName(), yerliyenialbumlar.get(ii).getUrl()));
        id8=true;


        for (int ii = 0; ii < k21.size(); ii++)
            jcAudios21.add(JcAudio.createFromURL(k21.get(ii).getFeedName(), k21.get(ii).getUrl()));
        id21=true;
        for (int ii = 0; ii < k22.size(); ii++)
            jcAudios22.add(JcAudio.createFromURL(k22.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+k22.get(ii).getUrl()));
        id22=true;
        for (int ii = 0; ii < k31.size(); ii++)
            jcAudios31.add(JcAudio.createFromURL(k31.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+k31.get(ii).getUrl()));
        id31=true;

        hamsi=true;
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

    public void jcpl(final ArrayList<NewsFeeds> fl, Context con, Integer pos, Integer kmm) {
        jcplayerView.setVisibility(View.VISIBLE);
        Integer id=fl.get(pos).getId();
        Log.e("hamsi",String.valueOf(hamsi));

      if (id == 9) {
            jcAudios9.clear();

            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios9.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+ fl.get(ii).getUrl()));
          //  km = id;
            //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
            Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
            jcplayerView.initPlaylist(jcAudios9);
        }
        else if(id==1){
        if(!id1){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios1.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+ fl.get(ii).getUrl()));
            id1=true;
        }
         // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios1);
    }
        else if(id==2){
        if(!id2){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios2.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+ fl.get(ii).getUrl()));
            id2=true;
        }
         // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios2);
    }
        else if(id==3){
        if(!id3){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios3.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+ fl.get(ii).getUrl()));
            id3=true;
        }
         // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios3);
    }

        else if(id==4){
        if(!id4){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios4.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), fl.get(ii).getUrl()));
            id4=true;
        }
         // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios4);
    }
    else if(id==5){
        if(!id5){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios5.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+ fl.get(ii).getUrl()));

            id5=true;
        }
        // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios5);
    }
        else if(id==6){
        if(!id6){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios6.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), fl.get(ii).getUrl()));
            id6=true;
        }
         // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios6);
    }
        else if(id==7){
        if(!id7){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios7.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+ fl.get(ii).getUrl()));
            id7=true;
        }
         // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios7);
    }
        else if(id==8){
        if(!id8){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios8.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), fl.get(ii).getUrl()));
            id8=true;
        }
         // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios8);
    }
        else if(id==21){
        if(!id21){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios21.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), fl.get(ii).getUrl()));
            id21=true;
        }
        jcplayerView.initPlaylist(jcAudios21);
    }
        else if(id==22){
        if(!id22){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios22.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+ fl.get(ii).getUrl()));
            id22=true;
        }
         // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios22);
    }
        else if(id==31){
        if(!id31){
            for (int ii = 0; ii < fl.size(); ii++)
                jcAudios31.add(JcAudio.createFromURL(fl.get(ii).getFeedName(), "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+ fl.get(ii).getUrl()));
            id31=true;
        }
         // km = id;
        //Toast.makeText(con,String.valueOf(km),Toast.LENGTH_LONG);
        Log.e("lll"+fl.get(pos).getFeedName(),String.valueOf(id));
        jcplayerView.initPlaylist(jcAudios31);
    }



        jcplayerView.playAudio(jcplayerView.getMyPlaylist().get(pos));

        jcplayerView.registerInvalidPathListener(this);
        jcplayerView.registerStatusListener(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        jcplayerView.createNotification(R.mipmap.ic_launcher);
    }

    @Override
    protected void onDestroy() {
        try {
            unregisterReceiver(legv);
        } catch (Exception e) {

        }
        try {
            dinleyici.releaseTaskEndRunnable();
        } catch (Exception e) {

        }
        try {
            unregisterReceiver(qr);
        } catch (Exception e) {

        }
        try {
            jcplayerView.kill();

        } catch (Exception e) {

        }



        super.onDestroy();


    }

    @Override
    public void onPathError(JcAudio jcAudio) {
        Toast.makeText(this, jcAudio.getPath() + " with problems", Toast.LENGTH_LONG).show();
//        jcplayerView.removeAudio(jcAudio);
//        jcplayerView.next();
    }

    @Override
    public void onPausedStatus(JcStatus jcStatus) {

    }

    @Override
    public void onContinueAudioStatus(JcStatus jcStatus) {

    }

    @Override
    public void onPlayingStatus(JcStatus jcStatus) {

    }

    @Override
    public void onTimeChangedStatus(JcStatus jcStatus) {
        //updateProgress(jcStatus);
    }

    @Override
    public void onCompletedAudioStatus(JcStatus jcStatus) {
        // updateProgress(jcStatus);
    }

    @Override
    public void onPreparedAudioStatus(JcStatus jcStatus) {
    }

    public void hj(final Context c, String luu, final ArrayList<NewsFeeds> fl, final Integer pos) {
        final Integer kmm=fl.get(pos).getId();
        if(pos%3==0)
            showInterstitial();
        final Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Please Choose");
        mPocketBar = (FrameLayout) dialog.findViewById(R.id.yut);
        qq = (LinearLayout) dialog.findViewById(R.id.wb);
        qq1 = (LinearLayout) dialog.findViewById(R.id.wb1);
        ay3 = (TextView) dialog.findViewById(R.id.ay3);
        ay33 = (TextView) dialog.findViewById(R.id.ay33);
        ay1 = (TextView) dialog.findViewById(R.id.ay1);
        ay11 = (TextView) dialog.findViewById(R.id.ay11);
        ay2 = (TextView) dialog.findViewById(R.id.ay2);
        ay22 = (TextView) dialog.findViewById(R.id.ay22);
        m1 = (TextView) dialog.findViewById(R.id.m1);
        m11 = (TextView) dialog.findViewById(R.id.m11);
        w1 = (LinearLayout) dialog.findViewById(R.id.w1);
        w2 = (LinearLayout) dialog.findViewById(R.id.w2);
        w3 = (LinearLayout) dialog.findViewById(R.id.w3);
        w11 = (LinearLayout) dialog.findViewById(R.id.w11);
        w22 = (LinearLayout) dialog.findViewById(R.id.w22);
        w33 = (LinearLayout) dialog.findViewById(R.id.w33);
        title = (TextView) dialog.findViewById(R.id.title_view);
        content = (TextView) dialog.findViewById(R.id.content_view);
        ce = (TextView) dialog.findViewById(R.id.c);
        d = (TextView) dialog.findViewById(R.id.daga);
        imageview = (NetworkImageView) dialog.findViewById(R.id.thumbnail);

        NewsFeeds feeds = fl.get(pos);
        //Pass the values of feeds object to Views
        title.setText(feeds.getFeedName());
        content.setText(feeds.getContent());
        ce.setText(feeds.getVv());
        d.setText(feeds.getDr());
        // holder.pl.setImageResource(feeds.getPlayPauseId());
        imageview.setImageUrl(feeds.getImgURL(), NetworkController.getInstance(getApplicationContext()).getImageLoader());

        w1.setBackground(makeSelector(Color.parseColor("#FF8800")));
        w11.setBackground(makeSelector(Color.parseColor("#FF4081")));
        w2.setBackground(makeSelector(Color.parseColor("#FF8800")));
        w22.setBackground(makeSelector(Color.parseColor("#FF4081")));

        w3.setBackground(makeSelector(Color.parseColor("#BF360C")));
        w33.setBackground(makeSelector(Color.parseColor("#304F9F")));
        qq.setBackground(makeSelector(Color.parseColor("#FF8800")));
        qq1.setBackground(makeSelector(Color.parseColor("#FF4081")));
        if (kmm == 1 || kmm == 2 || kmm == 3 || kmm == 5 || kmm == 7 || kmm == 9 || kmm==22 || kmm==31) {
            loadList2(luu,c);
            w1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //jcpl(fl,c,pos,kmm);
                    dialog.dismiss();
                    kl();
                    Intent intent = new Intent(c.getApplicationContext(), Vidp.class);
                    intent.putExtra("ur", w720);
                    intent.putExtra("ad", fl.get(pos).getFeedName());
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    c.getApplicationContext().startActivity(intent);
                    // finish();
                }
            });
            w11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //jcpl(fl,c,pos,3);
                    dove(fl.get(pos).getFeedName()+".mp4",  w720,false);
                    dialog.dismiss();
                }
            });
            w2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    kl();
                    Intent intent = new Intent(c.getApplicationContext(), Vidp.class);
                    intent.putExtra("ur", w360);
                    intent.putExtra("ad", fl.get(pos).getFeedName());
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    c.getApplicationContext().startActivity(intent);

                }
            });
            w22.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //jcpl(fl,c,pos,3);
                    dove(fl.get(pos).getFeedName()+".mp4",  w360,false);
                    dialog.dismiss();
                }
            });
            qq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    kl();
                    Intent intent = new Intent(c.getApplicationContext(), Vidp.class);
                    intent.putExtra("ur", webm);
                    intent.putExtra("ad", fl.get(pos).getFeedName());
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    c.getApplicationContext().startActivity(intent);
                    dialog.dismiss();
                }
            });
            qq1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //jcpl(fl,c,pos,3);
                    dove(fl.get(pos).getFeedName()+".3gp",webm,false);
                    dialog.dismiss();

                }
            });
            w3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    jcpl(fl, c, pos, kmm);
                }
            });
            w33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //jcpl(fl,c,pos,3);
                    dove(fl.get(pos).getFeedName()+".3gp",p3,true);
                    dialog.dismiss();
                }
            });
        }
        else if(kmm==21){
            mPocketBar.setVisibility(View.GONE);
            w3.setVisibility(View.VISIBLE);
            w33.setVisibility(View.VISIBLE);
            String ayy3 = mub;
            m1.setText(ayy3);
            m11.setText(ayy3);
            w3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jcpl(fl, c, pos, kmm);
                    dialog.dismiss();
                }
            });
            w33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fl.get(pos).getUrl()));
                    request.setDescription("Loading...");
                    request.setTitle(fl.get(pos).getFeedName());
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.allowScanningByMediaScanner();
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fl.get(pos).getFeedName()+".mp3");
                    // get download service and enqueue file
                    DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                    dialog.dismiss();

                }
            });
        }
        else  {
            tm.clear();
            queue = NetworkController.getInstance(c).getRequestQueue();
            StringRequest stri = new StringRequest(Request.Method.GET,
                    luu,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            mPocketBar.setVisibility(View.GONE);
                            w3.setVisibility(View.VISIBLE);
                            w33.setVisibility(View.VISIBLE);
                            Document document = Jsoup.parse(response);
                            final Elements element = document.select(".Orta_Indir_Buton a");
                            String ayy3 = mub;
                            m1.setText(ayy3);
                            m11.setText(ayy3);
                            w3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    jcpl(fl, c, pos, kmm);
                                    dialog.dismiss();
                                }
                            });
                            w33.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //jcpl(fl,c,pos,3);
                                    dove(fl.get(pos).getFeedName() + "."+muzka, "http://mp"+getResources().getString(R.string.ic)+"rco.info/" + element.attr("href"),false);

                                    dialog.dismiss();
                                }
                            });

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            queue.add(stri);
        }
        dialog.show();
       int w = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, getResources().getDisplayMetrics());
        Window window = dialog.getWindow();
        window.setLayout(w, WindowManager.LayoutParams.WRAP_CONTENT);


    }
public void hj2(final Context c, String luu) {


            showInterstitial();
        final Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.custom_dialogins);
        dialog.setTitle("Download from Instagram");
        mPocketBar = (FrameLayout) dialog.findViewById(R.id.yut);


        ay1 = (TextView) dialog.findViewById(R.id.ay1);
        ay11 = (TextView) dialog.findViewById(R.id.ay11);

        w1 = (LinearLayout) dialog.findViewById(R.id.w1);
        w2 = (LinearLayout) dialog.findViewById(R.id.w2);
        w11 = (LinearLayout) dialog.findViewById(R.id.w11);

        imageview = (NetworkImageView) dialog.findViewById(R.id.thumbnail);



        w1.setBackground(makeSelector(Color.parseColor("#FF8800")));
        w11.setBackground(makeSelector(Color.parseColor("#FF4081")));
        w2.setBackground(makeSelector(Color.parseColor("#FF8800")));

    queue = NetworkController.getInstance(c).getRequestQueue();
    String lj= null;
    try {
        lj = "https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video="+ URLEncoder.encode(luu, "UTF-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }

    StringRequest postRequest = new StringRequest(Request.Method.GET, lj,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    mPocketBar.setVisibility(View.GONE);
                    try {

                        Document document = Jsoup.parse(response);
                        Elements nm = document.select("a.downloadBtn");
                        Elements in = document.select(".info");
                        Elements imm = document.select("img[src]");
                        if((nm.get(0).text()).contains("Download image")){
                            w2.setVisibility(View.VISIBLE);
                            imageview.setVisibility(View.VISIBLE);
                            imageview.setImageUrl(imm.get(0).attr("src"), NetworkController.getInstance(getApplicationContext()).getImageLoader());

                            inim = nm.get(0).attr("href");
                        }
                        else{
                            w1.setVisibility(View.VISIBLE);
                            w11.setVisibility(View.VISIBLE);
                            imageview.setVisibility(View.VISIBLE);

                            imageview.setImageUrl(imm.get(0).attr("src"), NetworkController.getInstance(getApplicationContext()).getImageLoader());

                            invi = nm.get(0).attr("href");
                            invisi=(in.get(0).text()).replaceAll("Format:","")+(in.get(1).text()).replaceAll("Size:"," ");
                            ay1.setText(invisi);
                            ay11.setText(invisi);
                        }


                        //oynat(j,(nm.get(3).attr("href")).replaceAll("&amp;", "&"));
                    } catch (Exception e) {
                        // System.out.println(e.toString());
                        Toast.makeText(c,"Error...",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                }

            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          //  Log.e("hhh", error.toString());
            Toast.makeText(c,"URL is not supported",Toast.LENGTH_SHORT).show();
            dialog.dismiss();

        }
    });


    queue.add(postRequest);
    w1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //jcpl(fl,c,pos,kmm);
            dialog.dismiss();
            kl();
            Intent intent = new Intent(c.getApplicationContext(), Vidp.class);
            intent.putExtra("ur", invi);
            intent.putExtra("ad", "");
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            c.getApplicationContext().startActivity(intent);
            // finish();
        }
    });
    w11.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //jcpl(fl,c,pos,3);
            Random random = new Random();
            String gh="Instagram_Video_"+String.valueOf(random.nextInt(50000 - 500 + 1) + 500);
            dove(gh+".mp4",  invi,false);
            dialog.dismiss();
        }
    });
    w2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
            Random random = new Random();
            String gh="Instagram_Picture_"+String.valueOf(random.nextInt(50000 - 500 + 1) + 500);
            dove(gh+".jpg",  inim,false);
            dialog.dismiss();

        }
    });
        dialog.show();
       int w = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, getResources().getDisplayMetrics());
        Window window = dialog.getWindow();
        window.setLayout(w, WindowManager.LayoutParams.WRAP_CONTENT);


    }

    public void dove(String adl, String url,Boolean mp) {
         showInterstitial();


        String ad=adl;
        if (hasCameraPermission()) {
            Toast.makeText(this, ad.replaceAll("3gp","") + " downloading...", Toast.LENGTH_SHORT).show();
            dovem(ad,url,mp);


        }
        else{
            Toast.makeText(getApplicationContext(), "İzin yok!!!", Toast.LENGTH_SHORT).show();
            izin();
        }


    }
    public void dovem(String ad, String url,Boolean mp) {


        dinleyici = new UNotificationEsas(this, ad,mp);


        final Intent intent = new Intent(CancelReceiver.ACTION);
        final PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        dinleyici.setAction(new NotificationCompat.Action(0, "Cancel", cancelPendingIntent));
        dinleyici.initNotification();

        gorev = new DownloadTask
                .Builder(url, UYukle.getParentFile(this))
                .setFilename(ad)
                .setPassIfAlreadyCompleted(false)
                .setMinIntervalMillisCallbackProcess(80)
                .setAutoCallbackToUIThread(false)
                .build();

        GlobalTaskManager.getImpl().enqueueTask(gorev, dinleyici);

        // for cancel action on notification.
        IntentFilter filter = new IntentFilter(CancelReceiver.ACTION);
        legv = new CancelReceiver(gorev);
        registerReceiver(legv, filter);

        GlobalTaskManager.getImpl().attachListener(gorev, dinleyici);
        GlobalTaskManager.getImpl().addAutoRemoveListenersWhenTaskEnd(gorev.getId());


    }

    static class CancelReceiver extends BroadcastReceiver {
        static final String ACTION = "cancelOkdownload";

        private DownloadTask gorev;

        CancelReceiver(@NonNull DownloadTask gorev) {
            this.gorev = gorev;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            this.gorev.cancel();
        }
    }

    static class GlobalTaskManager {
        private UnifiedListenerManager manager;

        private GlobalTaskManager() {
            manager = new UnifiedListenerManager();
        }

        private static class ClassHolder {
            private static final GlobalTaskManager INSTANCE = new GlobalTaskManager();
        }

        static GlobalTaskManager getImpl() {
            return ClassHolder.INSTANCE;
        }

        void addAutoRemoveListenersWhenTaskEnd(int id) {
            manager.addAutoRemoveListenersWhenTaskEnd(id);
        }

        void attachListener(@NonNull DownloadTask gorev, @NonNull DownloadListener dinleyici) {
            manager.attachListener(gorev, dinleyici);
        }

        void enqueueTask(@NonNull DownloadTask gorev,
                         @NonNull DownloadListener dinleyici) {
            manager.enqueueTaskWithUnifiedListener(gorev, dinleyici);
        }
    }

    public void tbt(String t) {
        getSupportActionBar().setTitle(t);
        getSupportActionBar().getThemedContext();
        toolbar.setTitleTextColor(0xFFFFFFFF);
    }

    public void loadList2(String  JSON_URL,Context c) {
        queue = NetworkController.getInstance(c).getRequestQueue();
        String lj="https:/"+getResources().getString(R.string.gi)+"nloader.net/download?video=https%3A%2F%2Fwww."+getResources().getString(R.string.sen)+"u"+ara+getResources().getString(R.string.o)+".com%2F"+izle+"%3Fv%3D"+JSON_URL;
        StringRequest postRequest = new StringRequest(Request.Method.GET, lj,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mPocketBar.setVisibility(View.GONE);
                        try {

                            Document document = Jsoup.parse(response);
                            Elements nm = document.select("tbody tr");
                            Elements lm = document.select("tbody tr a");
                            //tm.clear();


                            for (int y = 0; y < 4; y++) {
                                //tm.add((nm.get(y).attr("href")).replaceAll("&amp;", "&"));
                                String ja = (nm.get(y).text());
                                if (ja.contains("240p")) {
                                    qq.setVisibility(View.VISIBLE);
                                    qq1.setVisibility(View.VISIBLE);
                                    String ayy3 = ja.replaceAll("3gp", "").replaceAll("Download", "");
                                    ay3.setText(ayy3);
                                    ay33.setText(ayy3);
                                    webm = (lm.get(y).attr("href")).replaceAll("&amp;", "&");
                                    w3.setVisibility(View.VISIBLE);
                                    w33.setVisibility(View.VISIBLE);
                                    String ayy33 = ja.replaceAll("3gp", "320Kbps").replaceAll("Download", "").replaceAll("240p", "");
                                    m1.setText(ayy33);
                                    m11.setText(ayy33);
                                    p3 = (lm.get(y).attr("href")).replaceAll("&amp;", "&");
                                    //360p (14.6 MB)
                                    //Download Webm [360x640] (638.85MB)
                                } else if (ja.contains("720p") && ja.contains("mp4")) {
                                    w1.setVisibility(View.VISIBLE);
                                    w11.setVisibility(View.VISIBLE);
                                    String ayy3 = ja.replaceAll("mp4", "").replaceAll("Download", "");
                                    ay1.setText(ayy3);
                                    ay11.setText(ayy3);
                                    w720 = (lm.get(y).attr("href")).replaceAll("&amp;", "&");
                                    //360p (14.6 MB)
                                    //Download MP4 (HD) [720x1280] (770.63MB)
                                } else if (ja.contains("360p") && ja.contains("mp4")) {
                                    w2.setVisibility(View.VISIBLE);
                                    w22.setVisibility(View.VISIBLE);
                                    String ayy3 = ja.replaceAll("mp4", "").replaceAll("Download", "");
                                    ay2.setText(ayy3);
                                    ay22.setText(ayy3);
                                    w360 = (lm.get(y).attr("href")).replaceAll("&amp;", "&");
                                    //360p (14.6 MB)
                                    //Download MP4 (HD) [720x1280] (770.63MB)
                                }

                                //tt.add(jl);
                            }
                            //oynat(j,(nm.get(3).attr("href")).replaceAll("&amp;", "&"));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("hhh", error.getMessage());
            }
        });


        queue.add(postRequest);
    }


    public void insge(String  JSON_URL, final Context c) {

    }



    @AfterPermissionGranted(123)
    public void izin() {
        if (!hasCameraPermission()) {

            EasyPermissions.requestPermissions(
                    this,"Permission",
                    123, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }
    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        // Log.d("log", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {

        }
    }
   /* @Override
    public void onBackPressed()
    {
        try{
            moveTaskToBack(true);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Çıkmak için geri düğmesine bir kaç defa dokunun.",Toast.LENGTH_SHORT).show();
        }
    }*/
    public StateListDrawable makeSelector(int color) {
        StateListDrawable res = new StateListDrawable();
        /*res.setExitFadeDuration(400);
        res.setAlpha(45);*/
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.parseColor("#FA78A5")));
       // res.addState(new int[]{},new ColorDrawable(color));
        res.addState(new int[]{},ContextCompat.getDrawable(getApplicationContext(), R.drawable.gr));
        return res;

    }

    static public void serve(final Context context, final String adi, final NotificationCompat.Builder builder, final NotificationManager manager, final Integer id){
        builder.setContentText("Please wait...");
        manager.notify(id, builder.build());
        nl.add(new Nfc(id,builder,manager));
        if(!qur){
            Intent startIntent = new Intent(context, MyService.class);
            startIntent.setAction(C);
            final Bundle bundle = new Bundle();
          //  bundle.putBinder("object_value", new ObjectWrapperForBinder(builder));
          //  bundle.putBinder("object_value2", new ObjectWrapperForBinder2(manager));
            startIntent.putExtra("pat", adi);
            startIntent.putExtra("id", id);
            startIntent.putExtra("bd", true);
            startIntent.putExtras(bundle);
            context.startService(startIntent);
            qur=true;

            buildere=builder;
            managera=manager;
            ida=id;
        }
        else{

            handler.postDelayed(new Runnable(){
                public void run(){
                    //do something
                    if(!qur){
                        Log.e("mamarod",adi);
                        Intent startIntent = new Intent(context, MyService.class);
                        startIntent.setAction(C) ;
                        startIntent.putExtra("pat", adi);
                        final Bundle bundle = new Bundle();
                       // bundle.putBinder("object_value", new ObjectWrapperForBinder3(builder));
                      //  bundle.putBinder("object_value2", new ObjectWrapperForBinder4(manager));
                        startIntent.putExtra("bd", false);
                        startIntent.putExtra("pat", adi);
                        startIntent.putExtra("id", id);
                        context.startService(startIntent);
                        qur=true;
                        buildere=builder;
                        managera=manager;
                        ida=id;


                    }
                    else
                    handler.postDelayed(this, 3000);
                }
            }, 3000);
        }

    }
    static public void cev(Boolean bb){
        qur=bb;
    }
    static public void son(Context context,String ada,Integer idd){
        for(int i=0;i<nl.size();i++) {
            if (idd == nl.get(i).getId()) {
                Integer idg = nl.get(i).getId();
                NotificationCompat.Builder builderg = nl.get(i).getBuilder();
                NotificationManager managerg = nl.get(i).getManager();

                builderg.setTicker("Download Completed");

                Uri uri = FileProvider.getUriForFile(context,
                        BuildConfig.APPLICATION_ID + ".provider", (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), ada.replaceAll("3gp","mp3"))));



                builderg.setContentText("Download Completed");
                //builderg.setAutoCancel(true);
                builderg.setProgress(1, 1, false);

                //   String last=ad.substring(ad.length()-1,ad.length());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setDataAndType(uri, "audio/mp3").setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                final PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                builderg.setContentIntent(contentIntent);

                IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_FINISHED);
                intentFilter.addDataScheme("file");
                managerg.notify(idg, builderg.build());
                nl.remove(i);
            }
        }

    }
    static public void bos(Context context,String ada,Integer idd){
        for(int i=0;i<nl.size();i++){
            if(idd==nl.get(i).getId()){
                Integer idg=nl.get(i).getId();
                NotificationCompat.Builder builderg=nl.get(i).getBuilder();
                NotificationManager managerg=nl.get(i).getManager();

                builderg.setTicker("Sorry, Error Occured");




                builderg.setContentText("Sorry, Error Occured");
              //  builderg.setAutoCancel(true);
                builderg.setProgress(0, 0, false);

                //   String last=ad.substring(ad.length()-1,ad.length());

                managerg.notify(idg, builderg.build());
                nl.remove(i);
            }
        }

    }

}
