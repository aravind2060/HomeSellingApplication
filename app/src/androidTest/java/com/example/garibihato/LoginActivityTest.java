package com.example.garibihato;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity>loginActivityActivityTestRule=new ActivityTestRule<>(LoginActivity.class);
    private LoginActivity loginActivity=null;
    @Before
    public void setUp() throws Exception {
        loginActivity=loginActivityActivityTestRule.getActivity();
    }
    //check button in activity
    @Test
    public void testLaunch()
    {
        View view=loginActivity.findViewById(R.id.signin);
        assertNotNull(view);
    }
    //check signin
    @Test
    public void checkButtonClick()
    {
        Espresso.onView(withId(R.id.signin)).perform(click());
        Espresso.onView(withId(R.id.email_in_sign_in)).perform(typeText("aravind4532@gmail.com"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.password_in_signin)).perform(typeText("Aravind@1"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.button_signin)).perform(click());

    }

    @After
    public void tearDown() throws Exception {
        loginActivity=null;
    }
}