package com.example.caracample.activity;

import android.os.Bundle;

import junit.framework.TestCase;

import org.junit.Test;

public class LoginActivityTest extends TestCase {

    LoginActivity la = new LoginActivity();

    @Test
    public void test_loginSuccess(){

        String id = "CaravanAdmin";
        String pw = "1234";
        String result = la.login(id,pw);
        assertEquals("true",result);

    }

    @Test
    public void test_loginFail(){

        String id = "CaravanAdmin";
        String pw = "123";
        String result = la.login(id,pw);
        assertEquals("false",result);

    }

}