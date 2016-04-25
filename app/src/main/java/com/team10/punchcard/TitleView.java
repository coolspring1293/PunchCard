package com.team10.punchcard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * Created by Eden on 4/25/16.
 */
public class TitleView extends FrameLayout {

    private ImageButton leftButton;

    private TextView titleText;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);//从LayoutInflater的inflate方法加载title.xml来构建TitleView
        titleText = (TextView) findViewById(R.id.title_text);
        leftButton = (ImageButton) findViewById(R.id.button_left);
        /*leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Activity) getContext()).finish();//调用finish()方法来关闭当前的Activity，也就相当于实现返回功能了
            }
        });*/
    }

    public void setTitleText(String text) {
        titleText.setText(text);
    }

    public void setLeftButtonListener(OnClickListener l) {
        leftButton.setOnClickListener(l);
    }

}

