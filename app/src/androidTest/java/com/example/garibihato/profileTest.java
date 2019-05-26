package com.example.garibihato;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class profileTest {

    @Rule
    public ActivityTestRule<profile>profileActivityTestRule=new ActivityTestRule<>(profile.class);
    private profile profilee=null;
    @Before
    public void setUp() throws Exception {

    profilee=profileActivityTestRule.getActivity();
    }
   @Test
   public void testLaunch()
   {
       View view=profilee.findViewById(R.id.phone_in_profile);
       assertNotNull(view);
   }
    @After
    public void tearDown() throws Exception {
        profilee=null;
    }
}