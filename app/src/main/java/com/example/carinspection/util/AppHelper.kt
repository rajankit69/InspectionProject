package com.example.carinspection.util

import android.os.Build
import com.example.carinspection.model.ListNameValue
import com.example.carinspection.model.UploadMediaData
import com.google.gson.Gson
import java.net.InetAddress

import java.util.Collections

import java.net.NetworkInterface


class AppHelper {
    companion object {
        fun osName(): String {
           return "Android"
        }
        fun osVersionName(): Int {
            return Build.VERSION.SDK_INT;
        }
       @JvmStatic
       fun convertToString(obj : Any?):String
       {
           val gson = Gson()
           return gson.toJson(obj)
       }

        fun convertStringToUploadObject(json: String) : UploadMediaData
        {
            val gson = Gson()
             return gson.fromJson(json, UploadMediaData::class.java)
        }
        fun convertStringToListNameDataObject(json: String) : ListNameValue
        {
            val gson = Gson()
            return gson.fromJson(json, ListNameValue::class.java)
        }

        /**
         * Returns MAC address of the given interface name.
         * @param interfaceName eth0, wlan0 or NULL=use first interface
         * @return  mac address or empty string
         */
        fun getMACAddress(interfaceName: String?): String? {
            try {
                val interfaces: List<NetworkInterface> =
                    Collections.list(NetworkInterface.getNetworkInterfaces())
                for (intf in interfaces) {
                    if (interfaceName != null) {
                        if (!intf.name.equals(interfaceName, ignoreCase = true)) continue
                    }
                    val mac = intf.hardwareAddress ?: return ""
                    val buf = StringBuilder()
                    for (aMac in mac) buf.append(String.format("%02X:", aMac))
                    if (buf.length > 0) buf.deleteCharAt(buf.length - 1)
                    return buf.toString()
                }
            } catch (ignored: Exception) {
            } // for now eat exceptions
            return null
            /*try {
            // this is so Linux hack
            return loadFileAsString("/sys/class/net/" +interfaceName + "/address").toUpperCase().trim();
        } catch (IOException ex) {
            return null;
        }*/
        }

        /**
         * Get IP address from first non-localhost interface
         * @param useIPv4   true=return ipv4, false=return ipv6
         * @return  address or empty string
         */
        fun getIPAddress(useIPv4: Boolean): String? {
            try {
                val interfaces: List<NetworkInterface> =
                    Collections.list(NetworkInterface.getNetworkInterfaces())
                for (intf in interfaces) {
                    val addrs: List<InetAddress> = Collections.list(intf.inetAddresses)
                    for (addr in addrs) {
                        if (!addr.isLoopbackAddress) {
                            val sAddr = addr.hostAddress
                            //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                            val isIPv4 = sAddr.indexOf(':') < 0
                            if (useIPv4) {
                                if (isIPv4) return sAddr
                            } else {
                                if (!isIPv4) {
                                    val delim = sAddr.indexOf('%') // drop ip6 zone suffix
                                    return if (delim < 0) sAddr.toUpperCase() else sAddr.substring(
                                        0,
                                        delim
                                    ).toUpperCase()
                                }
                            }
                        }
                    }
                }
            } catch (ignored: Exception) {
            } // for now eat exceptions
            return ""
        }
    }


}