package ca.six.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.lang.annotation.Target;

/**
 * Created by songzhw on 2016-07-15
 */
public class Permission6 extends Activity{
    private static IAfterDo doable;

    public static void executeWithPermission(Activity from, String permission, IAfterDo listener) {
        doable = listener;
        if (ContextCompat.checkSelfPermission(from, permission) != PackageManager.PERMISSION_GRANTED) {
            doable.doAfterPermission();
        } else {
            Intent it = new Intent(from, Permission6.class);
            it.putExtra("goal_permission", permission);
            from.startActivity(it);
        }
    }

    // =================================================================
    public void destory(){
        Permission6.doable = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String permission = getIntent().getStringExtra("goal_permission");
        ActivityCompat.requestPermissions(this, new String[]{permission}, 13);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 13 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            doable.doAfterPermission();
        } else {
            doable.userDenyPermission();
        }
    }


}
