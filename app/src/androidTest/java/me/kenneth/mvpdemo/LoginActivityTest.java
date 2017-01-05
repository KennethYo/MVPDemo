package me.kenneth.mvpdemo;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by kenneth on 2016/12/30.
 */
@RunWith(AndroidJUnit4.class) @LargeTest public class LoginActivityTest {
  private static final String email = "1234@1234.com";
  private static final String password = "1234@1234.com";

  @Rule public ActivityTestRule<LoginActivity> mLoginActivityTestRule =
      new ActivityTestRule<>(LoginActivity.class);

  @Test public void login() {
    onView(withId(R.id.email)).perform(typeText(email));
    onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());

    onView(withId(R.id.email_sign_in_button)).perform(click());

    onView(withId(R.id.login_form)).check(matches(isDisplayed()));
  }
}