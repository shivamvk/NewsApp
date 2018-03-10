package com.example.shivamvk.recyclerview;


import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ListItem listItem = listItems.get(position);

        holder.Heading.setText(listItem.getmHeading());
        holder.Description.setText(listItem.getmDescription());

        Picasso.get()
                .load(listItem.getmImageURL())
                .placeholder(R.drawable.user_placeholder)
                .into(holder.Image);

        holder.Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listItem.getmDetailURL()));
                    context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Heading;
        public TextView Description;
        public ImageView Image;
        public LinearLayout Layout;

        public ViewHolder(View itemView) {
            super(itemView);

            Heading = itemView.findViewById(R.id.heading);
            Description = itemView.findViewById(R.id.description);
            Image = itemView.findViewById(R.id.imageview);
            Layout = itemView.findViewById(R.id.linear_layout);
        }
    }
}
