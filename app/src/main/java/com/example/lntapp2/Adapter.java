package com.example.lntapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * is to put data into each row of the listview
 */
public class Adapter  extends RecyclerView.Adapter<Adapter.wordViewHolder>{

    String[] languages;
    LayoutInflater layoutInflater;

    public Adapter(Context context, String[] languagesData) {
        languages = languagesData;
        layoutInflater =LayoutInflater.from(context);

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

        holder.titleTextView.setText(languages[position]);

    }

    /**
     * it will keep the count of number of data items in the dataset
     * @return
     */
    @Override
    public int getItemCount() {
        return languages.length;
    }

    /**
     * to hold the recycle stock and new stock of wooden planks
     */
    public class wordViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public wordViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textView);
        }
    }
}
