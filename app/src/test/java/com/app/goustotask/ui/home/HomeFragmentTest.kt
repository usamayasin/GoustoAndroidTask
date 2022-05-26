package com.app.goustotask.ui.home

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.app.goustotask.R
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {

    private lateinit var scenario: FragmentScenario<HomeFragment>

    @Test
    fun `when fragment resumed ,check if its visibility`() {

        scenario = launchFragmentInContainer(
            themeResId = R.style.Theme_GoustoTask,
            initialState = Lifecycle.State.RESUMED
        )
        // trigger update event
        scenario.onFragment {

            // then assert views are visible
            Espresso.onView(withId(R.id.tvProductslabel))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(withId(R.id.rvProducts))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}