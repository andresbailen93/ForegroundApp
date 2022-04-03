# ForegroundApp

This is an example app to learn to detect when an App change between Foreground and Background in a Non-single-activity App.

Now is common to found Apps with the Single-activity archiquetures, In this case is easyly detect when the App is in Foreground and Background. We could reach it overriding Activity lifecycle methods like ```OnStart()``` or ```onPause()```. 

In this case we solve the problem in a multi-activity archiquetures app.

In our case, we need to include the ```androidx.lifecycle``` library.

We include the dependency in gradle

```
implementation 'androidx.lifecycle:lifecycle-process:2.4.1'
```

We need to use a Application class like this:

````
class ForegroundApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
````

And also define it in our  ```Manifest.xml```

````
    <application
        android:name=".ForegroundApp"
        ....
````

At this point we are ready to implement it

At first, ````ForegroundApp```` should implementent ````LifecycleEventObserver```` 

This will be our implementation:

````
class ForegroundApp: Application(), LifecycleEventObserver 
````

After that we should override the ````onStateChanged(source: LifecycleOwner, event: Lifecycle.Event)```` method.


````
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
````

And the last step is register our lifecycle observer like with this method, that we should include in out Application ````onCreate()```` method: 

````
ProcessLifecycleOwner.get().lifecycle.addObserver(this)
````
