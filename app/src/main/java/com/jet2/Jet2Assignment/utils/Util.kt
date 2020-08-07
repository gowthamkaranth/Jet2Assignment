package com.jet2.Jet2Assignment.utils

import java.text.SimpleDateFormat
import java.util.*

fun compareDate(pastTime:String):Long{
val sdf =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
val systemDate = Calendar.getInstance().getTime()
val myDate= sdf.format(systemDate)
val currentDate = sdf.parse(myDate)
val date = sdf.parse(pastTime)
val millse = currentDate.getTime() - date.getTime();
val mills = Math.abs(millse)
val days = (mills/ (1000 * 60 * 60 * 24))
    return days
}