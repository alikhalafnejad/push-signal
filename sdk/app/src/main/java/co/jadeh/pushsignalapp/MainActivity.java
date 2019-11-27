package co.jadeh.pushsignalapp;

import androidx.appcompat.app.AppCompatActivity;
import co.jadeh.pushsignal.OneSignal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private CheckBox postNotifGroupCheckBox,postNotifAsyncGroupCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postNotifGroupCheckBox = findViewById(R.id.postNotificationGroupCheckBox);
        postNotifAsyncGroupCheckBox = findViewById(R.id.postNotificationAsyncGroupCheckBox);

        setupGroupingNotificationCheckBoxes();
    }

    public void onPostNotifClicked(View v) {

        String userId = OneSignal.getUserId();

        JSONObject notifPayload = null;
        try {

            notifPayload = new JSONObject("{'contents': " + "{'en':'Test Message'}, 'include_player_ids': ['" + userId + "']}");

            if (postNotifGroupCheckBox.isChecked())
                notifPayload.put("android_group", "group_1");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        OneSignal.postNotification(notifPayload, new OneSignal.PostNotificationResponseHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, response.toString());
            }

            @Override
            public void onFailure(JSONObject response) {
                OneSignal.onesignalLog(OneSignal.LOG_LEVEL.ERROR, response.toString());
            }
        });
    }

    public void onPostNotifAsyncClicked(View v) {
        new AsyncTaskRunner().execute(
                postNotifAsyncGroupCheckBox.isChecked()
        );
    }

    private class AsyncTaskRunner extends AsyncTask<Object, Void, Void> {

        @Override
        protected Void doInBackground(Object... params) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String userId = OneSignal.getUserId();

            JSONObject notifPayload = null;
            try {

                notifPayload = new JSONObject("{'contents': " + "{'en':'Test Message'}, 'include_player_ids': ['" + userId + "']}");

                Boolean addGroup = (Boolean) params[0];
                if (addGroup)
                    notifPayload.put("android_group", "group_1");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            OneSignal.postNotification(notifPayload, new OneSignal.PostNotificationResponseHandler() {
                @Override
                public void onSuccess(JSONObject response) {
                    OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, response.toString());
                }

                @Override
                public void onFailure(JSONObject response) {
                    OneSignal.onesignalLog(OneSignal.LOG_LEVEL.ERROR, response.toString());
                }
            });

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(Void... entry) {

        }
    }

    private void setupGroupingNotificationCheckBoxes() {
        this.postNotifGroupCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "Main thread Notifications will be grouped: " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        this.postNotifAsyncGroupCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "Async notifications will be grouped: " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
