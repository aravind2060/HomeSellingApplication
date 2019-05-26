package com.example.garibihato;

import org.junit.Test;

import static org.junit.Assert.*;

public class calculatorTest {

    calculator cal=new calculator();
    @Test
    public void checkTimeFormat()
    {

        assertEquals(true,cal.isValidTime("9.988"));
    }
     @Test
    public void checkPrincipalAmount()
     {
         assertEquals(true,cal.isValidprincipal("12200"));
         assertEquals(true,cal.isValidprincipal("11f"));
     }
     @Test
     public void checkRate()
     {
         assertEquals(true,cal.isValidRate("12.00F"));
          assertEquals(true,cal.isValidRate("400.3.3")); //here it should return false

     }
}