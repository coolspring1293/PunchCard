package com.team10.punchcard.unity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import java.util.List;
import java.util.Map;

/**
 * Created by hrz on 16-5-22.
 */
public class SpecialAdapter extends SimpleAdapter {
    private int[] colors = new int[]{0xFFBFBFBF,0xFFFFFFFF};
    public SpecialAdapter(Context context, List<? extends Map<String, ?>> data,
                          int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        // TODO Auto-generated constructor stub
    }
    /* (non-Javadoc)
     * @see android.widget.SimpleAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = super.getView(position, convertView, parent);
        int colorPos = position%colors.length;
        view.setBackgroundColor(colors[colorPos]);
        return view;
    }
  /*  public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        int[] arrayOfInt = colors;
        int colorLength = colors.length;
        int selected = arrayOfInt[position % colorLength];
        View localView = super.getView(position, convertView, parent);
        localView.setBackgroundResource(selected);
        return localView;
    }*/
}
