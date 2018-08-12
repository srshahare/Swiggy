package com.example.shashank.swiggy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyAdapter extends ArrayAdapter<String> {

    String [] names;
    String [] description;
    int [] foodImage;
    Context mContext;

    public MyAdapter(@NonNull Context context,String[] foodName, String[] desc, int[] imgId) {
        super(context, R.layout.single_listview);
        this.names = foodName;
        this.description = desc;
        this.foodImage = imgId;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflate.inflate(R.layout.single_listview, parent, false);
            mViewHolder.mFood = (ImageView) convertView.findViewById(R.id.biryani_image);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.biryani_text);
            mViewHolder.mDesc = (TextView) convertView.findViewById(R.id.biryani_desc);

            convertView.setTag(mViewHolder);
        }else
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

            mViewHolder.mFood.setImageResource(foodImage[position]);
            mViewHolder.mName.setText(names[position]);
            mViewHolder.mDesc.setText(description[position]);
        return convertView;
    }
        static class ViewHolder{
            ImageView mFood;
            TextView mName;
            TextView mDesc;
        }

}













