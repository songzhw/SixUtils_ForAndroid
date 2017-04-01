package ca.six.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.ServiceLoader;


public class Router3 {
    private static Router3 instance = new Router3();
    private final HashMap<String, Class<? extends Activity>> map;

    public static Router3 getInstance(){
        return instance;
    }

    private Router3(){
        map = new HashMap<>();
    }

    public void merge(String url, Class<? extends Activity> activityClass){
        map.put(url, activityClass);
    }

    public void init(){
        ServiceLoader<IMerge> services = ServiceLoader.load(IMerge.class);
        for(IMerge service: services){
            service.merge();
        }
    }

    public void go(Context ctx, String url){
        Class<? extends Activity> clz = map.get(url);

        Intent it = new Intent(ctx, clz);
        boolean isCtxActivity = ctx instanceof Activity;
        if(!isCtxActivity){
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        ctx.startActivity(it);
    }
}
