package com.wyz.my123;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wyz.my123.adapter.MyFragmentStareAdapter;
import com.wyz.my123.client.NetClient;
import com.wyz.my123.helper.MyPagerHelper;
import com.wyz.my123.ui.login.LoginActivity;
import com.wyz.my123.url.BaseUrl;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PracticeActivity extends AppCompatActivity implements View.OnClickListener{

    private ProgressButton progressButton1,progressButton2,progressButton3;
    private ProgressThread pt;
    private ProgressThread2 pt2;
    private ProgressThread3 pt3;
    private Boolean isPause = false;
    private int i = 1,m = 1, n = 1;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            progressButton1.setProgress(msg.arg1);
            progressButton2.setProgress(msg.arg1);
            progressButton3.setProgress(msg.arg1);
        }
    };

    private int z = 0,v = 0,k = 0;

    private SoundPool soundPool1, soundPool2;
    private int soundID;

    Intent intent;
    ViewPager2 vp2;
    char[] c = new char[]{'+','-'};
    ArrayList<Integer> answer = new ArrayList<>();
    Button buttonTemp;
    TextView tvCount;
    Integer count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        initViewPager2();
        initButton();

        initSound_true();
        initSound_false();

        //????????????????????????
        progressButton1 = findViewById(R.id.button_pb1);
        progressButton2 = findViewById(R.id.button_pb2);
        progressButton3 = findViewById(R.id.button_pb3);
        progressButton1.setTag(0);
        progressButton2.setTag(0);
        progressButton3.setTag(1);
        progressButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ??????????????????????????????
                if ((Integer) progressButton1.getTag() == 0) {
                    pt = new ProgressThread();
                    pt.start();
                    progressButton1.setTag(1);
                }
                if (!progressButton1.isFinish()) {
                    progressButton1.toggle();
                }

            }
        });
        progressButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ??????????????????????????????
                if ((Integer) progressButton2.getTag() == 0) {
                    pt2 = new ProgressThread2();
                    pt2.start();
                    progressButton1.setTag(2);
                }
                if (!progressButton2.isFinish()) {
                    progressButton2.toggle();
                }

            }
        });
        progressButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ??????????????????????????????
                if ((Integer) progressButton3.getTag() == 1) {
                    pt3 = new ProgressThread3();
                    pt3.start();
                    progressButton3.setTag(2);
                }
                if (!progressButton3.isFinish()) {
                    progressButton3.toggle();
                }

            }
        });
        progressButton1.setOnStateListener(new ProgressButton.OnStateListener() {
            @Override
            //?????????????????????????????????
            public void onFinish() {
                isPause = true;
                synchronized (this) {
                    pt.interrupt();
                }
                /*progressButton.setText("??? ???");*/
                // progressButton.initState();
                /*Toast.makeText(PracticeActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();*/
                playSound_false();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                intent = new Intent(PracticeActivity.this,FinishActivity.class);
                startActivity(intent);
            }
            @Override
            public void onStop() {
                Log.i("zz", "stop");
            }

            @Override
            public void onContinue() {
                Log.i("zz", "continue");
                isPause = false;
            }
        });
        progressButton2.setOnStateListener(new ProgressButton.OnStateListener() {
            @Override
            public void onFinish() {
                isPause = true;
                synchronized (this) {
                    pt.interrupt();
                }
            }
            @Override
            public void onStop() {
                Log.i("zz", "stop");
            }

            @Override
            public void onContinue() {
                Log.i("zz", "continue");
                isPause = false;
            }
        });
        progressButton3.setOnStateListener(new ProgressButton.OnStateListener() {
            @Override
            public void onFinish() {
                isPause = true;
                synchronized (this) {
                    pt.interrupt();
                }
            }
            @Override
            public void onStop() {
                Log.i("zz", "stop");
            }

            @Override
            public void onContinue() {
                Log.i("zz", "continue");
                isPause = false;
            }
        });
        //???????????????
        progressButton1.performClick();
        progressButton2.performClick();
        progressButton3.performClick();
    }


    public void initButton() {
        findViewById(R.id.button_one).setOnClickListener(this);
        findViewById(R.id.button_two).setOnClickListener(this);
        findViewById(R.id.button_three).setOnClickListener(this);
    }

    private void initViewPager2() {
        vp2 = findViewById(R.id.practice_vp2);
        Random random = new Random();
        String str;
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            str = "";
            int num = random.nextInt(3) + 1;
            str += num + " ";
            for (int j = 0; j < 2; j++) {
                char operator;
                int num2;
                if (j == 0 && num <= 3) {
                    num2 = random.nextInt(3) + 1;
                    operator = c[random.nextInt(2)];
                } else {
                    if (num < 1){
                        operator = '+';
                        num2 = random.nextInt(3 + num) + 1 - num;
                    } else if(num == 1) {
                        operator = '+';
                        num2 = random.nextInt(2) + 1;
                    } else if(num == 2) {
                        operator = c[random.nextInt(2)];
                        num2 = 1;
                    } else if(num == 3) {
                        operator = '-';
                        num2 = random.nextInt(2) + 1;
                    } else {
                        operator = '-';
                        num2 = random.nextInt(7 - num) + num - 3;
                    }
                }
                str += operator + " " + num2 + " ";
                switch (operator){
                    case '+':
                        num += num2;
                        break;
                    case '-':
                        num -= num2;
                        break;
                }
            }
            answer.add(num);
            str += "=";
            fragmentList.add(BlankFragment.newInstance(str));
        }
        MyFragmentStareAdapter adapter = new MyFragmentStareAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        vp2.setAdapter(adapter);
        vp2.setUserInputEnabled(false);
    }


    public void setData() {
    }
    private void changeState(int position) {
        tvCount = findViewById(R.id.textView_count);
        count = vp2.getCurrentItem();
        Integer a = answer.get(count);
        buttonTemp = findViewById(position);
        TextView state = findViewById(R.id.blank_state);
        if(buttonTemp.getText().equals(a.toString())) {
            state.setText("???");
            state.setTextSize(36);

            playSound_true();

            state.setTextColor(getColor(R.color.maroon3));
            state.setBackgroundResource(R.drawable.answer);
            new Handler(Looper.getMainLooper()).post(() ->{
                try {

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // ????????????????????????
                MyPagerHelper.setCurrentItem(vp2, vp2.getCurrentItem() + 1, 500);
                tvCount.setText((++count).toString());
            });

        } else {
            state.setText("x");
            state.setTextSize(36);

            playSound_false();

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        intent = new Intent(PracticeActivity.this,FinishActivity.class);
                        intent.putExtra("count",vp2.getCurrentItem());
                        System.out.println("!!!!!!!!!!!!!!!!!!!"+vp2.getCurrentItem());
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        i = 2;
        m = 2;
        n = 2;
    }

    private void playSound_true() {
        soundPool1.play(
                soundID,
                0.8f,
                0.8f,
                0,
                0,
                1
        );
    }

    private void playSound_false() {
        soundPool2.play(
                soundID,
                0.8f,
                0.8f,
                0,
                0,
                1
        );
    }

    private void initSound_true() {
        soundPool1 = new SoundPool.Builder().build();
        soundID = soundPool1.load(this,R.raw.sound_1,1);
    }

    private void initSound_false() {
        soundPool2 = new SoundPool.Builder().build();
        soundID = soundPool2.load(this,R.raw.sound_false,1);
    }


    @Override
    public void onClick(View view) {
        changeState(view.getId());
    }

    public void pause(View view) {
        //?????? ???????????????????????????????????????
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("???????????????");//??????????????????????????????
        builder.setIcon(R.drawable.ic_baseline_pause_24);//??????????????????????????????
        builder.setMessage("?????????????????????????????????");//??????????????????????????????
        builder.setCancelable(false);//???????????????
        //????????????
        builder.setPositiveButton("????????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PracticeActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        //????????????
        builder.setNegativeButton("????????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PracticeActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        builder.show();
    }

    //?????????????????????????????????
    public class ProgressThread extends Thread {
        public int z = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    if (!isPause) {
                        if ( i == 2){
                            z = 0;
                            i = 1;
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        z += 1.5;
                        Message msg = Message.obtain();
                        msg.arg1 = z;
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    public class ProgressThread2 extends Thread {
        public int k = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    if (!isPause) {
                        if (n == 2){
                            k = 0;
                            n = 1;
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        k += 1.5;
                        Message msg = Message.obtain();
                        msg.arg1 = k;
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    public class ProgressThread3 extends Thread {
        public int v = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    if (!isPause) {
                        if (m == 2){
                            v = 0;
                            m = 1;
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        v += 1.5;
                        Message msg = Message.obtain();
                        msg.arg1 = v;
                        handler.sendMessage(msg);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}