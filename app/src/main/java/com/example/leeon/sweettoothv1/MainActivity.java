package com.example.leeon.sweettoothv1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
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

    public static void putData(HashMap<String, Object> data,String s){
        data.put("1", s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bridgefy.initialize(getApplicationContext(), null, registrationListener);

    }

    private RegistrationListener registrationListener = new RegistrationListener() {
        @Override
        public void onRegistrationSuccessful(BridgefyClient bridgefyClient) {
            super.onRegistrationSuccessful(bridgefyClient);
            Bridgefy.start(messageListener, stateListener);
            Toast toast = Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT);
        }

        @Override
        public void onRegistrationFailed(int errorCode, String message) {
            super.onRegistrationFailed(errorCode, message);
            Toast toast = Toast.makeText(getApplicationContext(), message + ": " + errorCode, Toast.LENGTH_SHORT);
        }
    };

    private MessageListener messageListener = new MessageListener() {
        @Override
        public void onMessageReceived(Message message) {
            super.onMessageReceived(message);
        }
    };

    private StateListener stateListener = new StateListener() {
        @Override
        public void onDeviceConnected(Device device, Session session) {
            super.onDeviceConnected(device, session);
            putData(data, "Hello");
            device.sendMessage(data);
        }
        public void onDeviceLost(Device device){
            super.onDeviceLost(device);
            Toast toast = Toast.makeText(getApplicationContext(), "Device Lost", Toast.LENGTH_SHORT);
        }
    };

    public void newPage(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}

