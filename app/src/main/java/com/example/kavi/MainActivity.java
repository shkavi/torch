package com.example.kavi;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton toggleButton;
    boolean hasCameraFlash=false;
    boolean flashOn=false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton=findViewById(R.id.toggleButton);
        hasCameraFlash=getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        toggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(hasCameraFlash) {
                    if (flashOn) {
                        flashOn = false;
                        toggleButton.setImageResource(R.drawable.power_on);
                        try {
                            flashLightOff();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    } else {
                        flashOn = true;
                        toggleButton.setImageResource(R.drawable.power_off);
                        try {
                            flashLightOn();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    Toast.makeText(MainActivity.this, "No flash availabe in your device",Toast.LENGTH_LONG).show();

                }
            }

        });
    }
private void flashLightOn() throws CameraAccessException {
    CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
     String cameraId=cameraManager.getCameraIdList()[0];
    Log.d("kavi","nwork");
        cameraManager.setTorchMode(cameraId,true);

    Toast.makeText(MainActivity.this,"Flashlight is on",Toast.LENGTH_SHORT).show();
}


    private void flashLightOff() throws CameraAccessException {

        CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId=cameraManager.getCameraIdList()[0];
        Log.d("kavi","work");
            cameraManager.setTorchMode(cameraId,false);

        Toast.makeText(MainActivity.this,"Flashlight is off",Toast.LENGTH_SHORT).show();
    }

}