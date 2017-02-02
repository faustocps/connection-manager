![alt tag](https://github.com/faustocps/connection-manager/blob/master/ic_network_connection.png)

# Network Connection
Demonstrates how to get connection status, connection type, among other features. This is not an exhaustive sample, but it shows some of the important features as it is necessary to work with connection, be it Wifi, 4G or others:

* Collapsing Toolbar
* FloatingActionButton
* CardView

# Permissions

- android.permission.INTERNET
- android.permission.ACCESS_WIFI_STATE
- android.permission.ACCESS_NETWORK_STATE

# Code: ConnectionType

```ruby
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
```

# License

Copyright 2014 The Android Open Source Project, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
