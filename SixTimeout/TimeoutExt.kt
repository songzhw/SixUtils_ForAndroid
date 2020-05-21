package ca.six.timeout.demo

import android.os.Handler
import android.os.Looper
import android.os.Message

fun timeout(delayInMillSec: Long, job: () -> Unit) {
    timeout(Looper.getMainLooper(), delayInMillSec, job)
}

fun timeout(looper: Looper = Looper.getMainLooper(), delayInMillSec: Long, job: () -> Unit) {
    val timeoutId = 12345
    val handler = object : Handler(looper) {
        override fun handleMessage(msg: Message) {
            if (msg.what == timeoutId) {
                job()
            }
        }
    }

    handler.sendEmptyMessageDelayed(timeoutId, delayInMillSec)
}

/*
[demo]


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


 */