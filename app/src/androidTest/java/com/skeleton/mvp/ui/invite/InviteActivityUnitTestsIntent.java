package com.skeleton.mvp.ui.invite;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.skeleton.mvp.R;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasType;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Developer: Click-Labs
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class InviteActivityUnitTestsIntent {
    @Rule
    public IntentsTestRule<InviteActivity> intentsTestRule = new IntentsTestRule<>(InviteActivity.class,
            true,
            true);
    private InviteActivity inviteActivity;

    @Before
    public void initialiseIntentActivity() {
        inviteActivity = intentsTestRule.getActivity();
        inviteActivity.shareLink("");
        // Stubbing to block all external intents
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void ensure_chooserIntentCreated() {
        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_CHOOSER),
                hasExtras(allOf(hasEntry(equalTo(Intent.EXTRA_INTENT), allOf(hasType("text/html"), hasAction(Intent.ACTION_SEND))))));
        intended(expectedIntent);
    }

    @After
    public void destroyActivity() throws Throwable {
        intentsTestRule.finishActivity();
    }
}
