package co.ghola.pushyme;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

import me.pushy.sdk.Pushy;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "Pushyme";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pushy.listen(this);
        new registerForPushNotificationsAsync().execute();

    }
    private class registerForPushNotificationsAsync extends AsyncTask<Void, Void, String>
    {
        protected String doInBackground(Void... params)
        {
            String registrationId = null;
            try
            {
                // Acquire a unique registration ID for this device
                registrationId = Pushy.register(getApplicationContext());
                Log.d(TAG, registrationId);

                // Send the registration ID to your backend server and store it for later
                sendRegistrationIdToBackendServer(registrationId);
            }
            catch( Exception exc )
            {
                // Return exc to onPostExecute
                Log.d(TAG, exc.toString());
            }

            // We're good
            return registrationId;
        }


        protected void onPostExecute(String s){
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(s);
        }

        // Example implementation
        void sendRegistrationIdToBackendServer(String registrationId) throws Exception
        {

            // The URL to the function in your backend API that stores registration IDs
            URL sendRegIdRequest = new URL("https://{YOUR_API_HOSTNAME}/register/device?registration_id=" + registrationId);

            // Send the registration ID by executing the GET request
            sendRegIdRequest.openConnection();



        }
    }
}
