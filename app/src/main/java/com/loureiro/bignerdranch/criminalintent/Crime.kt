package com.loureiro.bignerdranch.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity                  //Tells that this class is a database class it sets the structure of a table
data class Crime(

    @PrimaryKey val id: UUID,       //Each of these is a column in the table
    val title: String,
    val date: Date,
    val isSolved: Boolean
)
