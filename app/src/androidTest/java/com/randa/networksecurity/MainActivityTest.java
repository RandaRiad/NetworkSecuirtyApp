package com.randa.networksecurity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

  @Rule
  public ActivityScenarioRule<MainActivity> mainActivity=new ActivityScenarioRule<MainActivity>(MainActivity.class);

  @Test
  public void encryptTest(){
    onView(withId(R.id.emailText)).perform(typeText("Text"));
    onView(withId(R.id.passwordText)).perform(typeText("123"));
    onView(withId(R.id.encryptBtn)).perform(click());
    onView(withId(R.id.decryptBtn)).perform(click());
    onView(withId(R.id.showPassword)).check(matches(withText("Text")));


  }

}