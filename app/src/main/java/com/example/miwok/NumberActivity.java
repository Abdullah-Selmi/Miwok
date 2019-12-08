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

public class NumberActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_number);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("one" , "lutti" , R.drawable.number_one , R.raw.number_one));
        words.add(new word("two" , "otiiko" , R.drawable.number_two , R.raw.number_two));
        words.add(new word("three" , "tolookosu" , R.drawable.number_three , R.raw.number_three));
        words.add(new word("four" , "oyyisa" , R.drawable.number_four , R.raw.number_four));
        words.add(new word("five" , "massokka" , R.drawable.number_five , R.raw.number_five));
        words.add(new word("six" , "temmokka" , R.drawable.number_six , R.raw.number_six));
        words.add(new word("seven" , "kenekaku" , R.drawable.number_seven , R.raw.number_seven));
        words.add(new word("eight" , "kawinta" , R.drawable.number_eight , R.raw.number_eight));
        words.add(new word("nine" , "wo'e" , R.drawable.number_nine , R.raw.number_nine));
        words.add(new word("ten" , "na'aacha" , R.drawable.number_ten , R.raw.number_ten));

        WordAdapter myAdapter = new WordAdapter(this,words , R.color.category_numbers);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(myAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word w1 = words.get(i);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListenner, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(NumberActivity.this, w1.getmAudioResourseId());
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
