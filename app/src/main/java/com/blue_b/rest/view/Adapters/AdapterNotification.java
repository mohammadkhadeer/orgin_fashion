package com.blue_b.rest.view.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blue_b.rest.R;
import com.blue_b.rest.database.DBHelper;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.NotificationModel;
import com.blue_b.rest.view.activity.AboutUs;
import com.blue_b.rest.database.DataBaseInstance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.ViewHolder>{

    private final Context context;
    public ArrayList<NotificationModel> notificationCompsArrayL ;
    DBHelper dbHelper;
    private static final long FIVE_MINUTES = 1000 * 60 * 5; //5 minutes in milliseconds

    public AdapterNotification
            (Context context, ArrayList<NotificationModel> notificationCompsArrayL)
    {   this.context = context;
        this.notificationCompsArrayL = notificationCompsArrayL;
    }

    public AdapterNotification.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_notification, viewGroup, false);
        dbHelper = DataBaseInstance.getDataBaseInstance(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterNotification.ViewHolder holder, final int position) {
        changeFont(context, holder);
        changeNotificationColorIfUserOpen(position,holder,context);
        fillTextHeadAndDes(context,position,holder);
        fillProcessImageAndUserUserImage(context,holder,position);
        actionListenerToNotification(context,holder,position);
        actionListenerToMenu(context,position,holder);
    }

    private void actionListenerToMenu(final Context context, final int position, final ViewHolder holder) {
        holder.adapter_notification_menu_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,notificationCompsArrayL.get(position).getTitle_en(),Toast.LENGTH_SHORT).show();
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, holder.adapter_notification_menu_rl);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_notification);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_delete:
                                //handle menu1 click
                                deleteNotification(position);
                                removeItem(position);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();
            }
        });
    }

    private void removeItem(int position) {
        if (notificationCompsArrayL.get(position) != null) {
            notificationCompsArrayL.remove(position);
            notifyItemRemoved(position);
        }
    }


    private void fillProcessImageAndUserUserImage(Context context, ViewHolder holder, int position) {
        Picasso.get()
                .load(notificationCompsArrayL.get(position).getImage_url())
                .fit()
                .centerCrop()
                .into(holder.processIV);

    }

    private void actionListenerToNotification(final Context context, final ViewHolder holder, final int position) {
        holder.coverRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.coverRL.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
                updateNotificationToOpen(context,position);
                transporteToShowItemSelectedDetails(context,position,holder);
            }
        });
    }

    private void updateNotificationToOpen(Context context, int position) {
        dbHelper.updateNotificationStatus("1",notificationCompsArrayL.get(position).getTime_stamp());
    }

    private void deleteNotification(int position) {
        dbHelper.deleteNotification(notificationCompsArrayL.get(position).getTime_stamp());
    }


    private void transporteToShowItemSelectedDetails(Context context, int position, ViewHolder holder) {
        if (notificationCompsArrayL.get(position).getNotification_type().equals("welcome_screen") || notificationCompsArrayL.get(position).getNotification_type().equals("important_message"))
        {
            Intent intent = new Intent(context, AboutUs.class);
            intent.putExtra("item_object", notificationCompsArrayL.get(position));
            ((Activity)context).startActivity(intent);
            ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
        }
        //String cat =notificationCompsArrayL.get(position).getCat();

//        Bundle bundle = new Bundle();
//        bundle.putString("itemID","itemID");
//        bundle.putString("itemName","itemName");
//        bundle.putString("cat","offers");
//        bundle.putString("cat_type","png_image");
//        bundle.putString("from","not");
//
//        Intent intent = new Intent(context, ItemDetails.class);
//        intent.putExtras(bundle);
//        ((Activity)context).startActivity(intent);
//        ((Activity)context).overridePendingTransition(R.anim.right_to_left, R.anim.no_animation);
    }

    private void fillTextHeadAndDes(Context context, int position, ViewHolder holder) {
        holder.notificationTitleTV.setText(
                Functions.getTextEngOrLocal(context,notificationCompsArrayL.get(position).getTitle_en(),notificationCompsArrayL.get(position).getTitle_local())
        );
        String notificationDes = null;

//        if (notificationCompsArrayL.get(position).getInOrOut().equals("welcome")) {
//            notificationDes = context.getResources().getString(R.string.welcome_notifications);
//        }

        holder.notificationDesTV.setText(
                Functions.getTextEngOrLocal(context,notificationCompsArrayL.get(position).getDes_en(),notificationCompsArrayL.get(position).getDes_local())
        );


        String time = Functions.getTimeDiff(notificationCompsArrayL.get(position).getTime_stamp(),context);
        if (time.equals(context.getResources().getString(R.string.just_now)))
        {
            holder.notification_time_tv.setText(time);
            holder.notification_time_tv.setTextColor(Color.parseColor("#0581E2"));
        }else{
//            holder.notification_time_tv.setTextColor(Color.parseColor("#e0e0e0"));
            holder.notification_time_tv.setText(time);

        }

    }

    private void changeNotificationColorIfUserOpen(int position, ViewHolder holder,Context context) {
        if (notificationCompsArrayL.get(position).getOpen_or_not().equals("0"))
        {
            holder.coverRL.setBackgroundColor(ContextCompat.getColor(context, R.color.notificationNotOpenYet));
        }else{
            holder.coverRL.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
        }
    }

    private void changeFont(Context context, ViewHolder holder) {
        holder.notificationTitleTV.setTypeface(Functions.changeFontBold(context));
        holder.notificationDesTV.setTypeface(Functions.changeFontGeneral(context));
        holder.notification_time_tv.setTypeface(Functions.changeFontGeneral(context));
    }

    @Override
    public int getItemCount() {
        return notificationCompsArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView processIV;
        TextView notificationTitleTV,notificationDesTV,notification_time_tv;
        RelativeLayout coverRL,adapter_notification_menu_rl;
        public ViewHolder(View itemView) {
            super(itemView);
            notificationTitleTV = (TextView) itemView.findViewById(R.id.adapter_notification_head_tv);
            notificationDesTV = (TextView) itemView.findViewById(R.id.adapter_notification_des_tv);
            notification_time_tv = (TextView) itemView.findViewById(R.id.adapter_notification_time_tv);
            processIV = (ImageView) itemView.findViewById(R.id.adapter_notification_process_iv) ;
            coverRL = (RelativeLayout) itemView.findViewById(R.id.adapter_notification_cover) ;
            adapter_notification_menu_rl = (RelativeLayout) itemView.findViewById(R.id.adapter_notification_menu_rl) ;
        }
    }

}