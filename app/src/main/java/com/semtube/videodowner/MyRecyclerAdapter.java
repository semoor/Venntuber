package com.semtube.videodowner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.semtube.videodowner.R;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by AndroidNovice on 6/5/2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private ArrayList<NewsFeeds> feedsList;
    private Context context;
    private LayoutInflater inflater;
    private Integer kmm;

    public MyRecyclerAdapter(Context context, ArrayList<NewsFeeds> feedsList,Integer km) {

        this.context = context;
        this.feedsList = feedsList;
        kmm=km;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = inflater.inflate(R.layout.singleitem_recyclerview, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsFeeds feeds = feedsList.get(position);
        //Pass the values of feeds object to Views
        holder.title.setText(feeds.getFeedName());
        holder.content.setText(feeds.getContent());
        holder.c.setText(feeds.getVv());
        holder.d.setText(feeds.getDr());
       // holder.pl.setImageResource(feeds.getPlayPauseId());
        holder.imageview.setImageUrl(feeds.getImgURL(), NetworkController.getInstance(context).getImageLoader());
       // holder.ratingbar.setProgress(feeds.getRating());
    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView content, title,c,d;
        private NetworkImageView imageview;
        private ImageView pl;
        LinearLayout lj;


        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_view);
            content = (TextView) itemView.findViewById(R.id.content_view);
            c = (TextView) itemView.findViewById(R.id.c);
            d = (TextView) itemView.findViewById(R.id.daga);
            pl=(ImageView)itemView.findViewById(R.id.imageView2);
            imageview = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
            lj=(LinearLayout)itemView.findViewById(R.id.lj);
            lj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((SampleActivity) context).hj(context,feedsList.get(getAdapterPosition()).getUrl(),feedsList,getPosition());

                }
            });
            pl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((SampleActivity) context).hj(context,feedsList.get(getAdapterPosition()).getUrl(),feedsList,getPosition());

                }
            });

        }


    }

}