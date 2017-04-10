package ca.six.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.ServiceLoader;

// 1. add arguments (暂不支持多级跳转) -- 要支持也不验证，就是在_go()里判断， 是最后一级才加it.putExtras(bundle)
// 2. add flag      (暂不支持多级跳转)
public class Router3 {
    private static Router3 instance = new Router3();
    private final HashMap<String, Class<? extends Activity>> map;
    private final HashMap<Class<? extends Activity>, String> map2;

    private int flag = -1;
    private Bundle bundle;

    public static Router3 getInstance() {
        return instance;
    }

    private Router3() {
        map = new HashMap<>();
        map2 = new HashMap<>();
    }

    public void merge(String url, Class<? extends Activity> activityClass) {
        map.put(url, activityClass);  // 给正常跳转用
        map2.put(activityClass, url); // 给多级跳转用
    }

    public void init() {
        ServiceLoader<IMerge> services = ServiceLoader.load(IMerge.class);
        for (IMerge service : services) {
            service.merge();
        }
    }

    public Router3 addFlag(int flag){
        this.flag = flag;
        return this;
    }

    public Router3 addBundle(Bundle bundle){
        this.bundle = bundle;
        return this;
    }

    // ================================================
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
        // 应对多级跳转: 最后一级才加bundle 与 flag
        boolean isEndPoint = jumpUrl.equalsIgnoreCase(realUrl);

        Class<? extends Activity> clz = map.get(jumpUrl);
        if (clz == null) {
            Log.e("Router3", "====== go(): no class matched! ======= ");
            return;
        }

        Intent it = new Intent(ctx, clz);
        boolean isCtxActivity = ctx instanceof Activity;
        if (!isCtxActivity) {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            if(isEndPoint) {
                if (flag != -1) {
                    it.addFlags(flag);
                }
            }
        }

        if(isEndPoint) {
            if (bundle != null) {
                it.putExtras(bundle);
            }
        }

        Uri uri = Uri.parse(realUrl);
        it.setData(uri);

        ctx.startActivity(it);
    }


    // ================================================
    // TODO: 2017-04-01 暂不支持 “一个Activity对应多个URL”的配置
    public void rego(Activity ctx){
        Class<? extends Activity> clz = ctx.getClass();
        String url = map2.get(clz);
        if(url == null){
            Log.e("Router3", "====== rego(): no url matched! ======= ");
            return;
        }

        String urlInActivity = ctx.getIntent().getDataString();
        String middleUrl = urlInActivity.replace(url+"/","");

        go(ctx, middleUrl);

    }
}
