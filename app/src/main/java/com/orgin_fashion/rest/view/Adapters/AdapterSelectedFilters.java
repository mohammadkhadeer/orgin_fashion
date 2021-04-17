package com.orgin_fashion.rest.view.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orgin_fashion.rest.R;
import com.orgin_fashion.rest.functions.Functions;
import com.orgin_fashion.rest.model.ItemSelectedFilterModel;

import java.util.ArrayList;

public class AdapterSelectedFilters extends RecyclerView.Adapter<AdapterSelectedFilters.ViewHolder>{

    private final Context context;
    public ArrayList<ItemSelectedFilterModel> filterContentArrayL ;
    String fromWhereCome;
    PassCancelItem passCancelItem;

    public AdapterSelectedFilters
            (Context context, ArrayList<ItemSelectedFilterModel> filterContentArrayL
            ,String fromWhereCome,PassCancelItem passCancelItem)
    {   this.context = context;
        this.filterContentArrayL = filterContentArrayL;
        this.fromWhereCome = fromWhereCome;
        this.passCancelItem = passCancelItem;
    }

    public AdapterSelectedFilters.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_selected_filter_content, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterSelectedFilters.ViewHolder holder, final int position) {
        holder.filterContentTV.setText(filterContentArrayL.get(position).getFilter());
        holder.filterContentTV.setTypeface(Functions.changeFontGeneral(context));
        actionListenerToCancel(context,position,holder);
    }

    private void actionListenerToCancel(final Context context, final int position, final ViewHolder holder) {
        holder.relativeLayoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.filterContentTV.getText().toString().equals(context.getResources().getString(R.string.all)))
                passCancelItem.onFilterClicked(filterContentArrayL.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterContentArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView filterContentTV;
        RelativeLayout relativeLayoutCancel;

        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);
            filterContentTV = (TextView) itemView.findViewById(R.id.adapter_filter_selected_content_text_view);
            relativeLayoutCancel = (RelativeLayout) itemView.findViewById(R.id.selected_filter_cancel_filter);
        }
    }

    public interface PassCancelItem {
        void onFilterClicked(ItemSelectedFilterModel itemSelectedFilterModel);

    }
}