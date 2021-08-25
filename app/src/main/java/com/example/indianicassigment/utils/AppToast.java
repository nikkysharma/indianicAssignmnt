package com.example.indianicassigment.utils;

import android.app.Activity;
import android.widget.Toast;

public class AppToast {
    private AppToast() {
        // This class is not publicly instantiable
    }

    public static void show(Activity mActivity, String message, boolean isLongLength) {

        if (isLongLength) {
            Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
        }

    }
}
