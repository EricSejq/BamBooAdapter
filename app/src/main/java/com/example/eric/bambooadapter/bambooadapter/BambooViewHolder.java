package com.example.eric.bambooadapter.bambooadapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eric.bambooadapter.utils.GlideUtil;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description:
 * Data：2019/4/24-21:58
 *
 * @author: eric
 */
public class BambooViewHolder extends RecyclerView.ViewHolder {


    public static BambooViewHolder getBambooViewHolder(View itemView) {
        return getBambooViewHolder(itemView, null);
    }

    public static BambooViewHolder getBambooViewHolder(View itemView, OnItemClickListener onItemClickListener) {
        return new BambooViewHolder(itemView, onItemClickListener);
    }

    private BambooViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        if (onItemClickListener != null) {
            itemView.setOnClickListener(view -> {
                onItemClickListener.clickItem(itemView, getAdapterPosition());
            });
        }
    }


    public BambooViewHolder setBtnText(@IdRes int id, String text) {
        ((Button) itemView.findViewById(id)).setText(text);
        return this;
    }

    public BambooViewHolder addClickListener(@IdRes int id, View.OnClickListener clickListener) {
        itemView.findViewById(id).setOnClickListener(clickListener);
        return this;
    }

    public BambooViewHolder addItemClickListener(View.OnClickListener clickListener) {
        itemView.setOnClickListener(clickListener);
        return this;
    }

    public BambooViewHolder setTextViewText(@IdRes int id, String content) {
        ((TextView) itemView.findViewById(id)).setText(content);
        return this;
    }

    public BambooViewHolder setImageViewPic(@IdRes int id, int resId) {
        ((ImageView) itemView.findViewById(id)).setImageResource(resId);
        return this;
    }

    public View getView(@IdRes int viewId) {
        return itemView.findViewById(viewId);
    }

    public interface OnItemClickListener {
        /**
         * itemView 点击事件
         *
         * @param view     响应事件的控件
         * @param position 控件的position
         */
        void clickItem(View view, int position);
    }
}
