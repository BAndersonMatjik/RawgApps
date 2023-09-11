package com.dev.rawgapps.common

import java.text.SimpleDateFormat

object DateFormatter {
    //If null will give back value from parameter
    fun formatDate(date:String,oldPattern:String="yyyy-MM-dd",newPattern:String="MMM dd,yyyy"):String{
        if (date.isBlank()){
            return date
        }
        var formatter = SimpleDateFormat(oldPattern)
        val date = formatter.parse(date)
        formatter  = SimpleDateFormat(newPattern)
       return formatter.format(date)
    }

}