package com.example.over.cia4003_week5_activity07_bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.*;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity
{
    //Global variables
    String deviceName;
    String deviceHardwareAddress;

    private void displayMessage(String msg)
    {
        Toast.makeText(this,  msg, Toast.LENGTH_LONG).show();
    }

    //From Activity 7.
    private void isBluetoothOn()
    {
        //create a bluetooth adapter object
        BluetoothAdapter BA = BluetoothAdapter.getDefaultAdapter();
        if (BA != null) //bluetooth supported by device
        {
            //Continue with bluetooth setup
            if (BA.isEnabled()) {
                // Enabled. Work with Bluetooth.
                String mydeviceAddress = BA.getAddress(); //get the address of device
                String mydeviceName = BA.getName();       //get the name of device
                int state = BA.getState();//if state is on, value is 12 else value is 10
                displayMessage("Device Name: " + mydeviceName + " \nDevice Address: "
                        + mydeviceAddress + " \nDevice state: " + state);
            } else {
                displayMessage("Bluetooth is not enabled");
            }

        }//end if (BA!=null)
        else //Bluetooth not supported
        {
            displayMessage("Bluetooth not supported by device");
        }
    }

    //Activity 8, Task 1.
    public void checkBluetooth(View v)
    {
        Set<BluetoothDevice> pairedDevices;

        TextView tvBluetooth = findViewById(R.id.tvBlueTooth);

        BluetoothAdapter BA = BluetoothAdapter.getDefaultAdapter();
        pairedDevices = BA.getBondedDevices();
        String st = "";

        if (pairedDevices.size() > 0)
        {
            for (BluetoothDevice device:pairedDevices)
            {
                st += device.getName() + "\n";
            }
            tvBluetooth.setText(st);
        }
        else
        {
            tvBluetooth.setText("No paired device was detected.");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //From Activity 7.
        isBluetoothOn();
    }
}