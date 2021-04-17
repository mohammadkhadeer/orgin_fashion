package com.orgin_fashion.rest.view.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.orgin_fashion.rest.R;

public class AdapterLoadingType2 extends RecyclerView.Adapter<AdapterLoadingType2.ViewHolder>{

    private final Context context;

    public AdapterLoadingType2
            (Context context)
                {
                     this.context = context;
                }

    public AdapterLoadingType2.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_loading_type2, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterLoadingType2.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_suggested_to_you_IV) ;
        }

    }

}