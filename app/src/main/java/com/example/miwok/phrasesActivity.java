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

public class phrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("Where are you going?" , "minto wuksus" , R.raw.phrase_where_are_you_going));
        words.add(new word("What is your name?" , "tinnә oyaase'nә" , R.raw.phrase_what_is_your_name));
        words.add(new word("My name is..." , "oyaaset..." , R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?" , "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new word("I’m feeling good." , "kuchi achit" , R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming?" , "әәnәs'aa?" , R.raw.phrase_are_you_coming));
        words.add(new word("Yes, I’m coming." , "hәә’ әәnәm" , R.raw.phrase_yes_im_coming));
        words.add(new word("I’m coming." , "әәnәm" , R.raw.phrase_im_coming));
        words.add(new word("Let’s go." , "yoowutis" , R.raw.phrase_lets_go));
        words.add(new word("Come here." , "әnni'nem" , R.raw.phrase_come_here));

        WordAdapter myAdapter = new WordAdapter(this,words,R.color.category_phrases);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(myAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word w1 = words.get(i);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListenner, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(phrasesActivity.this, w1.getmAudioResourseId());
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
