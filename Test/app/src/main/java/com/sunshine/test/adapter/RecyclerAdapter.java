package com.sunshine.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunshine.test.R;
import com.sunshine.test.bean.DataBean;
import com.sunshine.test.inter.MyItemListener;

import java.util.List;

/**
 * RecyclerView的适配器
 * Created by Sunshine on 2016/5/23.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private Context context;
    private List<DataBean> dataBeanList;
    private int style = 0;
    private MyItemListener myItemListener;
    public RecyclerAdapter(Context context,int style,List<DataBean> dataBeanList){
        this.context = context;
        this.dataBeanList = dataBeanList;
        this.style = style;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //每一个条目创建的回调
        View view = null;
        if (style==0){
            view = View.inflate(context, R.layout.item_grid, null);
        }else {
            view = View.inflate(context, R.layout.item_list, null);
        }

        return new ViewHolder(view,myItemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //数据绑定条目的回调
        DataBean dataBean = dataBeanList.get(position);
        holder.setData(dataBean);
    }

    public void setOnItemListener(MyItemListener myItemListener){
        this.myItemListener = myItemListener;
    }

    @Override
    public int getItemCount() {
        //数据的个数
        if (dataBeanList!=null){
            return dataBeanList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivIcon;
        public TextView tvName;
        private MyItemListener myItemListener;
        public ViewHolder(View itemView,MyItemListener myItemListener) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.myItemListener = myItemListener;
            itemView.setOnClickListener(this);
        }

        public void setData(DataBean dataBean) {
            ivIcon.setImageResource(dataBean.ivIcon);
            tvName.setText(dataBean.tvName);
        }

        @Override
        public void onClick(View v) {
            if (myItemListener!=null){
                myItemListener.onItemClick(v,getPosition());
            }
        }
    }
}
