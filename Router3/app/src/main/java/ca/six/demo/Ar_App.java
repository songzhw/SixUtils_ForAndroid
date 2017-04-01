package ca.six.demo;

import android.app.Application;

import java.util.Map;

import ca.six.common.Router3;


public class Ar_App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Router3.getInstance().init();

    }
}
