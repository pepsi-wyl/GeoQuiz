package site.ylan.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主控件
        setContentView(R.layout.activity_main);

        // 修改TextView控件的内容
        TextView tv = findViewById(R.id.main_tv);
        tv.setText("你好!");
        tv.setTextColor(Color.GREEN);
        tv.setBackgroundColor(Color.RED);
        tv.setBackgroundResource(R.color.white);

        // 获取按钮
        Button button = findViewById(R.id.main_button);
        button.setOnClickListener(v -> {
            // 意图对象
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, YlanActivity.class);
            // 跳转到一个新的页面
            startActivity(intent);
        });

    }
}