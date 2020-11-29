package com.example.listview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
 * 自定义的基于BaseAdapter的适配器
 * */
public class MyAdapter extends BaseAdapter {

    List<Item> list;//item的list对象
    Context context;//上下文对象

    //初始化
    public MyAdapter(List<Item> list, Context context) {
        this.context = context;
        this.list = list;
        //列表同步方法
        notifyDataSetChanged();
    }

    //得到当前列表的选项数量
    public int getCount() {
        return list.size();
    }

    //根据下标得到列表项
    public Item getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }
        /**自定义适配器的getView */
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            /**加载名字和图片*/
            convertView=View.inflate(context, R.layout.activity_data, null);
            viewHolder=new ViewHolder();
            viewHolder.textView=convertView.findViewById(R.id.str);
            viewHolder.imageView=convertView.findViewById(R.id.im);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        int green = Color.parseColor("#FF4081");
        int white = Color.parseColor("#ffffff");
        viewHolder.textView.setText(list.get(position).getName());
        viewHolder.imageView.setImageResource(R.drawable.robot);
        /** 被选择就改变颜色 */
        if(list.get(position).isBo()){
            viewHolder.textView.setBackgroundColor(green);
        }
        else {
            viewHolder.textView.setBackgroundColor(white);
        }
        return convertView;

    }

    //创建内部类，定义每一个列表项所包含的东西，这里是每个列表项都有一个imageView和textView。
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
