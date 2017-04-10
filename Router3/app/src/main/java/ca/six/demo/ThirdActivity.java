package ca.six.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        String str = getIntent().getStringExtra("key2");
        textView.setText("Third Page ("+str+")");

    }

    public void onClickSimpleButton(View v){
    }

    public void onClickSimpleButton2(View v){
    }
}


