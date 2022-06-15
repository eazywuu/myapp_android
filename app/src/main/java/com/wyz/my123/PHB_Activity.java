package com.wyz.my123;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wyz.my123.client.NetClient;
import com.wyz.my123.entity.RecordEntity;
import com.wyz.my123.entity.UserEntity;
import com.wyz.my123.ui.login.LoginActivity;
import com.wyz.my123.url.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PHB_Activity extends AppCompatActivity {

    private static final String TAG = "Unreal";
    /*设置测试数据*/
    /*private List<RecordEntity> data = new ArrayList<>();*/
    private List<UserEntity> data = new ArrayList<>();

    /*定义排行榜两个表头切换的样式*/
    private  RelativeLayout.LayoutParams layoutParams_1;
    private  RelativeLayout.LayoutParams layoutParams_2;

    /*两个表头*/
    TextView tv3;
    TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phb);
        /*getTop();*/
        /*设置排行榜列表的适配器*/
        ListView listview = findViewById(R.id.lv);
        listview.setAdapter(new PHB_Adapter(data,this));

        for (int i = 0; i < 20; i++){
            UserEntity user = new UserEntity();
            user.setUserName("测试人员_" + i);
            data.add(user);
        }

        /*获取排行榜的两个表头id，用来设置点击事件*/
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);

        layoutParams_1 = (RelativeLayout.LayoutParams) tv3.getLayoutParams();
        layoutParams_2 = (RelativeLayout.LayoutParams) tv4.getLayoutParams();

        //切换到总答题数榜单
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv3_onClick();
            }
        });

        //切换到今日答题数榜单
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv4_onClick();
            }
        });

    }

    /*private void getTop() {
        new Thread(() -> {
            String res;
            try {
                Gson gson = new Gson();
                res = NetClient.getNetClient().get(BaseUrl.URL+"/record/getTop");
                res = new JSONObject(res).getString("top");
                data = gson.fromJson(res, new TypeToken<List<RecordEntity>>() {}.getType());
                for (RecordEntity recordEntity : data) {
                    System.out.println(recordEntity);
                    Log.e(TAG, "getTop: ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }*/

    /*总答题数的点击事件*/
    private void tv3_onClick(){
        tv3.setTextSize(20);
        tv3.setBackground(getDrawable(R.drawable.shape2));
        layoutParams_1.addRule(RelativeLayout.START_OF, R.id.tv_4);
        tv3.setLayoutParams(layoutParams_1);
        tv4.setTextSize(15);
        tv4.setBackground(getDrawable(R.drawable.shape3));
        tv4.setLayoutParams(layoutParams_2);
    }

    /*今日答题数的点击事件*/
    private void tv4_onClick(){
        tv3.setTextSize(15);
        tv3.setBackground(getDrawable(R.drawable.shape3));
        layoutParams_1.addRule(RelativeLayout.START_OF, R.id.tv_4);
        tv3.setLayoutParams(layoutParams_1);
        tv4.setTextSize(20);
        tv4.setBackground(getDrawable(R.drawable.shape2));
        tv4.setLayoutParams(layoutParams_2);
    }
}