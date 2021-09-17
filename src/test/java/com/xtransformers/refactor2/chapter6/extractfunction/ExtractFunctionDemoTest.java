package com.xtransformers.refactor2.chapter6.extractfunction;

import com.xtransformers.refactor2.chapter6.extractfunction.wrapper.Clock;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExtractFunctionDemoTest {

    @Test
    public void clockTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Clock mock = mock(Clock.class);
        when(mock.today()).thenReturn(new Date(1631851200000L));
        Assert.assertEquals("2021-09-17 12:00:00", sdf.format(mock.today()));
        mock = new Clock();
        System.out.println(mock.today());
    }
}