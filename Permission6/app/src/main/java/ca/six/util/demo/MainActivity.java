package ca.six.util.demo;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ca.six.util.IAfterDo;
import ca.six.util.Permission6;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IAfterDo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Permission6.executeWithPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, this);
    }

    @Override
    public void doAfterPermission() {
        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userDenyPermission() {
        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();

        // normally, a dialog is better for user experience
        Permission6.startAppSettings(this, "ca.six.util.demo");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Permission6.destory();
    }
}
