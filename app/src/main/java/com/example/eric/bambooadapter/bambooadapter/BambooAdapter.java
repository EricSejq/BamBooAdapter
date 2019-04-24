package com.example.eric.bambooadapter.bambooadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description:
 * Data：2019/4/24-21:49
 *
 * @author: eric
 */
public class BambooAdapter<T> extends RecyclerView.Adapter<BambooViewHolder> {
    private List<T> data;
    private int mLayoutId;
    private Context mContext;
    private int mHeadCount;
    private int mFootCount;
    private SparseArray<Integer> mHeadArray = new SparseArray<>();
    private SparseArray<Integer> mFootArray = new SparseArray<>();
    private final int mHeadBase = 1000;
    private final int mFootBase = 2000;

    public BambooAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public BambooViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i >= mHeadBase && i < mFootBase) {
            view = LayoutInflater.from(mContext).inflate(mHeadArray.get(i), viewGroup, false);
            return BambooViewHolder.getBambooViewHolder(view);
        } else if (i >= mFootBase) {
            view = LayoutInflater.from(mContext).inflate(mFootArray.get(i), viewGroup, false);
            return BambooViewHolder.getBambooViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(mLayoutId, viewGroup, false);
            return BambooViewHolder.getBambooViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BambooViewHolder bambooViewHolder, int i) {
        if (mBindListener != null) {
            if (isHead(i)) {
                mBindListener.onBindHead(bambooViewHolder, getHeadPosition(i));
            } else if (isNormal(i)) {
                mBindListener.onBindNormal(bambooViewHolder, data.get(i - mHeadCount), getNormalItemPosition(i));
            } else if (isFoot(i)) {
                mBindListener.onBindFoot(bambooViewHolder, getFootPosition(i));
            }
        }
    }


    @Override
    public int getItemCount() {
        return (data == null ? 0 : data.size()) + mHeadCount + mFootCount;
    }


    public BambooAdapter addNormal(@LayoutRes int layoutId) {
        this.mLayoutId = layoutId;
        return this;
    }

    public BambooAdapter addNormalData(List<T> data) {
        this.data = data;
        return this;
    }

    public BambooAdapter onNormalBindListener(BindListener bindListener) {
        this.mBindListener = bindListener;
        return this;
    }

    public BambooAdapter build() {
        notifyDataSetChanged();
        return this;
    }


    private BindListener<T> mBindListener;

    public interface BindListener<T> {
        /**
         * 绑定item数据
         * BindListener下的三个接口中的position参数，皆为当前类型的index
         *
         * @param bambooViewHolder bambooViewHolder 对象
         * @param data             展示的item数据
         * @param position         item 的位置   0 1 2
         * @return void
         */
        void onBindNormal(BambooViewHolder bambooViewHolder, T data, int position);

        /**
         * 绑定head数据
         *
         * @param bambooViewHolder bambooViewHolder 对象
         * @param position         第几个head  0 1 2
         */
        void onBindHead(BambooViewHolder bambooViewHolder, int position);

        /**
         * 绑定脚数据
         *
         * @param bambooViewHolder bambooViewHolder 对象
         * @param position         第几个foot 0 1 2
         */
        void onBindFoot(BambooViewHolder bambooViewHolder, int position);
    }

    public BambooAdapter addNormalItemClickListener(BambooViewHolder.OnItemClickListener onClickListener) {
        this.mItemClickListener = onClickListener;
        return this;
    }

    public BambooAdapter addHead(@LayoutRes int headLayout) {
        mHeadArray.append(mHeadBase + mHeadCount, headLayout);
        mHeadCount++;
        return this;
    }

    public BambooAdapter addFoot(@LayoutRes int footLayout) {
        mFootArray.append(mFootBase + mFootCount, footLayout);
        mFootCount++;
        return this;
    }

    private BambooViewHolder.OnItemClickListener mItemClickListener;


    private boolean isNormal(int position) {
        return position >= mHeadCount && position < (data.size() + mHeadCount);
    }

    private boolean isHead(int position) {
        return position < mHeadCount;
    }

    private boolean isFoot(int position) {
        return position >= (data.size() + mHeadCount);
    }


    @Override
    public int getItemViewType(int position) {
        if (isHead(position)) {
            return mHeadBase + position;
        } else if (isFoot(position)) {
            return mFootBase + position - mHeadCount - data.size();
        } else {
            return 1;
        }
    }

    private int getHeadPosition(int position) {
        return position;
    }

    private int getNormalItemPosition(int position) {
        return position - mHeadCount;
    }

    private int getFootPosition(int position) {
        return position - mHeadCount - (data == null ? 0 : data.size());
    }

    public void removeItem(int position) {
        if ((position - mHeadCount) <= data.size()) {
            data.remove(position - mHeadCount);
            notifyItemRemoved(position);
        }
    }
}
