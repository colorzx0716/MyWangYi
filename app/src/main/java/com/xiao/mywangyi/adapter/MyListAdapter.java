package com.xiao.mywangyi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiao.mywangyi.R;
import com.xiao.mywangyi.XiangQingActivity;
import com.xiao.mywangyi.bean.Bean;

import java.util.List;

/**
 * Created by 张肖肖 on 2017/9/14.
 */

public class MyListAdapter extends BaseAdapter {
    private final Context context;
    private final List<Bean.NewslistBean> newslist;

    private final int atype = 0;
    private final int btype = 1;

    public MyListAdapter(Context context, List<Bean.NewslistBean> newslist) {
        this.context = context;
        this.newslist = newslist;
    }

    @Override
    public int getCount() {
        return newslist.size();
    }

    @Override
    public Object getItem(int i) {
        return newslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2 == 0){
            return atype;
        }else{
            return btype;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        int type = getItemViewType(i);
        ViewHolder1 viewHolder1;
        ViewHolder2 viewHolder2;

        if(view == null){
            switch (type){
                case atype:
                    viewHolder1 = new ViewHolder1();
                    view = View.inflate(context, R.layout.list_item, null);
                    view.setTag(viewHolder1);
                    viewHolder1.list_img = view.findViewById(R.id.list_img);
                    viewHolder1.list_title = view.findViewById(R.id.list_title);
                    viewHolder1.list_address = view.findViewById(R.id.list_address);
                    viewHolder1.list_date = view.findViewById(R.id.list_date);

                    break;
                case btype:
                    viewHolder2 =new ViewHolder2();
                    view = View.inflate(context, R.layout.grid_item, null);
                    view.setTag(viewHolder2);
                    viewHolder2.g_img = view.findViewById(R.id.g_img);
                    viewHolder2.g_title = view.findViewById(R.id.g_title);
                    viewHolder2.g_addres = view.findViewById(R.id.g_addres);
                    viewHolder2.g_date = view.findViewById(R.id.g_date);

                    break;
            }

        }else {
            switch (type) {
                case atype:
                    viewHolder1 = (ViewHolder1) view.getTag();
                    viewHolder1.list_title.setText(newslist.get(i).getTitle());
                    viewHolder1.list_address.setText(newslist.get(i).getDescription());
                    viewHolder1.list_date.setText(newslist.get(i).getCtime());

                    ImageLoader.getInstance().displayImage(newslist.get(i).getPicUrl(),viewHolder1.list_img);

                    break;
                case btype:
                    viewHolder2 = (ViewHolder2) view.getTag();
                    viewHolder2.g_title.setText(newslist.get(i).getTitle());
                    viewHolder2.g_addres.setText(newslist.get(i).getDescription());
                    viewHolder2.g_date.setText(newslist.get(i).getCtime());
                    ImageLoader.getInstance().displayImage(newslist.get(i).getPicUrl(),viewHolder2.g_img);

                    break;
            }
        }

        //点击条目跳转
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, XiangQingActivity.class);
                intent.putExtra("url",newslist.get(i).getUrl());//传参
                context.startActivity(intent);
            }
        });

        return view;
    }

    private class ViewHolder1 {
        public ImageView list_img;
        public TextView list_title;
        public TextView list_address;
        public TextView list_date;
    }

    private class ViewHolder2 {
        public ImageView g_img;
        public TextView g_title;
        public TextView g_date;
        public TextView g_addres;
    }
}
