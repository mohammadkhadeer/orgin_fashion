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
import java.util.ArrayList;

import static com.fashion.rest.functions.Functions.getTextEngOrLocal;

public class AdapterSelectedAreas extends RecyclerView.Adapter<AdapterSelectedAreas.ViewHolder>{

    private final Context context;
    ArrayList<Area> areaArrayL = new ArrayList<>();
    CancelArea cancelArea;
    String from;
    public AdapterSelectedAreas
            (Context context,ArrayList<Area> areaArrayL,CancelArea cancelArea,String from)
                {
                    this.context = context;
                    this.areaArrayL = areaArrayL;
                    this.cancelArea = cancelArea;
                    this.from = from;
                }

    public AdapterSelectedAreas.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view;
        if (from.equals("all_filter"))
        {
            view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.adapter_selected_areas_small, viewGroup, false);
        }else{
            view = LayoutInflater.from(viewGroup.getContext()).
                    inflate(R.layout.adapter_selected_areas, viewGroup, false);
        }
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

    public void removeAllArea() {
        int size = areaArrayL.size();
        areaArrayL.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public void onBindViewHolder(final AdapterSelectedAreas.ViewHolder holder, final int position) {

        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToAddToCart(holder,context,position);
    }

    private void actionListenerToAddToCart(ViewHolder holder, final Context context, final int position) {
        holder.selected_area_cancel_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelArea.onClickedCancel(areaArrayL.get(position));
            }
        });
    }


    private void fillText(ViewHolder holder, Context context, int position) {
        holder.selectedAreaTV.setText(getTextEngOrLocal(context,areaArrayL.get(position).getName_en(),areaArrayL.get(position).getName_local()));
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.selectedAreaTV.setTypeface(Functions.changeFontGeneral(context));
    }

    @Override
    public int getItemCount() {
        return areaArrayL.size();
    }

    public interface CancelArea {
        void onClickedCancel(Area area);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView selectedAreaTV;
        RelativeLayout cover_relativeLayout,selected_area_cancel_area;
        @SuppressLint("WrongViewCast")
        public ViewHolder(View itemView) {
            super(itemView);
            selectedAreaTV = (TextView) itemView.findViewById(R.id.adapter_selected_area_text_view);
            cover_relativeLayout = (RelativeLayout) itemView.findViewById(R.id.adapter_city_and_areas_coverRL);
            selected_area_cancel_area = (RelativeLayout) itemView.findViewById(R.id.selected_area_cancel_area);
        }
    }
}