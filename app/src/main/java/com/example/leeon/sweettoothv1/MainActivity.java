package com.example.leeon.sweettoothv1;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bridgefy.sdk.client.Bridgefy;
import com.bridgefy.sdk.client.BridgefyClient;
import com.bridgefy.sdk.client.BridgefyUtils;
import com.bridgefy.sdk.client.Device;
import com.bridgefy.sdk.client.Message;
import com.bridgefy.sdk.client.MessageListener;
import com.bridgefy.sdk.client.RegistrationListener;
import com.bridgefy.sdk.client.Session;
import com.bridgefy.sdk.client.StateListener;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public HashMap<String, Object> data = new HashMap<>();
    public static int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    public void putData(HashMap<String, Object> data,String s){
        data.put("1", s);
    }
    public boolean isConnectedToOtherPhone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClickStartBridgefy(View view){
        Bridgefy.initialize(getApplicationContext(), "81637191-6bd6-46ab-a47a-cb5401638146", registrationListener);
    }

    private RegistrationListener registrationListener = new RegistrationListener() {
        @Override
        public void onRegistrationSuccessful(BridgefyClient bridgefyClient) {
            super.onRegistrationSuccessful(bridgefyClient);
            Bridgefy.start(messageListener, stateListener);
            Toast toast = Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT);
            toast.show();
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Check Permissions Now
                ActivityCompat.requestPermissions(MainActivity.this,
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
            isConnectedToOtherPhone = true;
            Toast toast2 = Toast.makeText(getApplicationContext(), "Person near by: app. 32 feet radius", Toast.LENGTH_LONG);
            toast2.show();
//            Message message = Bridgefy.createMessage(device.getUserId(),data);
//            device.sendMessage(new HashMap<String, Object>() {{put("1","Hello");}});
        }
        public void onDeviceLost(Device device){
            Toast toast = Toast.makeText(getApplicationContext(), "Device Lost", Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    public void newPage(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
