package ca.six.router2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    }

    public void onClickSimpleButton2(View v){
    }
}

