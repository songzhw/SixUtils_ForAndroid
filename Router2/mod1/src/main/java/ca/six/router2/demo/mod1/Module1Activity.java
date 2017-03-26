package ca.six.router2.demo.mod1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ca.six.router2.annotation.Path;

@Path("mod1")
public class Module1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        TextView tv = (TextView)findViewById(R.id.tv_simple);
        tv.setText("Module 01");
    }

    public void onClickSimpleButton(View v) {

    }

    public void onClickSimpleButton2(View v) {

    }

}
