package com.dft.onyx50demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.dft.onyxcamera.config.Onyx;

/**
 * Example activity for running Onyx that has been previously configured.
 */

public class OnyxActivity extends Activity {
    private Onyx configuredOnyx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Setting the activity being used to run Onyx here so that it can be finished from
        // the SuccessCallback in OnyxSetupActivity
        MainApplication.setActivityForRunningOnyx(this);

        // Get the configured Onyx that was returned from the OnyxCallback
        configuredOnyx = MainApplication.getConfiguredOnyx();

        // Creates Onyx in this activity
        try {
            configuredOnyx.create(this);
        }catch (Exception e){
            Log.e("Error","Unable to start Onnx Activity");

        }
        // Make Onyx start the capture process
        // Important: configuredOnyx.capture() must occur after configuredOnyx.create() has been called
        if (!configuredOnyx.getOnyxConfig().isManualCapture()) {
            // Start the capture with auto capture process
            configuredOnyx.capture();
        }

    }
}
