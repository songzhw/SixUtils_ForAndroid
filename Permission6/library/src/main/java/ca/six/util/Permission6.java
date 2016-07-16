package ca.six.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by songzhw on 2016-07-15
 */
public class Permission6 extends Activity{
    private static IAfterDo doable;
    public static final int REQUEST_CODE = 3;

    public static void executeWithPermission(Activity from, String permission, IAfterDo listener) {
        doable = listener;
        if (ContextCompat.checkSelfPermission(from, permission) == PackageManager.PERMISSION_GRANTED) {
            doable.doAfterPermission();
        } else {
            Intent it = new Intent(from, Permission6.class);
            it.putExtra("goal_permission", permission);
            from.startActivity(it);
        }
    }

    public static void destory(){
        Permission6.doable = null;
    }

    public static void startAppSettings(Activity ctx, String packageName) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        ctx.startActivity(intent);
    }

    // =================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String permission = getIntent().getStringExtra("goal_permission");
        ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            doable.doAfterPermission();
        } else {
            // user choose "never show the permission dialog", it will goes here constantly
            doable.userDenyPermission();
        }
        this.finish();
    }


}
