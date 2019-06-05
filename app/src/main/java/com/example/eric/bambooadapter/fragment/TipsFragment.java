package com.example.eric.bambooadapter.fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.eric.bambooadapter.R;
import com.example.eric.bambooadapter.bambooadapter.BambooAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eric
 * @date
 */
public class TipsFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match


    public TipsFragment() {
        // Required empty public constructor
    }

    public static TipsFragment newInstance() {
        TipsFragment fragment = new TipsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tips;
    }

    @Override
    protected void initView(View view) {
        List list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add(String.valueOf(i));
        }
        RecyclerView tips_rv = view.findViewById(R.id.tips_rv);
        tips_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        tips_rv.setAdapter(
                new BambooAdapter(getContext())
                        .addNormal(R.layout.item_tips)
                        .addNormalData(list)
                        .addDecoration(tips_rv, new RecyclerView.ItemDecoration() {
                            @Override
                            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                                super.getItemOffsets(outRect, view, parent, state);
                                outRect.set(10, 10, 10, 10);
                            }
                        })
                        
                        .build()
        );
    }

    @Override
    protected void initData() {

    }
}
