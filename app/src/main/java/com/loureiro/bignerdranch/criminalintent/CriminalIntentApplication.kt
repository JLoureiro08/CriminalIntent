package com.loureiro.bignerdranch.criminalintent

import android.app.Application

class CriminalIntentApplication: Application() {      //Used for the repository initialization

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }

}