package co.jadeh.pushsignalapp;

import android.app.Application;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import co.jadeh.pushsignal.OSNotification;
import co.jadeh.pushsignal.OSNotificationOpenResult;
import co.jadeh.pushsignal.OneSignal;

public class PushSignalApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        OneSignal.init(
                this,
                Constants.API_KEY,
                Constants.GOOGLE_NUMBER, // This is ignored, dashboard value will be used.
                Constants.APP_ID,
                //getOneSignalAppId(this),
                new ExampleNotificationOpenedHandler(),
                new ExampleNotificationReceivedHandler()
        );
//        OneSignal.sendTag("test1", "test1");
    }

    private class ExampleNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        /**
         * Callback to implement in your app to handle when a notification is received while your app running
         *  in the foreground or background.
         *
         *  Use a NotificationExtenderService instead to receive an event even when your app is closed (not 'forced stopped')
         *     or to override notification properties.
         *
         * @param notification Contains information about the notification received.
         */
        @Override
        public void notificationReceived(OSNotification notification) {
            Log.w("OneSignalExample", "notificationReceived!!!!!!");
            DebuggingHelper.printObject(notification);
            DebuggingHelper.printObject(notification.payload);

            Toast.makeText(PushSignalApp.this, "notif received", Toast.LENGTH_SHORT).show();

        }
    }

    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        /**
         * Callback to implement in your app to handle when a notification is opened from the Android status bar or in app alert
         *
         * @param openedResult Contains information about the notification opened and the action taken on it.
         */
        @Override
        public void notificationOpened(OSNotificationOpenResult openedResult) {
            Log.w("OneSignalExample", "notificationOpened!!!!!!");
            DebuggingHelper.printObject(openedResult.action);
            DebuggingHelper.printObject(openedResult.notification);


            Toast.makeText(PushSignalApp.this, "notif opened", Toast.LENGTH_SHORT).show();

        }
    }
}
