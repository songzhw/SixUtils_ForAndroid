package ca.six.router2.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        TextView tv = (TextView)findViewById(R.id.tv_simple);
        tv.setText("Main Module");
    }

    public void onClickSimpleButton(View v) {

    }

    public void onClickSimpleButton2(View v) {

    }
}
