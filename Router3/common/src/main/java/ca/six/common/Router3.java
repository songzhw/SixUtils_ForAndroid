package ca.six.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;
import java.util.ServiceLoader;


public class Router3 {
    private static Router3 instance = new Router3();
    private final HashMap<String, Class<? extends Activity>> map;

    public static Router3 getInstance() {
        return instance;
    }

    private Router3() {
        map = new HashMap<>();
    }

    public void merge(String url, Class<? extends Activity> activityClass) {
        map.put(url, activityClass);
    }

    public void init() {
        ServiceLoader<IMerge> services = ServiceLoader.load(IMerge.class);
        for (IMerge service : services) {
            service.merge();
        }
    }

    public void go(Context ctx, String url) {
        // 应对多级跳转
        String[] splited = url.split("/");
        String jumpUrl = "";
        if (splited.length > 0) {
            jumpUrl = splited[0];
        }

        // 真正的跳转
        _go(ctx, jumpUrl, url);
    }

    private void _go(Context ctx, String jumpUrl, String realUrl) {
        Class<? extends Activity> clz = map.get(jumpUrl);
        if (clz == null) {
            Log.e("Router3", "====== no class matched! ======= ");
            return;
        }

        Intent it = new Intent(ctx, clz);
        boolean isCtxActivity = ctx instanceof Activity;
        if (!isCtxActivity) {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        Uri uri = Uri.parse(realUrl);
        it.setData(uri);

        ctx.startActivity(it);
    }

}
