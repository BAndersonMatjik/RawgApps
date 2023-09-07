package com.dev.rawgapps.common

import android.content.Context
import android.content.pm.ApplicationInfo


fun Context.isApplicationDebuggable():Boolean{
    return this.applicationInfo.flags == ApplicationInfo.FLAG_DEBUGGABLE
}