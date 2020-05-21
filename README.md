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

![](/pic/logutils.jpg)



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

# 3. RxJava - EasyDispose

## 3.1 Context
The following code, in my opinion, is not elegant, and is hard to read. 

```kotlin
compositeDisposable.add(
    MyHttpEngine.fetchSomething()
        .subscribe {...}
)
```

First, you need to initialize one CompositeDisposable instance
And later, you must not forget to use "compositeDisposable.add(...)"
Of course, you also have to call `compositeDisposable.clear` in the onDestory() or onStop
Also, too much brackets looks ugly for me.

These are, of course, boilerplate. I am a guy who hat boilerplate. It's hard to write, to extend, and easy to make mistake.  That's why I made this tool.

## 3.2 How to use this tool
All you have to do is add a `disposedBy()` add the end of your rxjava call chain.

### Approach 01
Take the previous code for example, now you just need to write the following code:

```kotlin
    MyHttpEngine.fetchSomething()
        .subscribe {...}
        .disposedBy(lifecycle) // "lifecycle" is the appcompatActivity.getLifeyCycle()
```

Then this subscription will get disposed in your Actiivty's onDestory().

### Approach 02
Or you could indicate that which phrase you want to dispose this subscription.

```kotlin
    MyHttpEngine.fetchSomething()
        .subscribe {...}
        .disposedBy(lifecycle, Lifecycle.Event.ON_STOP) // "lifecycle" is the appcompatActivity.getLifeyCycle()
```

### Approach 03
Or you could also provide an CompositeDisposable. 
Remember, you still need to call `compositeDisposable.clear()` in the destroy of your Activity.

```kotlin
fun foo() {
    MyHttpEngine.fetchSomething()
        .subscribe {...}
        .disposedBy(compositeDisposable)
}

fun onDestory(){
    compositeDisposable.clear()
    super.onDestory()
}
```

# 4. Timeout
Did you ever want a handy timeout function, just like JavaScript's `setTimeout(lambda, timeout)`. Yeah, here it is. 

Here is how to use it:

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("szw start : ${System.currentTimeMillis()}")
        timeout(6000) {
            println("szw 6 second timeout: ${System.currentTimeMillis()}")
        }

        timeout(4000) {
            println("szw 4 second timeout: ${System.currentTimeMillis()}")
        }
    }
}
```

You can also pass a different Thread's looper to this timeout function. So you could use this timeout function outside the UI thread.

```kotlin
val handlerThread = HandlerThread("thread-2")
handlerThread.start()
timeout(handlerThread.getLooper(), 3000){
    println("ok, timeout")
}
```