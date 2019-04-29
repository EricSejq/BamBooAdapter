package com.example.eric.bambooadapter.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.eric.bambooadapter.R;
import com.example.eric.bambooadapter.activity.DiaryDetailActivity;
import com.example.eric.bambooadapter.bambooadapter.BambooAdapter;
import com.example.eric.bambooadapter.bambooadapter.BambooViewHolder;
import com.example.eric.bambooadapter.utils.StringUtils;


import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class DiaryFragment extends BaseFragment {

    private RecyclerView diary_rv;

    public DiaryFragment() {
        // Required empty public constructor
    }


    public static DiaryFragment newInstance() {
        DiaryFragment fragment = new DiaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_diary;
    }

    @Override
    protected void initView(View view) {
        diary_rv = view.findViewById(R.id.diary_rv);
    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        diary_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        diary_rv.setAdapter(
                new BambooAdapter<String>(getContext())
                        .addNormal(R.layout.diary_item)
                        .addNormalData(list)
                        .onNormalBindListener(new BambooAdapter.BindListener<String>() {
                            @Override
                            public void onBindNormal(BambooViewHolder bambooViewHolder, String data, int position) {
                                bambooViewHolder
                                        .setTextViewText(R.id.diary_item_title, data)
                                        .setImageViewPic(R.id.diary_item_pic, R.drawable.pikaqiu)
                                        .setTextViewText(R.id.diary_item_time, StringUtils.getDateString(System.currentTimeMillis()))
                                        .addClickListener(R.id.diary_item_pic, view ->
                                                DiaryDetailActivity
                                                        .startWithShareAnimation(getActivity(), data, (ImageView) bambooViewHolder.getView(R.id.diary_item_pic)));
                            }

                            @Override
                            public void onBindHead(BambooViewHolder bambooViewHolder, int position) {

                            }

                            @Override
                            public void onBindFoot(BambooViewHolder bambooViewHolder, int position) {

                            }
                        })
                        .build()
        );
    }

}
