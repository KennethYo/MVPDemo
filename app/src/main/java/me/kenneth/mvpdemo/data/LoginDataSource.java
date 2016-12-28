package me.kenneth.mvpdemo.data;

import android.support.annotation.NonNull;
import me.kenneth.mvpdemo.model.User;

/**
 * Created by kenneth on 2016/12/28.
 */

public interface LoginDataSource {
  interface LoginCallback {
    void onLoginSuccess(User user);

    void onLoginFailure();
  }

  void login(@NonNull String email, @NonNull String password, @NonNull LoginCallback callback);
}
