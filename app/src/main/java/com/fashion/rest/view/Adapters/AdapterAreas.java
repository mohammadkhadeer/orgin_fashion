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
import com.fashion.rest.model.Area;
import com.fashion.rest.model.City;

import java.util.ArrayList;

import static com.fashion.rest.functions.Functions.getTextEngOrLocal;

public class AdapterAreas extends RecyclerView.Adapter<AdapterAreas.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<Area> areaArrayL = new ArrayList<>();
    PassArea passArea;
    public AdapterAreas
            (Context context,ArrayList<Area> areaArrayL,PassArea passArea)
                {
                    this.context = context;
                    this.areaArrayL = areaArrayL;
                    this.passArea = passArea;
                }

    public AdapterAreas.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_filter_city_and_areas, viewGroup, false);
        return new ViewHolder(view);
    }

    public void removeArea(Area area) {
        for (int i=0;i<areaArrayL.size();i++)
        {
            if (area.getName_en().equals(areaArrayL.get(i).getName_en()))
            {
                areaArrayL.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }

    @Override
    public void onBindViewHolder(final AdapterAreas.ViewHolder holder, final int position) {

        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToAddToCart(holder,context,position);
    }

    private void actionListenerToAddToCart(ViewHolder holder, final Context context, final int position) {
        holder.cover_relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passArea.onClicked(areaArrayL.get(position));
            }
        });
    }


    private void fillText(ViewHolder holder, Context context, int position) {
        holder.cityOrAreaTV.setText(getTextEngOrLocal(areaArrayL.get(position).getName_en(),areaArrayL.get(position).getName_local()));
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.cityOrAreaTV.setTypeface(Functions.changeFontGeneral(context));
    }

    @Override
    public int getItemCount() {
        return areaArrayL.size();
    }

    public interface PassArea {
        void onClicked(Area area);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityOrAreaTV;
        RelativeLayout cover_relativeLayout;
        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);
            cityOrAreaTV = (TextView) itemView.findViewById(R.id.adapter_city_and_areas_text_view);
            cover_relativeLayout = (RelativeLayout) itemView.findViewById(R.id.adapter_city_and_areas_coverRL);
        }
    }
}