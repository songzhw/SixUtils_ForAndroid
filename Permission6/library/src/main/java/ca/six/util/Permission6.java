package ca.six.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by songzhw on 2016-07-15
 */
public class Permission6 {
    private IAfterDo doable;

    public Permission6(IAfterDo listener) {
        this.doable = listener;
    }

    public void executeWithPermission(Activity ctx, String permission) {
        if (ContextCompat.checkSelfPermission(ctx, permission) != PackageManager.PERMISSION_GRANTED) {
            doable.doAfterPermission();
        } else {
            ctx.startActivity(new Intent(ctx, PermissionUtilsActivity.class));
        }
    }



}
