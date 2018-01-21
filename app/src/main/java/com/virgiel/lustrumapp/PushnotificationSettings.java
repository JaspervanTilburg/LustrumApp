package com.virgiel.lustrumapp;

import android.content.SharedPreferences;

/**
 * Created by Rolf on 26-11-2017.
 */

public class PushnotificationSettings {

    private static boolean algemeenNotifications;
    private static boolean galaNotifications;
    private static boolean wispoNotifications;
    private static boolean lustrumweekNotifications;
    private static boolean piekweekNotifications;
    private static boolean tinderNotifications;
    private static SharedPreferences preferences;

    private static final String algemeenNotificationKey = "algemeenNotifications";
    private static final String galaNotificationKey = "galaNotifications";
    private static final String wispoNotificationKey = "wispoNotifications";
    private static final String lustrumweekNotificationKey = "lustrumweekNotifications";
    private static final String piekweekNotificationKey = "piekweekNotifications";
    private static final String tinderNotificationKey = "tinderNotifications";


    public static void initNotificationSettings(SharedPreferences pref) {
        preferences = pref;
        algemeenNotifications = preferences.getBoolean(algemeenNotificationKey, true);
        galaNotifications = preferences.getBoolean(galaNotificationKey, true);
        wispoNotifications = preferences.getBoolean(wispoNotificationKey, true);
        lustrumweekNotifications = preferences.getBoolean(lustrumweekNotificationKey, true);
        piekweekNotifications = preferences.getBoolean(piekweekNotificationKey, true);
        tinderNotifications = preferences.getBoolean(tinderNotificationKey, true);
    }

    public static boolean getAlgemeenNotifications() {
        return galaNotifications;
    }

    public static boolean getGalaNotifications() {
        return galaNotifications;
    }

    public static boolean getWispoNotifications() {
        return wispoNotifications;
    }

    public static boolean getLustrumweekNotifications() {
        return lustrumweekNotifications;
    }

    public static boolean getPiekweekNotifications() {
        return piekweekNotifications;
    }

    public static boolean getTinderNotifications() {
        return tinderNotifications;
    }

    public static void setAlgemeenNotifications(boolean algemeenNotifications) {
        PushnotificationSettings.algemeenNotifications = algemeenNotifications;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(algemeenNotificationKey, algemeenNotifications);
        editor.commit();
    }

    public static void setGalaNotifications(boolean galaNotifications) {
        PushnotificationSettings.galaNotifications = galaNotifications;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(galaNotificationKey, galaNotifications);
        editor.commit();
    }
    public static void setWispoNotifications(boolean wispoNotifications) {
        PushnotificationSettings.wispoNotifications = wispoNotifications;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(wispoNotificationKey, wispoNotifications);
        editor.commit();
    }

    public static void setLustrumweekNotifications(boolean lustrumweekNotifications) {
        PushnotificationSettings.lustrumweekNotifications = lustrumweekNotifications;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(lustrumweekNotificationKey, lustrumweekNotifications);
        editor.commit();
    }

    public static void setPiekweekNotifications(boolean piekweekNotifications) {
        PushnotificationSettings.piekweekNotifications = piekweekNotifications;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(piekweekNotificationKey, piekweekNotifications);
        editor.commit();
    }

    public static void setTinderNotifications(boolean tinderNotifications) {
        PushnotificationSettings.tinderNotifications = tinderNotifications;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(tinderNotificationKey, tinderNotifications);
        editor.commit();
    }

}
