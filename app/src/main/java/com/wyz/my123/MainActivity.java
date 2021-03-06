package com.wyz.my123;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wyz.my123.ui.login.LoginActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Dialog dialog;
    private View inflate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.imageButton_start).setOnClickListener(this);

        findViewById(R.id.imageButton_phb).setOnClickListener(this);

        findViewById(R.id.imageButton_history).setOnClickListener(this);

        findViewById(R.id.imageButton_prompt).setOnClickListener(this);

        findViewById(R.id.setting_button).setOnClickListener(this);

        findViewById(R.id.setting_button).setOnClickListener(view -> show_dialog());
    }

    @Override
    protected void onStart() {
        super.onStart();

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

    private void show_dialog(){
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //????????????????????????
        inflate = LayoutInflater.from(this).inflate(R.layout.activity_set, null);
        //????????????
        inflate.findViewById(R.id.btn_logout).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
        inflate.findViewById(R.id.btn_return).setOnClickListener(view -> dialog.dismiss());
        //??????????????????Dialog
        dialog.setContentView(inflate);
        //????????????Activity???????????????
        Window dialogWindow = dialog.getWindow();
        //??????Dialog?????????????????????
        dialogWindow.setGravity(Gravity.CENTER);
        //?????????????????????
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 0;//??????Dialog?????????????????????
        //????????????????????????????????????
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        //????????????????????????
        dialogWindow.setAttributes(lp);
        dialog.show();//???????????????
    }
}