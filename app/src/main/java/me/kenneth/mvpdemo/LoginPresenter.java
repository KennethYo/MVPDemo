package me.kenneth.mvpdemo;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import me.kenneth.mvpdemo.data.LoginDataSource;
import me.kenneth.mvpdemo.model.User;

/**
 * Created by kenneth on 2016/12/28.
 */

public class LoginPresenter implements LoginContract.Presenter {
  private final LoginContract.View mView;
  private final LoginDataSource mDataSource;

  public LoginPresenter(@NonNull LoginDataSource dataSource, @NonNull LoginContract.View view) {
    this.mView = view;
    this.mDataSource = dataSource;
  }

  @Override public void start() {

  }

  @Override public void attemptLogin(String email, String password) {
    if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
      mView.showPasswordError();
      return;
    }

    if (TextUtils.isEmpty(email) || !isEmailValid(email)) {
      mView.showEmailError();
      return;
    }

    mView.showProgress(true);
    mDataSource.login(email, password, new LoginDataSource.LoginCallback() {
      @Override public void onLoginSuccess(User user) {
        if (mView.isActive()) {
          mView.showProgress(false);
          mView.showPersonalCenter(user);
        }
      }

      @Override public void onLoginFailure() {
        if (mView.isActive()) {
          mView.showProgress(false);
          mView.showPasswordError();
        }
      }
    });
  }

  private boolean isEmailValid(String email) {
    return email.contains("@");
  }

  private boolean isPasswordValid(String password) {
    return password.length() > 4;
  }
}
