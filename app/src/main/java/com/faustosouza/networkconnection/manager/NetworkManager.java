package com.faustosouza.networkconnection.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.faustosouza.networkconnection.integrator.NetworkIntegrator;

/**
 * Created by faustocps on 01/02/2017.
 */

public class NetworkManager {

    private final NetworkIntegrator mNetworkIntegrator;

    public NetworkManager() {
        super();
        this.mNetworkIntegrator = new NetworkIntegrator();
    }

    /**
     * Verify connection status
     */
    public boolean isConnectionAvailable(final Context context) {
        return mNetworkIntegrator.isConnectionAvailable(context);
    }

    /**
     * Check type connection
     *
     * @param context
     * @return boolean
     */

    public String getNetworkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        String result = "";
        if (isConnectedWifi(info)) {
            result = "WIFI";
        } else if (isConnectedMobile(info)) {
            switch (info.getSubtype()) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    result = "2G";
                    break;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    result = "3G";
                    break;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    result = "4G";
                    break;
                default:
                    result = "Offline";
            }
        }
        else {
            result ="Offline";
        }
        return result;
    }


    /**
     * Check if the connection is fast
     *
     * @param info NetworkInfo
     * @return boolean
     */
    public boolean isConnectionFast(NetworkInfo info) {
        boolean result = false;
        if (isConnectedWifi(info)) {
            result = true;
        } else if (isConnectedMobile(info)) {
            switch (info.getSubtype()) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    // 2G
                    break;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                case TelephonyManager.NETWORK_TYPE_LTE:
                    result = true;
                    break;
                default:
                    result = false;
            }
        }
        return result;
    }


    /**
     * Check if there is any connectivity to a Wifi network
     *
     * @param info NetworkInfo
     * @return boolean
     */
    public boolean isConnectedWifi(NetworkInfo info) {
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network
     *
     * @param info NetworkInfo
     * @return boolean
     */
    public boolean isConnectedMobile(NetworkInfo info) {
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }


}
