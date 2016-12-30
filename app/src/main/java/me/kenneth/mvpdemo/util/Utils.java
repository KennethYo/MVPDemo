package me.kenneth.mvpdemo.util;

import android.support.annotation.Nullable;

/**
 * Created by kenneth on 2016/12/29.
 */

public class Utils {

  public static boolean isEmpty(@Nullable CharSequence str) {
    if (str == null || str.length() == 0) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean isEmailValid(String email) {
    return email.contains("@");
  }

  public static boolean isPasswordValid(String password) {
    return password.length() > 4;
  }
}
