package com.houlai.wzryname;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomActionBar extends LinearLayout {
    private TextView headerTitle, headerMenuText;
    private LinearLayout llSearch;
    private LayoutInflater mInflater;
    private View headView;

    public CustomActionBar(Context context) {
        super(context);
        init(context);
    }

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        mInflater = LayoutInflater.from(context);
        headView = mInflater.inflate(R.layout.action_bar, null);
        addView(headView);
        initView();
    }

    private void initView() {
        headerTitle = headView.findViewById(R.id.header_title);
        headerMenuText = headView.findViewById(R.id.header_menu);
        llSearch = headView.findViewById(R.id.ll__search);
    }

    public void setStyle(String title) {
        if (title != null)
            headerTitle.setText(title);
    }

    /**
     * 标题加文字菜单
     *
     * @param title
     * @param menuText
     * @param listener
     */
    public void setStyle(String title, String menuText, OnClickListener listener) {
        setStyle(title);
        if (menuText != null)
            headerMenuText.setText(menuText);
        headerMenuText.setOnClickListener(listener);
    }


}