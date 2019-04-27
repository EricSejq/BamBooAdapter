package com.example.eric.bambooadapter.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.eric.bambooadapter.R;
import com.example.eric.bambooadapter.bambooadapter.BambooAdapter;
import com.example.eric.bambooadapter.bambooadapter.BambooViewHolder;
import com.example.eric.bambooadapter.data.ClassBean;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eric.bambooadapter.*;
/**
 * Description:
 * Data：2019/4/24-21:49
 *
 * @author: eric
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView mainRv;
    private List<ClassBean> classBeans = new ArrayList<>();
    private final int mItemCount = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < mItemCount; i++) {
            classBeans.add(new ClassBean("view" + i, null));
        }
    }

    private void initView() {
        mainRv = findViewById(R.id.main_rv);
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        mainRv.setAdapter(
                new BambooAdapter<ClassBean>(this)
                        .addHead(R.layout.base_head_layout)
                        .addHead(R.layout.base_head_layout)
                        .addNormal(R.layout.base_item_btn)
                        .addFoot(R.layout.base_foot_layout)
                        .addNormalData(classBeans)
                        .onNormalBindListener(new BambooAdapter.BindListener<ClassBean>() {
                            @Override
                            public void onBindNormal(BambooViewHolder bambooViewHolder, ClassBean classBean, int position) {
                                bambooViewHolder
                                        .setBtnText(R.id.normal_btn, classBean.name)
                                        .addClickListener(R.id.normal_btn, view -> {
                                            Log.e(TAG, "onBindNormal: " + position);
                                            removeItem(bambooViewHolder.getAdapterPosition());
                                        });
                            }

                            @Override
                            public void onBindHead(BambooViewHolder bambooViewHolder, int position) {
                                bambooViewHolder.addItemClickListener(v -> {
                                    Log.e(TAG, "onBindHead: " + "click head " + position);
                                });
                            }

                            @Override
                            public void onBindFoot(BambooViewHolder bambooViewHolder, int position) {
                                bambooViewHolder.addItemClickListener(v -> {
                                    Log.e(TAG, "onBindFoot: " + "click foot " + position);
                                });
                            }
                        })
        );
    }

    private void removeItem(int position) {
        ((BambooAdapter) mainRv.getAdapter()).removeItem(position);
    }
}
