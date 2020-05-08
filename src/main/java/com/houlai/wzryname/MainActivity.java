package com.houlai.wzryname;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    RadioGroup group;
    EditText edit;
    Button btn;
    ListView listView;
    String tag = "blank";
    CustomActionBar customActionBar;
    ArrayList<String> name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        Window window = getWindow();
        //After LOLLIPOP not translucent status bar
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Then call setStatusBarColor.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        customActionBar = findViewById(R.id.bar);
        //返回键 + 标题 + 菜单
        customActionBar.setStyle("空白名生成", "关于", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.alert,null);
                new AlertDialog.Builder(MainActivity.this)
                        .setView(view)
                        .setCancelable(true)
                        .setPositiveButton("整个好评！", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                try {
                                    String marketPkg = "com.coolapk.market";
                                    final PackageManager packageManager = MainActivity.this.getPackageManager();
                                    List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
                                    List<String> packageNames = new ArrayList<String>();
                                    if (packageInfos != null) {
                                        for (int i = 0; i < packageInfos.size(); i++) {
                                            String packName = packageInfos.get(i).packageName;
                                            packageNames.add(packName);
                                        }
                                    }
                                    // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
                                    if( packageNames.contains(marketPkg)){
                                        Uri uri = Uri.parse("market://details?id=com.houlai.wzryname");
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        if (!TextUtils.isEmpty(marketPkg))
                                            intent.setPackage(marketPkg);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        MainActivity.this.startActivity(intent);
                                    }else {
                                        Toast.makeText(MainActivity.this,"很可惜 您的手机上没有安装酷安", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
            }
        });

        group = findViewById(R.id.group);
        edit = findViewById(R.id.edit);
        btn = findViewById(R.id.btn);
        listView = findViewById(R.id.list);
        group.check(R.id.blank);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.center:
                        tag = "center";
                        edit.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.blank:
                        tag = "blank";
                        edit.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.reuse:
                        tag = "reuse";
                        edit.setVisibility(View.VISIBLE);
                        break;
                    case R.id.sun:
                        tag = "sun";
                        edit.setVisibility(View.VISIBLE);
                        break;
                    case R.id.flower:
                        tag = "flower";
                        edit.setVisibility(View.VISIBLE);
                        break;
                    case R.id.ly:
                        tag = "ly";
                        edit.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rhombus:
                        tag = "rhombus";
                        edit.setVisibility(View.VISIBLE);
                        break;
                    case R.id.round:
                        tag = "round";
                        edit.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 request(tag);
            }
        });
    }

    private void request(String tag){
        name = BuildUtil.build(this,tag,String.valueOf(edit.getText()));
        MyAdapter myAdapter = new MyAdapter(MainActivity.this,R.layout.list_item,name);
        listView.setAdapter(myAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.zan:
                try {
                    String marketPkg = "com.coolapk.market";
                    final PackageManager packageManager = this.getPackageManager();
                        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
                        List<String> packageNames = new ArrayList<String>();
                        if (packageInfos != null) {
                            for (int i = 0; i < packageInfos.size(); i++) {
                                String packName = packageInfos.get(i).packageName;
                                packageNames.add(packName);
                            }
                        }
                        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
                        if( packageNames.contains(marketPkg)){
                            Uri uri = Uri.parse("market://details?id=com.houlai.wzryname");
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            if (!TextUtils.isEmpty(marketPkg))
                                intent.setPackage(marketPkg);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            this.startActivity(intent);
                        }else {
                            Toast.makeText(this,"很可惜 您的手机上没有安装酷安", Toast.LENGTH_SHORT).show();
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.about:
                View view = LayoutInflater.from(this).inflate(R.layout.alert,null);
                new AlertDialog.Builder(this)
                        .setView(view)
                        .setCancelable(true)
                        .show();
                break;
        }
        return true;
    }
}
