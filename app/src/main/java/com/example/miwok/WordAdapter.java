package com.example.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miwok.R;
import com.example.miwok.word;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<word> {

    private int mColorResourseId;

    public WordAdapter(Activity context, ArrayList<word> words , int Color) {
        super(context, 0, words);
        mColorResourseId = Color ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }

        word currentword = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentword.getmMewokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentword.getmDefaultTranslation());

        ImageView Image = (ImageView) listItemView.findViewById(R.id.image);
        if(currentword.hasImage()) {
            Image.setImageResource(currentword.getmImageResurseId());
            Image.setVisibility(View.VISIBLE);
        } else {
            Image.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.container);
        int color = ContextCompat.getColor(getContext(),mColorResourseId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
