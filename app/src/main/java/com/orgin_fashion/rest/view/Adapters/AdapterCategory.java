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
import com.orgin_fashion.rest.model.Area;
import com.orgin_fashion.rest.model.Categories;
import java.util.ArrayList;
import static com.orgin_fashion.rest.functions.Functions.getTextEngOrLocal;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder>{

    private final Context context;
//    List<Offer> mList = new ArrayList<>();
    ArrayList<Categories> catArrayL = new ArrayList<>();
    PassCategory passCat;
    public AdapterCategory
            (Context context,ArrayList<Categories> catArrayL,PassCategory passCat)
                {
                    this.context = context;
                    this.catArrayL = catArrayL;
                    this.passCat = passCat;
                }

    public AdapterCategory.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_filter_city_and_areas, viewGroup, false);
        return new ViewHolder(view);
    }

    public void removeArea(Area area) {
        for (int i=0;i<catArrayL.size();i++)
        {
            if (area.getName_en().equals(catArrayL.get(i).getName()))
            {
                catArrayL.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }

    @Override
    public void onBindViewHolder(final AdapterCategory.ViewHolder holder, final int position) {

        changeFont(holder,context);
        fillText(holder,context,position);
        actionListenerToAddToCart(holder,context,position);
    }

    private void actionListenerToAddToCart(ViewHolder holder, final Context context, final int position) {
        holder.cover_relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passCat.onClickedCategory(catArrayL.get(position));
            }
        });
    }


    private void fillText(ViewHolder holder, Context context, int position) {
        holder.cityOrAreaTV.setText(getTextEngOrLocal(context,catArrayL.get(position).getName(),catArrayL.get(position).getName_local()));
    }

    private void changeFont(ViewHolder holder, Context context) {
        holder.cityOrAreaTV.setTypeface(Functions.changeFontGeneral(context));
    }

    @Override
    public int getItemCount() {
        return catArrayL.size();
    }

    public interface PassCategory {
        void onClickedCategory(Categories category);
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