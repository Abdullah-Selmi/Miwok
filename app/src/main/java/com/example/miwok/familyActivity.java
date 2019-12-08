package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class familyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListenner =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("father", "әpә" , R.drawable.family_father , R.raw.family_father));
        words.add(new word("mother" , "әṭa" , R.drawable.family_mother , R.raw.family_mother));
        words.add(new word("son" , "angsi" , R.drawable.family_son , R.raw.family_son));
        words.add(new word("daughter" , "tune" , R.drawable.family_daughter , R.raw.family_daughter));
        words.add(new word("older brother" , "taachi" , R.drawable.family_older_brother , R.raw.family_older_brother));
        words.add(new word("younger brother" , "chalitti" , R.drawable.family_younger_brother , R.raw.family_younger_brother));
        words.add(new word("older sister" , "teṭe" , R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new word("younger sister" , "kolliti" , R.drawable.family_younger_sister , R.raw.family_younger_sister));
        words.add(new word("grandmother" , "ama" , R.drawable.family_grandmother , R.raw.family_grandmother));
        words.add(new word("grandfather" , "paapa" , R.drawable.family_grandfather , R.raw.family_grandfather));

        WordAdapter myAdapter = new WordAdapter(this,words,R.color.category_family);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(myAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word w1 = words.get(i);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListenner, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(familyActivity.this, w1.getmAudioResourseId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });
    }
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListenner);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
