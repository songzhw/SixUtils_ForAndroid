package ca.six.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ca.six.common.Router3;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        StringBuilder builder = new StringBuilder();
        builder.append("Login Page\n");
        TextView textView = (TextView) findViewById(R.id.tv_simple);
        textView.setText(builder.toString());
    }

    public void onClickSimpleButton(View v) {
        System.out.println("szw login : url = "+getIntent().getDataString());
        Router3.getInstance().rego(this);
    }

    public void onClickSimpleButton2(View v){
    }
}

