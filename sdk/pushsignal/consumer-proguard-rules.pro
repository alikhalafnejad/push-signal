-dontwarn co.jadeh.pushsignal.**

# These 2 methods are called with reflection.
-keep class com.google.android.gms.common.api.GoogleApiClient {
    void connect();
    void disconnect();
}


-keep class co.jadeh.pushsignal.ActivityLifecycleListenerCompat** {*;}


# Observer backcall methods are called with reflection
-keep class co.jadeh.pushsignal.OSSubscriptionState {
    void changed(co.jadeh.pushsignal.OSPermissionState);
}

-keep class co.jadeh.pushsignal.OSPermissionChangedInternalObserver {
    void changed(co.jadeh.pushsignal.OSPermissionState);
}

-keep class co.jadeh.pushsignal.OSSubscriptionChangedInternalObserver {
    void changed(co.jadeh.pushsignal.OSSubscriptionState);
}

-keep class ** implements co.jadeh.pushsignal.OSPermissionObserver {
    void onOSPermissionChanged(co.jadeh.pushsignal.OSPermissionStateChanges);
}

-keep class ** implements co.jadeh.pushsignal.OSSubscriptionObserver {
    void onOSSubscriptionChanged(co.jadeh.pushsignal.OSSubscriptionStateChanges);
}

-keep class co.jadeh.pushsignal.shortcutbadger.impl.AdwHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.ApexHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.AsusHomeLauncher { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.DefaultBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.EverythingMeHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.HuaweiHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.LGHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.NewHtcHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.NovaHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.OPPOHomeBader { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.SamsungHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.SonyHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.VivoHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.XiaomiHomeBadger { <init>(...); }
-keep class co.jadeh.pushsignal.shortcutbadger.impl.ZukHomeBadger { <init>(...); }


-dontwarn com.amazon.**

# Proguard ends up removing this class even if it is used in AndroidManifest.xml so force keeping it.
-keep public class co.jadeh.pushsignal.ADMMessageHandler {*;}

-keep class co.jadeh.pushsignal.JobIntentService$* {*;}

-keep class co.jadeh.pushsignal.OneSignalUnityProxy {*;}