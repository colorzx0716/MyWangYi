package com.xiao.mywangyi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiao.mywangyi.bean.Popup;

import java.util.List;

/**
 * pop的适配器类
 * Created by 张肖肖 on 2017/9/15.
 */

public class PopupAdapter extends BaseAdapter{
    private final Context context;
    private final List<Popup> popupList;

    public PopupAdapter(Context context, List<Popup> popupList) {
        this.context = context;
        this.popupList = popupList;
    }


    @Override
    public int getCount() {
        return popupList.size();
    }

    @Override
    public Object getItem(int i) {
        return popupList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

     ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.pop_tv_item, null);
            viewHolder.img = view.findViewById(R.id.popup_iv);
            viewHolder.tv = view.findViewById(R.id.popup_tv);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.img.setImageResource(popupList.get(i).getId());

        viewHolder.tv.setText(popupList.get(i).getName());

        return view;
    }

    private class ViewHolder {
        public ImageView img;
        public TextView tv;
    }
}
