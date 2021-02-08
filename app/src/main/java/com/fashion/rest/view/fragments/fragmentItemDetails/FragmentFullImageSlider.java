package com.fashion.rest.view.fragments.fragmentItemDetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fashion.rest.R;
import com.fashion.rest.animations.EnterScreenAnimations;
import com.fashion.rest.animations.ExitScreenAnimations;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.yc.cn.ycgallerylib.gallery.GalleryImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class FragmentFullImageSlider extends Fragment {

    public FragmentFullImageSlider() {
    }

    ArrayList<String> allImage = new ArrayList<>();
    View view;
    private GalleryImageView scrollGalleryView;
    private ImageView mTransitionImage;
    private EnterScreenAnimations mEnterScreenAnimations;
    private ExitScreenAnimations mExitScreenAnimations;
    private TextView tag;

    //
    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            allImage = getArguments().getStringArrayList("allImage");
        }
        super.onAttach(context);
        //set userName in followID just as init value well need it to insert in
        //fireBase as object after added well updated
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_full_image_slider, container, false);

        scrollGalleryView = view.findViewById(R.id.scroll_gallery_view);
        final RelativeLayout mainContainer = view.findViewById(R.id.main_container);
        tag = view.findViewById(R.id.tag);
        FrameLayout androidContent = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        mTransitionImage = new ImageView(getActivity());
        androidContent.addView(mTransitionImage);

        mEnterScreenAnimations = new EnterScreenAnimations(mTransitionImage, scrollGalleryView, mainContainer);
        mExitScreenAnimations = new ExitScreenAnimations(mTransitionImage, scrollGalleryView, mainContainer);


        final int[] finalLocationOnTheScreen = new int[2];
        scrollGalleryView.getLocationOnScreen(finalLocationOnTheScreen);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mEnterScreenAnimations.playEnteringAnimation(
                finalLocationOnTheScreen[0], // left
                finalLocationOnTheScreen[1], // top
                scrollGalleryView.getWidth(),
                scrollGalleryView.getHeight());

        return view;
    }

    private Bitmap bitmap1;
    ConvertUrlToBitmap convertUrlToBitmap;

    @SuppressLint("SetTextI18n")
    private void init() throws IOException {
        tag.setText(1 + "/" + allImage.size());
        scrollGalleryView
                //设置viewPager底部缩略图大小尺寸
                .setThumbnailSize(200)
                //设置是否支持缩放
                .setZoom(true)
                //设置缩放的倍数
                .setZoomSize(3)
                //设置是否隐藏底部缩略图
                .hideThumbnails(false)
                //设置FragmentManager
                .setFragmentManager(getActivity().getSupportFragmentManager())
                //添加滑动事件，也可以不用添加
                .addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPageSelected(int position) {
                        scrollGalleryView.setCurrentItem(position);
                        tag.setText((position + 1) + "/" + allImage.size());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
        //添加单张图片


        for (int i = 0; i < allImage.size(); i++) {
            convertUrlToBitmap = (ConvertUrlToBitmap) new ConvertUrlToBitmap().execute(allImage.get(i));
        }


    }

    private Bitmap toBitmap(int image) {
        return ((BitmapDrawable) getResources().getDrawable(image)).getBitmap();
    }


    private class ConvertUrlToBitmap extends AsyncTask<String, Long, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            for (int i = 0; i < allImage.size(); i++) {
                // you need to break your loop on particular condition here

                if(isCancelled())
                    break;
            }

            try {
                URL url = new URL(params[0]);
                bitmap1 = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return true;
            } catch (Exception e) {
                Log.e("TAG", e.toString());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean) {
                // download was successful
                // if you want to update your UI, make sure u do it on main thread. like this:
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        scrollGalleryView.addBitmap(bitmap1);
                    }
                });
            } else {
                // download failed
            }
        }

    }

    public void diestroyAsynk(){
        convertUrlToBitmap.cancel(true);
    }

}
