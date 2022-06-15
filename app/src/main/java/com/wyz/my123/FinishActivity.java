package com.wyz.my123;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wyz.my123.client.NetClient;
import com.wyz.my123.ui.login.LoginActivity;
import com.wyz.my123.url.BaseUrl;

import org.json.JSONObject;

public class FinishActivity extends AppCompatActivity implements View.OnClickListener {

    private Dialog dialog;
    private View inflate;
    private TextView tv_best;
    private TextView finish_gradle;
    MainApp myapp = (MainApp) LoginActivity.loginActivity.getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_4);

        tv_best = findViewById(R.id.tv_best);
        new Thread(() ->tv_best.setText(getData("/getTotalRecord"))).start();
        finish_gradle = findViewById(R.id.finish_gradle);
        finish_gradle.setText("2");

        findViewById(R.id.imageButton_start).setOnClickListener(this);

        findViewById(R.id.imageButton_phb).setOnClickListener(this);

        findViewById(R.id.imageButton_history).setOnClickListener(this);

        findViewById(R.id.imageButton_prompt).setOnClickListener(this);

        findViewById(R.id.setting_button).setOnClickListener(this);

        findViewById(R.id.setting_button).setOnClickListener(view -> show_dialog());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButton_start:
                Intent intent = new Intent(this,PracticeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_bottom_in,R.anim.slide_top_out);
                break;
            case R.id.imageButton_phb:
                Intent intent_phb = new Intent(this,PHB_Activity.class);
                startActivity(intent_phb);
                overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
                break;
            case R.id.imageButton_history:
                Intent intent_his = new Intent(this,RecordActivity.class);
                startActivity(intent_his);
                overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);
                break;
            case R.id.setting_button:
                Intent intent_set = new Intent(this,SetActivity.class);
                startActivity(intent_set);
                overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
                show_dialog();
            case R.id.imageButton_prompt:
                Intent intent_prompt = new Intent(this,Layout_Activity.class);
                startActivity(intent_prompt);
                overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);
        }
    }
    public String getData(String param) {
        String data = "0";
        try {
            data = new JSONObject(NetClient.getNetClient().get(BaseUrl.URL+"/user" + param + "/"+ myapp.getUid().toString())).getString("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    private void show_dialog(){
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.activity_set, null);
        //获取控件
        inflate.findViewById(R.id.btn_logout).setOnClickListener(view -> {
            startActivity(new Intent(FinishActivity.this, LoginActivity.class));
        });
        inflate.findViewById(R.id.btn_return).setOnClickListener(view -> dialog.dismiss());
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离
        //宽度填充当前布局文件宽度
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
}