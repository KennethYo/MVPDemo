package me.kenneth.mvpdemo.data.remote;

import android.os.Handler;
import android.support.annotation.NonNull;
import me.kenneth.mvpdemo.data.LoginDataSource;
import me.kenneth.mvpdemo.model.User;

/**
 * Created by kenneth on 2016/12/28.
 */

public class LoginRemoteDataSource implements LoginDataSource {

  private int i;

  public LoginRemoteDataSource() {
  }

  @Override public void login(@NonNull String email, @NonNull String password,
      @NonNull final LoginCallback callback) {

    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override public void run() {

        if (i++ % 2 == 1) {
          callback.onLoginSuccess(new User());
        } else {
          callback.onLoginFailure();
        }
      }
    }, 3 * 1000);
  }
}
