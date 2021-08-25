package com.example.indianicassigment.utils;

import androidx.annotation.StringRes;

public class AppManager {
    private static boolean isChatActVisible =false;
    /**
     * Is is chat act visible boolean.
     *
     * @return the boolean
     */
    public static boolean isIsChatActVisible() {
        return isChatActVisible;
    }

    /**
     * Sets is chat act visible.
     *
     * @param isChatActVisible the is chat act visible
     */
    public static void setIsChatActVisible(boolean isChatActVisible) {
        AppManager.isChatActVisible = isChatActVisible;
    }

    public static void getString(@StringRes int r){
        return;
    }
}
