package com.flymr92gmail.sejonghangugeo.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;

import android.text.SpannableString;
import android.text.Spanned;

import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flymr92gmail.sejonghangugeo.POJO.Word;
import com.flymr92gmail.sejonghangugeo.R;
import com.flymr92gmail.sejonghangugeo.Utils.Constants;

import java.util.ArrayList;


public class SearchWordsAdapter extends RecyclerView.Adapter<SearchWordsAdapter.ViewHolder> {
    private ArrayList<Word> mWords;
    private Context mContext;
    private String searchingText;
    private Constants.Language language;

    public SearchWordsAdapter(ArrayList<Word> words, Context context, String searchingText, Constants.Language language) {
        this.mWords=words;
        this.mContext = context;
        this.searchingText = searchingText;
        this.language = language;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Word word = mWords.get(position);
        if (language == Constants.Language.Korean){
            Spannable kor = new SpannableString(word.getKoreanWord());
            int start = word.getKoreanWord().indexOf(searchingText);
            if (start < 0){
                start = word.getRussianWord().toLowerCase().indexOf(searchingText.toLowerCase());
            }
            kor.setSpan(new BackgroundColorSpan(mContext.getResources().getColor(R.color.green)), start,
                    start + searchingText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tvKorWord.setText(kor);
            holder.tvRusWord.setText(word.getRussianWord());
        }else {
            Spannable rus = new SpannableString(word.getRussianWord());
            int start = word.getRussianWord().indexOf(searchingText);
            if (start < 0){
                start = word.getRussianWord().toLowerCase().indexOf(searchingText.toLowerCase());
            }
            rus.setSpan(new BackgroundColorSpan(Color.GREEN), start,
                    start + searchingText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tvRusWord.setText(rus);
            holder.tvKorWord.setText(word.getKoreanWord());
        }
        Log.d("VIEWHOLDER", "onBindViewHolder: " + word.getCorrectCount());
    }
    @Override
    public int getItemCount () {
        return mWords.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvKorWord,tvRusWord;
        ImageView ivAdd;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvKorWord = itemView.findViewById(R.id.korean_word_tv);
            tvRusWord = itemView.findViewById(R.id.russian_word_tv);
            ivAdd = itemView.findViewById(R.id.iv_add);
            cardView = itemView.findViewById(R.id.word_item_cv);
            cardView.setCardBackgroundColor(Color.WHITE);

        }

    }
}
