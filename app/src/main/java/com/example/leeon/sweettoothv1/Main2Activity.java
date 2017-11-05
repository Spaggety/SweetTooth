package com.example.leeon.sweettoothv1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgefy.sdk.client.Bridgefy;
import com.bridgefy.sdk.client.BridgefyClient;
import com.bridgefy.sdk.client.Device;
import com.bridgefy.sdk.client.Message;
import com.bridgefy.sdk.client.MessageListener;
import com.bridgefy.sdk.client.RegistrationListener;
import com.bridgefy.sdk.client.Session;
import com.bridgefy.sdk.client.StateListener;

import java.util.HashMap;

import static com.example.leeon.sweettoothv1.R.id.textView;

public class Main2Activity extends AppCompatActivity {

    public static int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    public boolean isConnectedToOtherPhone2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startBridgefy();
    }
    public void startBridgefy(){
        Bridgefy.initialize(getApplicationContext(), "81637191-6bd6-46ab-a47a-cb5401638146", registrationListener);
    }



    private RegistrationListener registrationListener = new RegistrationListener() {
        @Override
        public void onRegistrationSuccessful(BridgefyClient bridgefyClient) {
            super.onRegistrationSuccessful(bridgefyClient);
            Bridgefy.start(messageListener, stateListener);
            Toast toast = Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT);
            toast.show();
            if (ActivityCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Check Permissions Now
                ActivityCompat.requestPermissions(Main2Activity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }


        @Override
        public void onRegistrationFailed(int errorCode, String message) {
            super.onRegistrationFailed(errorCode, message);
            Toast toast = Toast.makeText(getApplicationContext(), message + "\n " + errorCode, Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    private MessageListener messageListener = new MessageListener() {
        @Override
        public void onMessageReceived(Message message) {

        }
    };

    private StateListener stateListener = new StateListener() {
        @Override
        public void onStarted(){
            Toast toast = Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT);
            toast.show();
        }
        public void onStartError(String s, int i){
            Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
            toast.show();
        }
        @Override
        public void onDeviceConnected(Device device, Session session) {
            //putData(data, "Hello");
            Toast toast = Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT);
            toast.show();
            isConnectedToOtherPhone2 = true;
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText("Person near by:\napproximately 32 feet radius");
//            Message message = Bridgefy.createMessage(device.getUserId(),data);
//            device.sendMessage(new HashMap<String, Object>() {{put("1","Hello");}});
            startBridgefy();
        }
        public void onDeviceLost(Device device){
            Toast toast = Toast.makeText(getApplicationContext(), "Device Lost", Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    public void oldPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
