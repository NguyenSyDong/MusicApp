package com.example.mp3app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mp3app.Activity.ItemMenu;
import com.example.mp3app.R;

import java.util.List;

public class MenuAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ItemMenu> list;

    public MenuAdapter(Context context, int layout, List<ItemMenu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = view.findViewById(R.id.tvItem);
            viewHolder.img = view.findViewById(R.id.imgicon);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ItemMenu itemMenu = list.get(i);
        viewHolder.tv.setText(itemMenu.tenItem);
        viewHolder.img.setImageResource(itemMenu.icon);

        return view;
    }
}
