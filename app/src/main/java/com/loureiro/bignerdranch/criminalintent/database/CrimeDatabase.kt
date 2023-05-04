package com.loureiro.bignerdranch.criminalintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.loureiro.bignerdranch.criminalintent.Crime

@Database(entities = [Crime::class], version = 1)    //Represents the database in the class
@TypeConverters(CrimeTypeConverters::class)          //Tells the database to use the function in that class when converting

abstract class CrimeDatabase:RoomDatabase() {

    abstract fun crimeDao(): CrimeDao                //Dao = Database access object (Holds all the access functions)

}