package ca.six.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ca.six.common.Router3;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        TextView tv = (TextView)findViewById(R.id.tv_simple);
        tv.setText("Main Module");
    }

    public void onClickSimpleButton(View v) {
        Router3.getInstance().go(this, "mod1");
    }

    public void onClickSimpleButton2(View v) {
        Router3.getInstance().go(this, "login/third");
    }
}

/*
 Router.build("user")
     .requestCode(int) // 调用startActivityForResult
     .extras(bundle)  // 携带跳转参数
     .addFlags(flag)  // 添加标记，比如intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
     .anim(enter, exit)  // 添加跳转动画
     .callback(calback)  // 跳转结果回调
     .go(context);
 */