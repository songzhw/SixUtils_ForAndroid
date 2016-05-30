#Log Util

This is an Android log library.

## How to import it

```groovy
  dependencies {
    classpath 'ca.six.util:LogUtil:1.0.1'
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