package com.fashion.rest.view.Adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.MultiArea;

import java.util.ArrayList;

import static com.fashion.rest.functions.Functions.getTextEngOrLocal;

public class AdapterMultiArea extends RecyclerView.Adapter<AdapterMultiArea.ViewHolder> {

    private final Context context;
    public ArrayList<MultiArea> carOptionsArrayL;
    PassSelected passSelected;


    public AdapterMultiArea
            (Context context, ArrayList<MultiArea> carOptionsArrayL, PassSelected passSelected) {
        this.context = context;
        this.carOptionsArrayL = carOptionsArrayL;
        this.passSelected = passSelected;
    }

    public AdapterMultiArea.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_multi_area, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterMultiArea.ViewHolder holder, final int position) {

        holder.modelTV.setText(getTextEngOrLocal(context,carOptionsArrayL.get(position).getArea_en(),carOptionsArrayL.get(position).getArea_local()));
        checkIfAlredyCheckedOrNot(holder,position);
        actionListenerOption(holder,position);
        changeFont(holder);

    }

    private void changeFont(ViewHolder holder) {
        holder.modelTV.setTypeface(Functions.changeFontGeneral(context));
    }

    private void actionListenerOption(final ViewHolder holder, final int position) {
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carOptionsArrayL.get(position).getIsSelected() == 1)
                {
                    carOptionsArrayL.get(position).setIsSelected(0);
                    holder.checkBox.setChecked(false);
                    passSelected.onOptionClicked(carOptionsArrayL.get(position),position);
                    holder.relativeLayoutSplit.setVisibility(View.GONE);
                    reActiveSplit(context, holder);
                }else{
                    carOptionsArrayL.get(position).setIsSelected(1);
                    holder.checkBox
                            .setChecked(true);
                    passSelected.onOptionClicked(carOptionsArrayL.get(position),position);
                    holder.relativeLayoutSplit.setVisibility(View.GONE);
                    reActiveSplit(context, holder);
                }
            }

        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carOptionsArrayL.get(position).getIsSelected() == 1)
                {
                    carOptionsArrayL.get(position).setIsSelected(0);
                    holder.checkBox.setChecked(false);
                    passSelected.onOptionClicked(carOptionsArrayL.get(position),position);
                    holder.relativeLayoutSplit.setVisibility(View.GONE);
                    reActiveSplit(context, holder);
                }else{
                    carOptionsArrayL.get(position).setIsSelected(1);
                    holder.checkBox
                            .setChecked(true);
                    passSelected.onOptionClicked(carOptionsArrayL.get(position),position);
                    holder.relativeLayoutSplit.setVisibility(View.GONE);
                    reActiveSplit(context, holder);
                }
            }

        });
    }

    private void checkIfAlredyCheckedOrNot(ViewHolder holder, int position) {
        if(carOptionsArrayL.get(position).getIsSelected() == 0)
        {
            holder.checkBox.setChecked(false);

        }else{
            holder.checkBox.setChecked(true);
        }
    }

    private void reActiveSplit(Context context, final ViewHolder holder) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                holder.relativeLayoutSplit.setVisibility(View.VISIBLE);
            }
        }, 300);
    }

    @Override
    public int getItemCount() {
        return carOptionsArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView modelTV;
        RelativeLayout relativeLayout, relativeLayoutSplit, relativeLayoutSelect;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            modelTV = (TextView) itemView.findViewById(R.id.adapter_car_options_TV);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.adapter_car_options_container_RL);
            relativeLayoutSplit = (RelativeLayout) itemView.findViewById(R.id.adapter_car_options_split_TV);
            relativeLayoutSelect = (RelativeLayout) itemView.findViewById(R.id.adapter_car_options_select_RL);
            checkBox = (CheckBox) itemView.findViewById(R.id.imgQueueMultiSelected);
        }
    }

    public interface PassSelected {
        void onOptionClicked(MultiArea carOption, int position);
    }

    public void filterList(ArrayList<MultiArea> filterdNames) {
        this.carOptionsArrayL = filterdNames;
        notifyDataSetChanged();
    }

}