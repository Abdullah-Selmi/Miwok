package com.example.miwok;

import java.util.StringTokenizer;

public class word {

    private String mDefaultTranslation;

    private String mMewokTranslation;

    private int mImageResourseId = -1;

    private int mAudioResourseId ;

    public word (String DefaultTranslation , String MewokTranslation , int AudioResourseId) {
        mDefaultTranslation = DefaultTranslation;
        mMewokTranslation = MewokTranslation;
        mAudioResourseId = AudioResourseId;
    }

    public word (String DefaultTranslation , String MewokTranslation , int Image , int AudioResourseId) {
        mDefaultTranslation = DefaultTranslation;
        mMewokTranslation = MewokTranslation;
        mImageResourseId = Image;
        mAudioResourseId = AudioResourseId;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMewokTranslation() {
        return mMewokTranslation;
    }

    public int getmImageResurseId() {
        return mImageResourseId;
    }

    public boolean hasImage() { return mImageResourseId != -1; }

    public int getmAudioResourseId() { return mAudioResourseId; }

}
