package com.dev.rawgapps.common

import android.content.Context
import android.content.pm.ApplicationInfo


fun Context.isApplicationDebuggable():Boolean{
    return (this.applicationInfo.flags.and(ApplicationInfo.FLAG_DEBUGGABLE))!=0
}