package ca.six.demo.util

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

// Dispose your disposable || Approach 01
fun Disposable.disposedBy(manager: CompositeDisposable) {
    manager.add(this)
}

// Dispose your disposable || Approach 02
fun Disposable.disposedBy(lifecycle: Lifecycle, lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY) {
    lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onAny(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == lifecycleEvent) {
                this@disposedBy.dispose()
            }
        }
    })
}