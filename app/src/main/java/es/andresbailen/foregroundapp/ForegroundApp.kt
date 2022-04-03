package es.andresbailen.foregroundapp

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
const val TAG = "ForegroundApp"

class ForegroundApp: Application(), LifecycleEventObserver {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                Log.d(TAG, "ON START App is in foreground")
                Toast.makeText(this, "App is in foreground", Toast.LENGTH_SHORT).show()
            }
            Lifecycle.Event.ON_PAUSE -> {
                Log.d(TAG, "ON PAUSE")
                Toast.makeText(this, "App is on background", Toast.LENGTH_SHORT).show()
            }
            Lifecycle.Event.ON_STOP -> {
                Log.d(TAG, "ON STOP App is on background")
                Toast.makeText(this, "App is on background", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }
}