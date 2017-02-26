package com.example.administrator.lesson19_xutilsdemo2.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/16.
 */

@Table(name = "duanzi")
public class DuanZiBean {

    public static final String TYPE_DUANZI = "29";
    public static final String TYPE_MUSIC = "31";
    public static final String TYPE_VIDEO = "41";
    public static final String TYPE_IMAGE = "10";


    @Override
    public String toString() {
        return "DuanZiBean{" +
                "image3='" + image3 + '\'' +
                ", text='" + text + '\'' +
                ", hate='" + hate + '\'' +
                ", videotime='" + videotime + '\'' +
                ", voicetime='" + voicetime + '\'' +
                ", weixin_url='" + weixin_url + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", width='" + width + '\'' +
                ", voiceuri='" + voiceuri + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", love='" + love + '\'' +
                ", height='" + height + '\'' +
                ", video_uri='" + video_uri + '\'' +
                ", voicelength='" + voicelength + '\'' +
                ", name='" + name + '\'' +
                ", create_time='" + create_time + '\'' +
                ", image0='" + image0 + '\'' +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                '}';
    }

    @Column(name = "text")
    private String text;
    private String hate;
    private String videotime;
    private String voicetime;
    private String weixin_url;//
    @Column(name = "profile_image")
    private String profile_image;
    private String width;
    private String voiceuri;
    @Column(name = "type")
    private String type;

    @Column(name = "id", isId = true, autoGen = false)
    private String id;

    private String love;
    private String height;
    @Column(name="video_uri")
    private String video_uri;
    private String voicelength;
    @Column(name="name")
    private String name;
    @Column(name="create_time")
    private String create_time;

    @Column(name="image0")
    private String image0;
    private String image1;
    private String image2;
    private String image3;


    public String getImage0() {
        return image0;
    }

    public void setImage0(String image0) {
        this.image0 = image0;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHate() {
        return hate;
    }

    public void setHate(String hate) {
        this.hate = hate;
    }

    public String getVideotime() {
        return videotime;
    }

    public void setVideotime(String videotime) {
        this.videotime = videotime;
    }

    public String getVoicetime() {
        return voicetime;
    }

    public void setVoicetime(String voicetime) {
        this.voicetime = voicetime;
    }

    public String getWeixin_url() {
        return weixin_url;
    }

    public void setWeixin_url(String weixin_url) {
        this.weixin_url = weixin_url;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getVoiceuri() {
        return voiceuri;
    }

    public void setVoiceuri(String voiceuri) {
        this.voiceuri = voiceuri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getVideo_uri() {
        return video_uri;
    }

    public void setVideo_uri(String video_uri) {
        this.video_uri = video_uri;
    }

    public String getVoicelength() {
        return voicelength;
    }

    public void setVoicelength(String voicelength) {
        this.voicelength = voicelength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
