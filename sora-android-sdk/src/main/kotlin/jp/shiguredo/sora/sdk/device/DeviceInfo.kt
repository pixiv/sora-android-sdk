package jp.shiguredo.sora.sdk.device

import android.os.Build

object DeviceInfo {

    fun description() : String {
        return "Android-SDK: " + Build.VERSION.SDK_INT + ", " +
                "Release: " + Build.VERSION.RELEASE + ", " +
                "Id: " + Build.ID + ", " +
                "Device: " + Build.DEVICE + ", " +
                "Hardware: " + Build.HARDWARE + ", " +
                "Brand: " + Build.BRAND + ", " +
                "Manufacturer: " + Build.MANUFACTURER + ", " +
                "Model: " + Build.MODEL + ", " +
                "Product: " + Build.PRODUCT
    }

}