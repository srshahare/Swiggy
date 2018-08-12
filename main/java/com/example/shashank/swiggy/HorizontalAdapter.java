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

public class HorizontalAdapter extends ArrayAdapter<String> {

    int[] image;
    Context mContext;

    public HorizontalAdapter(@NonNull Context context,int[] images) {
        super(context, R.layout.horizontal_list_single);
        this.image = images;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflate.inflate(R.layout.horizontal_list_single, parent, false);
            mViewHolder.mImage = (ImageView) convertView.findViewById(R.id.image_view);

            convertView.setTag(mViewHolder);
        }else
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mImage.setImageResource(image[position]);
        return convertView;
    }
       static class ViewHolder {
        ImageView mImage;
       }
}
