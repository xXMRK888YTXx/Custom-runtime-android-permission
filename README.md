
# Example of creating custom runtime android permission 

## Creating permission

To create a custom permission, you need to declare it in the manifest
``` 
<permission android:name="com.xxmrk888ytxx.SEND_EXO_MESSAGE"
        android:protectionLevel="dangerous"
        android:label="My Exo permission"
        android:icon="@drawable/permission_icon" />
```

He has protectionlevel="dangerous" specified, which means that in order to get this permission, you need to request it from the user in runtime


Learn more about the permission tag and about protection level you can read [here](https://developer.android.com/guide/topics/manifest/permission-element)

That's all our resolution. Now we can set it to, for example, BroadcastReceiver.

```
<receiver android:name=".ExoReceiver"
            android:exported="true"
            android:permission="com.xxmrk888ytxx.SEND_EXO_MESSAGE" />
```

Now, in order to send an intent to this BroadcastReceiver, you must first request our permission.

## Request Permission
The process of obtaining our permission is no different from any other request for system permissions.

```
<uses-permission android:name="com.xxmrk888ytxx.SEND_EXO_MESSAGE"/>
```

Next, during the execution of the application, you must send a request for our permission. You can do this in many ways:
the old-fashioned way through onActivityResult, through the [Activity Result Api](https://developer.android.com/training/basics/intents/result), or if you use compose through the [accompanist library](https://google.github.io/accompanist/permissions/) (they in alpha)

It will look like this

```
val permissionState = rememberPermissionState("com.xxmrk888ytxx.SEND_EXO_MESSAGE")

if(permissionState.status != PermissionStatus.Granted) {
   permissionState.launchPermissionRequest()
} else {
  //TODO()
}
```

That's all. Next, the usual permission request window will appear.

Thanks for reading)
