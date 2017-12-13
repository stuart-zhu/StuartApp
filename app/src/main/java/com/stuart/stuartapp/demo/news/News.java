package com.stuart.stuartapp.demo.news;

/**
 * Created by stuart on 2017/8/22.
 */

import java.util.ArrayList;
import java.util.List;


public class News {
    private int id;
    private String uniquekey;
    private String title;
    private long date;
    private String category;
    private String author_name;
    private String url;
    private List<String> thumbnail_pic_s;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void addThumbnail_pic_s(String uri) {
        if (thumbnail_pic_s == null) thumbnail_pic_s = new ArrayList<>();
        thumbnail_pic_s.add(uri);
    }


    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", uniquekey='" + uniquekey + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", author_name='" + author_name + '\'' +
                ", url='" + url + '\'' +
                ", thumbnail_pic_s=" + thumbnail_pic_s +
                '}';
    }
}
