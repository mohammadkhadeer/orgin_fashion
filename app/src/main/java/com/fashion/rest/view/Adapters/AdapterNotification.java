package com.fashion.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.Notification;
import com.fashion.rest.view.activity.ItemDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.ViewHolder>{

    private final Context context;
    public ArrayList<Notification> notificationCompsArrayL ;

    public AdapterNotification
            (Context context, ArrayList<Notification> notificationCompsArrayL)
    {   this.context = context;
        this.notificationCompsArrayL = notificationCompsArrayL;
    }

    public AdapterNotification.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_notification, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterNotification.ViewHolder holder, final int position) {
        changeFont(context, holder);
        changeNotificationColorIfUserOpen(position,holder,context);
        fillTextHeadAndDes(context,position,holder);
        fillProcessImageAndUserUserImage(context,holder,position);
        actionListenerToNotification(context,holder,position);
    }



    private void fillProcessImageAndUserUserImage(Context context, ViewHolder holder, int position) {
        if (position ==0)
        {
            Picasso.get()
                    .load(R.drawable.circl_silver_bg)
                    .fit()
                    .centerCrop()
                    .into(holder.processIV);
        }else {
            Picasso.get()
                    .load(notificationCompsArrayL.get(position).getImagePath())
                    .fit()
                    .centerCrop()
                    .into(holder.processIV);
        }

    }

    private void actionListenerToNotification(final Context context, final ViewHolder holder, final int position) {
        holder.coverRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.coverRL.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
                transporteToShowItemSelectedDetails(context,position,holder);
            }
        });
    }


    private void transporteToShowItemSelectedDetails(Context context, int position, ViewHolder holder) {
        String cat =notificationCompsArrayL.get(position).getCat();

        Bundle bundle = new Bundle();
        bundle.putString("itemID","itemID");
        bundle.putString("itemName","itemName");
        bundle.putString("cat","offers");
        bundle.putString("cat_type","png_image");
        bundle.putString("from","not");

        Intent intent = new Intent(context, ItemDetails.class);
        intent.putExtras(bundle);
        ((Activity)context).startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }

    private void fillTextHeadAndDes(Context context, int position, ViewHolder holder) {
        holder.notificationTitleTV.setText(notificationCompsArrayL.get(position).getNotificationTitle());
        String notificationDes = null;

//        if (notificationCompsArrayL.get(position).getInOrOut().equals("welcome")) {
//            notificationDes = context.getResources().getString(R.string.welcome_notifications);
//        }

        holder.notificationDesTV.setText(notificationCompsArrayL.get(position).getNotificationDes());
    }

    private void changeNotificationColorIfUserOpen(int position, ViewHolder holder,Context context) {
        if (notificationCompsArrayL.get(position).getOpenOrNotYet().equals("0"))
        {
            holder.coverRL.setBackgroundColor(ContextCompat.getColor(context, R.color.notificationNotOpenYet));
        }else{
            holder.coverRL.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
        }
    }

    private void changeFont(Context context, ViewHolder holder) {
        holder.notificationTitleTV.setTypeface(Functions.changeFontBold(context));
        holder.notificationDesTV.setTypeface(Functions.changeFontGeneral(context));
    }

    @Override
    public int getItemCount() {
        return notificationCompsArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView processIV;
        TextView notificationTitleTV,notificationDesTV;
        RelativeLayout coverRL;
        public ViewHolder(View itemView) {
            super(itemView);
            notificationTitleTV = (TextView) itemView.findViewById(R.id.adapter_notification_head_tv);
            notificationDesTV = (TextView) itemView.findViewById(R.id.adapter_notification_des_tv);
            processIV = (ImageView) itemView.findViewById(R.id.adapter_notification_process_iv) ;
            coverRL = (RelativeLayout) itemView.findViewById(R.id.adapter_notification_cover) ;
        }
    }

}