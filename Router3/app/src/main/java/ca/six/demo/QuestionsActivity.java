package ca.six.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ca.six.common.Router3;


public class QuestionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        TextView tv = (TextView) findViewById(R.id.tv_simple);
        tv.setText("What is your name?");
    }

    public void onClickSimpleButton(View v) {
        Router3.getInstance().rego(this);
    }

    public void onClickSimpleButton2(View v) {

    }

}