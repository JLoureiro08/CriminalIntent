package com.loureiro.bignerdranch.criminalintent

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class CrimeDetailFragmentTest : TestCase() {

    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    @Before
    fun setup(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_CriminalIntent)

        scenario.moveToState(Lifecycle.State.STARTED)

    }

    @Test

    fun testCrimeTitle(){
        val desc = "Leaving dishes in the sink"
        onView(withId(R.id.crime_title)).perform(typeText(desc))
        Espresso.closeSoftKeyboard()

        onView(withId(R.id.crime_solved)).perform(click())
    }
}