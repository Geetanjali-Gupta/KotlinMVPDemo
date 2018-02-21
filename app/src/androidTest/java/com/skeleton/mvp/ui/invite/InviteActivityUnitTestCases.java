package com.skeleton.mvp.ui.invite;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.skeleton.mvp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Developer: Click-Labs
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class InviteActivityUnitTestCases {
    @Rule
    public ActivityTestRule<InviteActivity> activityTestRule = new ActivityTestRule<>(InviteActivity.class,
            true,
            true);

    @Before
    public void initialiseActivity() throws Throwable {
        activityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().showNetworkError();
            }
        });
    }

   /* @Test
    public void ensure_NoInternetDialogTitleIsCorrect() {
        int titleId = activityTestRule.getActivity().getResources()
                .getIdentifier("alertTitle", "id", "android");
        onView(withId(titleId))
                .inRoot(isDialog())
                .check(matches(withText(R.string.error_internet_not_connected)))
                .check(matches(isDisplayed()));

    }*/

    @Test
    public void ensure_NoInternetDialogMessageIsCorrect() {
        onView(withId(android.R.id.message))
                .inRoot(isDialog())
                .check(matches(withText(R.string.error_internet_not_connected)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void ensure_NoInternetDialogButtonMessageIsCorrect() {
        onView(withId(android.R.id.button1))//Positive Button ID
                .inRoot(isDialog())
                .check(matches(withText(R.string.text_retry)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void ensure_NoInternetDialogButtonRetryClickIsCorrect() {
        onView(withId(android.R.id.button1)).perform(click());
    }

    @After
    public void destroyActivity() {
        activityTestRule.finishActivity();
    }
}
