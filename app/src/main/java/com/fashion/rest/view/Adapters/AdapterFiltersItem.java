package com.fashion.rest.view.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.ItemFilterModel;
import java.util.ArrayList;

public class AdapterFiltersItem extends RecyclerView.Adapter<AdapterFiltersItem.ViewHolder>{

    PassFilter passFilter;
    private final Context context;
    public ArrayList<ItemFilterModel> filterContentArrayL ;
    String filterType;
    //Neighborhood

    public AdapterFiltersItem
            (Context context, ArrayList<ItemFilterModel> filterContentArrayL
            ,String filterType,PassFilter passFilter)
    {   this.context = context;
        this.filterContentArrayL = filterContentArrayL;
        this.filterType = filterType;
        this.passFilter = passFilter;
    }

    public AdapterFiltersItem.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_filter_content, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterFiltersItem.ViewHolder holder, final int position) {
        holder.filterContentTV.setText(filterContentArrayL.get(position).getFilter());
        holder.filterContentTV.setTypeface(Functions.changeFontGeneral(context));
        actionListenerToCardView(context,position,holder);
    }

    private void actionListenerToCardView(Context context, final int position, ViewHolder holder) {
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passFilter.onFilterClicked(filterContentArrayL.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterContentArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView filterContentTV;
        RelativeLayout relativeLayout;
        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);
            filterContentTV = (TextView) itemView.findViewById(R.id.adapter_filter_content_text_view);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.coverRL);
        }
    }

    public interface PassFilter {
        void onFilterClicked(ItemFilterModel itemFilterModel);
    }

}