package com.blue_b.rest.view.Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue_b.rest.R;
import com.blue_b.rest.functions.Functions;
import com.blue_b.rest.model.SlidImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<SlidImage> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;
    ImageClicked imageClicked;

    public SlidingImage_Adapter(Context context, ArrayList<SlidImage> imageModelArrayList, ImageClicked imageClicked) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        this.imageClicked = imageClicked;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        final TextView numberOfImage = (TextView) imageLayout
                .findViewById(R.id.numberOfImage);

        Picasso.get()
                .load(imageModelArrayList.get(position).getImagePath())
                .fit()
                .centerCrop()
                .into(imageView);

        numberOfImage.setText(imageModelArrayList.get(position).getImageNumber()
                +"/"+imageModelArrayList.get(position).getTotalImage());

        numberOfImage.setTypeface(Functions.changeFontGeneral(context));

        view.addView(imageLayout, 0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageClicked.onImageClicked(true);
            }
        });

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public interface ImageClicked {
        void onImageClicked(Boolean clicked);
    }

}