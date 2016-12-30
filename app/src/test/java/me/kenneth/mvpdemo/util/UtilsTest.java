package me.kenneth.mvpdemo.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Utils test
 * Created by kenneth on 2016/12/29.
 */
public class UtilsTest {
  @Test public void isEmpty_true() throws Exception {
    Assert.assertTrue(Utils.isEmpty(""));
  }

  @Test public void isEmpty_false() throws Exception {
    Assert.assertFalse(Utils.isEmpty("abc"));
  }

  @Test public void isEmailValid_true() throws Exception {
    Assert.assertTrue(Utils.isEmailValid("1234@"));
  }

  @Test public void isEmailValid_false() throws Exception {
    Assert.assertFalse(Utils.isEmailValid("1234"));
  }

  @Test public void isPasswordValid_true() throws Exception {
    Assert.assertTrue(Utils.isPasswordValid("1234@"));
  }

  @Test public void isPasswordValid_false() throws Exception {
    Assert.assertFalse(Utils.isPasswordValid("123"));
  }
}