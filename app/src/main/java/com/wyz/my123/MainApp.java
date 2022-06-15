package com.wyz.my123;

import android.app.Application;

public class MainApp extends Application {
    Integer uid;
    String displayName;
    public Integer getUid(){
        return uid;
    }
    public void setUid(Integer uid){
        this.uid = uid;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
