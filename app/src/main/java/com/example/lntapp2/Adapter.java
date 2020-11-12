package com.example.lntapp2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lntapp2.database.FeedReaderContract;

/**
 * is to put data into each row of the listview
 */
public class Adapter  extends RecyclerView.Adapter<Adapter.wordViewHolder>{

    //String[] languages;
    Cursor languagesCursor;
    LayoutInflater layoutInflater;
    int titleIndex,subtitleIndex;
//    public Adapter(Context context, String[] languagesData) {
//        languages = languagesData;
//        layoutInflater =LayoutInflater.from(context);
//
//    }
public Adapter(Context context, Cursor cursor){
    //String[] languagesData) {
    languagesCursor = cursor;
    layoutInflater = LayoutInflater.from(context);
    titleIndex = languagesCursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE);
    subtitleIndex = languagesCursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE);
}
    /**
     * onCreateViewHolder job  is to buy wooden planks
     * @param parent
     * @param viewType
     * @return
     */

    @NonNull
    @Override
    public Adapter.wordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rowView = layoutInflater.inflate(R.layout.row_listview,parent,false);
        return new wordViewHolder(rowView);
    }

    /**
     * onBindViewHolder job is write data on the planks
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull Adapter.wordViewHolder holder, int position) {

       // holder.titleTextView.setText(languages[position]);
        if(position+1 < languagesCursor.getCount()) {
            languagesCursor.move(position + 1);
            String title = languagesCursor.getString(titleIndex);
            String subtitle = languagesCursor.getString(subtitleIndex);

            holder.titleTextView.setText(title);
            holder.subtitleTextView.setText(subtitle);
        }
    }

    /**
     * it will keep the count of number of data items in the dataset
     * @return
     */
    @Override
    public int getItemCount(){

         //return languages.length;
        return languagesCursor.getCount()+1;
    }

    /**
     * to hold the recycle stock and new stock of wooden planks
     */
    public class wordViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public  TextView subtitleTextView;
        public wordViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textView);
            subtitleTextView = itemView.findViewById(R.id.textViewsubtitle);
        }
    }
}
