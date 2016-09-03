# android-utils
some utils written by Java. I can import these into my projects. (songzhw/android-toolkit is the project with some tools by groovy or other language, which can not be imported into the Android project)


# 1. Log Util

This is an Android log library.

## How to import it

```groovy
  dependencies {
    compile 'ca.six.util:LogUtil:1.0.1'
  }
```

## How to use it

```java
L.d("your message")
```

## output 

```
v/szw:  MainActivity  || onCreate() || (MainActivity.java: Line 08)
        your message
```

The `(MainActivity.java: Line 08)` is clickable. And if you click it, Android Studio will jump to the line 8 of MainActivity. 

# 2. Permission6

A easy way to implement the permission check/request code in Android 6.0


## How to import it

```groovy
  dependencies {
    compile 'ca.six.util:Permisssion6:1.0.1'
  }
```


## How to use it

Your activity should implement the `IAfterDo` callback. And you can call "Permission6.executeWithPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, this);" to check/request permission.

```java
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
```



