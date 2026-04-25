package com.skillio.base;

import com.skillio.utils.App;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.skillio.base.Keyword.*;

public class Hooks {

    @Before
    public void setUp() {
        openBrowser(App.getBrowserName());
        launchUrl(App.getappUrl("qa"));
    }

    @After
    public void tearDown() {
        quitBrowser(); 
    }
}