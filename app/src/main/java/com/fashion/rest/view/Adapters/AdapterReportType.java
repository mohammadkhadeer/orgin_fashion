package com.fashion.rest.view.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.functions.Functions;
import com.fashion.rest.model.ReportType;

import java.util.ArrayList;

public class AdapterReportType extends RecyclerView.Adapter<AdapterReportType.ViewHolder> {

    private final Context context;
    public ArrayList<ReportType> reportTypesArrayL;
    PassReport passReport;

    public AdapterReportType
            (Context context, ArrayList<ReportType> reportTypesArrayL, PassReport passReport) {
        this.context = context;
        this.reportTypesArrayL = reportTypesArrayL;
        this.passReport = passReport;
    }

    public AdapterReportType.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_report_type, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterReportType.ViewHolder holder, final int position) {

        holder.imageView.setBackgroundResource(reportTypesArrayL.get(position).getReportImage());
        holder.textView.setText(reportTypesArrayL.get(position).getReport());
        holder.textView.setTypeface(Functions.changeFontGeneral(context));
        actionListenerToCard(holder, position, context);
    }

    private void actionListenerToCard(ViewHolder holder, final int position, Context context) {
        holder.radioRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passReport.onReportClicked(reportTypesArrayL.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return reportTypesArrayL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        RelativeLayout radioRL;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.adapter_report_type_tv);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_report_type_image);
            radioRL = (RelativeLayout) itemView.findViewById(R.id.adapter_report_type_radioRelative);
        }

    }

    public interface PassReport {
        void onReportClicked(ReportType carFuel);
    }
}