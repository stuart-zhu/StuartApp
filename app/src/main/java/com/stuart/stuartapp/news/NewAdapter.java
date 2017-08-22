package com.stuart.stuartapp.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.squareup.picasso.Picasso;
import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2017/8/22.
 */

public class NewAdapter extends BaseAdapter {

    List<News> mNews;

    private LayoutInflater mInflater;

    private Context context;
    NewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void addNew(News n) {
        if (mNews == null) mNews = new ArrayList<>();
        for (News nn : mNews) {
            if (nn.getUniquekey().equals(n.getUniquekey())) {
                return;
            }
        }
        mNews.add(0, n);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mNews == null ? 0 : mNews.size();
    }

    @Override
    public News getItem(int position) {
        return mNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mNews.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder vh;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.activity_news_item, null);
            vh = new MyViewHolder(convertView);
        } else {
            vh = (MyViewHolder) convertView.getTag();
        }
        News item = getItem(position);
        vh.tvTitle.setText(item.getTitle());
        if (item.getThumbnail_pic_s() != null && item.getThumbnail_pic_s().size() > 0) {
            vh.ivL.setVisibility(View.VISIBLE);
            if (item.getThumbnail_pic_s().size() >= 3) {
                Picasso.with(context).load(item.getThumbnail_pic_s().get(0))/*.resize(400,400).centerCrop()*/.into(vh.iv1);
                Picasso.with(context).load(item.getThumbnail_pic_s().get(1))/*.resize(40,40).centerCrop()*/.into(vh.iv2);
                Picasso.with(context).load(item.getThumbnail_pic_s().get(2))/*.resize(40,40).centerCrop()*/.into(vh.iv3);
            } else if (item.getThumbnail_pic_s().size() >= 2) {
                Picasso.with(context).load(item.getThumbnail_pic_s().get(0))/*.resize(40,40).centerCrop()*/.into(vh.iv1);
                Picasso.with(context).load(item.getThumbnail_pic_s().get(1))/*.resize(40,40).centerCrop()*/.into(vh.iv2);
                vh.iv3.setImageBitmap(null);
            } else if (item.getThumbnail_pic_s().size() >= 1) {
                Picasso.with(context).load(item.getThumbnail_pic_s().get(0))/*.resize(40,40).centerCrop()*/.into(vh.iv1);
                vh.iv2.setImageBitmap(null);
                vh.iv3.setImageBitmap(null);
            }

        } else {
            vh.ivL.setVisibility(View.GONE);
        }
        vh.tvTName.setText(item.getAuthor_name());
        vh.tvTime.setText(formatTime(item.getDate()));
        return convertView;
    }

    private String formatTime(long time) {
        long current = System.currentTimeMillis();
        long xx = current - time;
        if (xx < 1000 * 60 * 3) {
            return "刚刚";
        } else if (xx < 1000 * 60 * 60) {
            return xx /1000/60 +"分钟前";
        } else if (xx > 1000 * 60 * 60){
            return xx / (1000 * 60 * 60) + "小时前";
        } else {
          return  xx+"";
        }
    }


    private
    class MyViewHolder {

        MyViewHolder(View v) {
            tvTitle = (TextView) v.findViewById(R.id.title);
            ivL = v.findViewById(R.id.iv_c);
            iv1 = (ImageView) v.findViewById(R.id.iv1);
            iv2 = (ImageView) v.findViewById(R.id.iv2);
            iv3 = (ImageView) v.findViewById(R.id.iv3);
            tvTName = (TextView) v.findViewById(R.id.au_name);
            tvTime = (TextView) v.findViewById(R.id.time);
            v.setTag(this);
        }

        TextView tvTitle;
        View ivL;
        ImageView iv1;
        ImageView iv2;
        ImageView iv3;
        TextView tvTName;
        TextView tvTime;
    }
}
