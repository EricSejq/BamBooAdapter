package com.example.eric.bambooadapter.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eric.bambooadapter.R;

import androidx.annotation.IdRes;

/**
 * Description:
 * Data：2019/4/27-21:27
 *
 * @author: eric
 */
public class GlideUtil {

    public static void loadResImage(ImageView view, @IdRes int resId) {
        Glide
                .with(view.getContext())
                .load(resId)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }
}
