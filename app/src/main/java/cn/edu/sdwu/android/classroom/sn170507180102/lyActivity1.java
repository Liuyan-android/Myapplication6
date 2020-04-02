package cn.edu.sdwu.android.classroom.sn170507180102;

import android.app.WallpaperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lyActivity1 extends AppCompatActivity implements View.OnFocusChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //加载界面
        setContentView(R.layout.layout_ly);
        //获取普通界面组件必须在setContentView之后调用findviewbyid方法
        ImageView imageView = (ImageView) findViewById(R.id.ly_im);
        Button button = (Button) findViewById(R.id.button1);

//使用事件源的setxxxListener方法注册时间监听器
        button.setOnClickListener(new MyClickListener());
        //使用内部匿名类注册监听器

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //设置壁纸
                WallpaperManager wallpaperManager = (WallpaperManager) getSystemService(WALLPAPER_SERVICE);
                try {
                    wallpaperManager.setResource(R.raw.timg);
                } catch (Exception e) {
                    Log.e(lyActivity1.class.toString(), e.toString());
                }


                return true;
            }
        });
        EditText email = (EditText) findViewById(R.id.ly_1_email);
        email.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        //参数b代表是否获取焦点
        //判断邮箱地址的合法性
        EditText editText = (EditText) view;
        if (!b) {
            String content = editText.getText().toString();
            //判断邮箱的正则表达式
            String regEx1 = "^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$";
            Pattern pattern = Pattern.compile(regEx1);
            Matcher matcher = pattern.matcher(content);
            TextView textView=(TextView)findViewById(R.id.ly_1_tv);
            if (matcher.matches()) {
                textView.setText("");

            } else {
                textView.setText("email invalidate");
            }


        }
    }

    //实现事件监听类该监听类是一个特殊的java类，必须实现一个xxxListenerlei
    class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(lyActivity1.class.toString(), "button click");
        }
    }
}



