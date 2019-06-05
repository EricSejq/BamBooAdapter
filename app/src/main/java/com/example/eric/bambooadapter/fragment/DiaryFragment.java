package com.example.eric.bambooadapter.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.eric.bambooadapter.R;
import com.example.eric.bambooadapter.activity.DiaryDetailActivity;
import com.example.eric.bambooadapter.bambooadapter.BambooAdapter;
import com.example.eric.bambooadapter.bambooadapter.BambooViewHolder;
import com.example.eric.bambooadapter.data.DBBean.DiaryBean;
import com.example.eric.bambooadapter.data.DBBean.DiaryBean_;
import com.example.eric.bambooadapter.data.rxUtils.BaseControlInterface;
import com.example.eric.bambooadapter.data.rxUtils.RxUtils;
import com.example.eric.bambooadapter.utils.StringUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.objectbox.Box;
import io.reactivex.disposables.Disposable;

/**
 * @author eric
 */
public class DiaryFragment extends BaseFragment {

    private RecyclerView diary_rv;
    private SmartRefreshLayout refresh_srl;
    private FloatingActionButton fab_add;

    public DiaryFragment() {
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
        refresh_srl = view.findViewById(R.id.refresh_srl);
        fab_add = view.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(v -> refreshDiary());
    }

    /**
     * 刷新日记列表
     */
    private void refreshDiary() {
    }

    @Override
    protected void initData() {
        RxUtils.ControlDiary(new BaseControlInterface<Box<DiaryBean>>() {
            @Override
            public void onControl(Box<DiaryBean> data) {
                bindDiaryAdapter(
                        data.query()
//                                .equal(DiaryBean_.userName, "eric")
                                .build()
                                .find()
                );

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mDisposable = disposable;
            }
        });
    }

    private void bindDiaryAdapter(List<DiaryBean> list) {
        diary_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        diary_rv.setAdapter(
                new BambooAdapter<String>(getContext())
                        .addNormal(R.layout.diary_item)
                        .addNormalData(list)
                        .onNormalBindListener(new BambooAdapter.BindListener<DiaryBean>() {
                            @Override
                            public void onBindNormal(BambooViewHolder bambooViewHolder, DiaryBean data, int position) {
                                bambooViewHolder
                                        .setTextViewText(R.id.diary_item_title, data.getDiarytitle())
                                        .setImageViewPic(R.id.diary_item_pic, R.drawable.pikaqiu)
                                        .setTextViewText(R.id.diary_item_time, StringUtils.getDateString(data.getCreateTime()))
                                        .addClickListener(R.id.diary_item_pic, view ->
                                                DiaryDetailActivity
                                                        .startWithShareAnimation(getActivity(), data, (ImageView) bambooViewHolder.getView(R.id.diary_item_pic), fab_add));
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
