package com.onexzgj.onexzgjmd;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.onexzgj.onexzgjmd.annotation.Async;
import com.onexzgj.onexzgjmd.annotation.Logger;
import com.onexzgj.onexzgjmd.annotation.Main;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnexUtils.getInstance(this);

        tvTitle = findViewById(R.id.tv_title);
        Button btnClick = findViewById(R.id.btn_click);
        Button btnClick2 = findViewById(R.id.btn_click2);
        Button btnClick3 = findViewById(R.id.btn_click3);

        btnClick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
                showToast();
                Toast.makeText(MainActivity.this, "btnClick2", Toast.LENGTH_SHORT).show();
            }
        });

        btnClick3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum(1,2);
                Toast.makeText(MainActivity.this, "btnClick3", Toast.LENGTH_SHORT).show();
            }
        });

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("点击了");
            }
        });


        Glide.with(this).load("").into(new ImageView(this));
    }

    @Async
    public void readFile() {
        Log.e("TAG", "readFile:" + Thread.currentThread().toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tvTitle.setText("12344");
        showToast();
    }


    @Main
    private void showToast() {
        Log.e("TAG", "showToast:" + Thread.currentThread().toString());
        Toast.makeText(this, "主线程中执行" + Thread.currentThread().toString(), Toast.LENGTH_SHORT).show();
    }

    @Logger(Log.ERROR)
    public void sum(int i, int j) {
        int result = i + j;
        Log.e("asdsad","sadasdsasdas");
    }
}
