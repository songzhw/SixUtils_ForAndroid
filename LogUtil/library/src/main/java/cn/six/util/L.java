package cn.six.util;

import android.util.Log;

/**
 * Created by songzhw on 4/13/16.
 */
public class L {
    public static String tag = "szw";

    public static void d(String msg){
        StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        if(traces == null || traces.length < 8){
            Log.d(tag,"(normal) "+msg);
            return;
        }

        StringBuilder sb = new StringBuilder();
        StackTraceElement element = traces[3];

        String classWholeName = element.getClassName();
        String className = classWholeName.substring(classWholeName.lastIndexOf(".") + 1);


//        for(StackTraceElement element : traces) {
            sb.append(classWholeName);
            sb.append(" || ");
            sb.append(element.getMethodName());
            sb.append("() || ( ");
            sb.append(className);
            sb.append(".java:");
            sb.append(element.getLineNumber());
            sb.append(")\n");
//        }
        sb.append(msg);

        Log.d(tag, sb.toString());
    }
}
