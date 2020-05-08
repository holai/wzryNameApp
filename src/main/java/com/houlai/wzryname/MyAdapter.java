package com.houlai.wzryname;

import android.content.Context;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<String> jsonArray;
    public MyAdapter(Context context, int layout, List<String> jsonArray) {
        this.context = context;
        this.layout = layout;
        this.jsonArray = jsonArray;
    }
    @Override
    public int getCount() {
        return jsonArray.size();
    }

    @Override
    public String getItem(int i) {
        return jsonArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Hold hold;
        if(view == null){
            view = LayoutInflater.from(context).inflate(layout,null);
            hold = new Hold();
            hold.textView = view.findViewById(R.id.txt);
            hold.button = view.findViewById(R.id.fz);
            view.setTag(hold);
        }else {
            hold = (Hold) view.getTag();
        }
        final String s = getItem(i);
        hold.textView.setText(s);
        hold.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(s);
                Toast.makeText(context, "复制成功!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    class Hold{
        TextView textView;
        Button button;
    }
}
