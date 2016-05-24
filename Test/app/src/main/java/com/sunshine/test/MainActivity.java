package com.sunshine.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sunshine.test.adapter.RecyclerAdapter;
import com.sunshine.test.bean.DataBean;
import com.sunshine.test.inter.MyItemListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  MyItemListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private int[] ints = new int[]{R.mipmap.vaccination_normal,R.mipmap.vivo_anthelmintic_normal,R.mipmap.walk_dog_normal
    ,R.mipmap.weibo_normal,R.mipmap.weixin_normal,R.mipmap.vivo_anthelmintic_normal,R.mipmap.walk_dog_normal
            ,R.mipmap.weibo_normal,R.mipmap.weixin_normal,R.mipmap.vivo_anthelmintic_normal,R.mipmap.walk_dog_normal
            ,R.mipmap.weibo_normal,R.mipmap.weixin_normal};
    private String[] strings = new String[]{"图片01","图片02","图片03","图片04","图片05","图片02","图片03","图片04","图片05","图片02","图片03","图片04","图片05"};
    private List<DataBean> dataBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("可折叠的View");
        collapsingToolbarLayout.setExpandedTitleColor(Color.GREEN);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.RED);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    refreshLayout.setRefreshing(false);
                                    Toast.makeText(MainActivity.this,"已经是最新了",Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        loadListData(0);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.list_normal://标准
                loadListData(0);
                return true;
            case R.id.list_vertical_reverse://垂直反向
                loadListData(1);
                return true;
            case R.id.list_horizontal://水平
                loadListData(2);
                return true;
            case R.id.list_horizontal_reverse://水平反向
                loadListData(3);
                return true;
            case R.id.grid_normal://水平反向
                loadGridData(0);
                return true;
            case R.id.grid_vertical_reverse://水平反向
                loadGridData(1);
                return true;
            case R.id.grid_horizontal://水平反向
                loadGridData(2);
                return true;
            case R.id.grid_horizontal_reverse://水平反向
                loadGridData(3);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadGridData(int style) {
        List<DataBean> dataBeanList = new ArrayList<>();
        DataBean bean;
        for (int i=0;i<ints.length;i++){
            bean = new DataBean();
            bean.ivIcon = ints[i];
            bean.tvName = strings[i];
            dataBeanList.add(bean);
        }
        GridLayoutManager gridLayoutManager = null;
        switch (style){
            case 0:
                gridLayoutManager =  new GridLayoutManager(this, 3,LinearLayoutManager.VERTICAL,false);
                break;
            case 1:
                gridLayoutManager =  new GridLayoutManager(this, 3,LinearLayoutManager.VERTICAL,true);
                break;
            case 2:
                gridLayoutManager =  new GridLayoutManager(this, 1,LinearLayoutManager.HORIZONTAL,false);
                break;
            case 3:
                gridLayoutManager =  new GridLayoutManager(this, 1,LinearLayoutManager.HORIZONTAL,true);
                break;
        }

        //设置布局管理器
        recyclerView.setLayoutManager(gridLayoutManager);
        //设置适配器
        recyclerView.setAdapter(new RecyclerAdapter(this,0,dataBeanList));
    }

    private void loadListData(int style) {
        dataBeanList = new ArrayList<>();
        DataBean bean;
        for (int i=0;i<ints.length;i++){
            bean = new DataBean();
            bean.ivIcon = ints[i];
            bean.tvName = strings[i];
            dataBeanList.add(bean);
        }
        LinearLayoutManager linearLayoutManager = null;
        switch (style){
            case 0:
                linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
                break;
            case 1:
                linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true);
                break;
            case 2:
                linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
                break;
            case 3:
                linearLayoutManager =  new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true);
                break;
        }

        //设置布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, 1, dataBeanList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemListener(this);

    }

    @Override
    public void onItemClick(View view, int position) {
        DataBean bean = dataBeanList.get(position);
        Toast.makeText(this,bean.tvName,Toast.LENGTH_SHORT).show();
    }
}
