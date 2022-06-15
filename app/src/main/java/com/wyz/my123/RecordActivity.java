package com.wyz.my123;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wyz.my123.client.NetClient;
import com.wyz.my123.entity.RecordEntity;
import com.wyz.my123.ui.login.LoginActivity;
import com.wyz.my123.url.BaseUrl;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    TextView today_date;
    TextView today_record;
    TextView today_rank;
    TextView total_record;
    TextView total_rank;
    TextView record_name;
    MainApp myapp = (MainApp) LoginActivity.loginActivity.getApplication();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initView() {
        String todayRecord,todayRank,totalRecord,totalRank;
        todayRecord = "/getTodayRecord";
        todayRank = "/getTodayRank";
        totalRecord = "/getTotalRecord";
        totalRank = "/getTotalRank";

        today_date = findViewById(R.id.textView12);
        today_date.setText(getDate());

        today_record = findViewById(R.id.tv_record_today);
        today_rank = findViewById(R.id.tv_record_today_rank);
        total_record = findViewById(R.id.tv_record_total);
        total_rank = findViewById(R.id.tb_record_total_rank);
        record_name = findViewById(R.id.record_name);
        record_name.setText(myapp.getDisplayName());

        new Thread(() -> {
            today_record.setText(getData(todayRecord));
            today_rank.setText(getData(todayRank));
            total_record.setText(getData(totalRecord));
            total_rank.setText(getData(totalRank));
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
}