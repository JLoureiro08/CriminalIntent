package com.loureiro.bignerdranch.criminalintent.database

import androidx.room.Dao
import androidx.room.Query
import com.loureiro.bignerdranch.criminalintent.Crime
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")                     //The Query notation refers to the fact that these functions pull
    fun getCrimes(): Flow<List<Crime>>             //from the database

    @Query("SELECT * FROM crime WHERE id=(:id)")
    suspend fun getCrime(id: UUID): Crime

}
