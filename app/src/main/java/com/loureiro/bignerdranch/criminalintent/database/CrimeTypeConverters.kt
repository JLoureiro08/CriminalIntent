package com.loureiro.bignerdranch.criminalintent.database

import androidx.room.TypeConverter
import java.util.Date

class CrimeTypeConverters {

    @TypeConverter                        //Makes the conversion from writing a date and reading a date
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }

}
