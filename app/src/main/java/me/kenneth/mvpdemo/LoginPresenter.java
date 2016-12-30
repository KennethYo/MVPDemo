package me.kenneth.mvpdemo;

import android.support.annotation.NonNull;
import me.kenneth.mvpdemo.data.LoginDataSource;
import me.kenneth.mvpdemo.model.User;
import me.kenneth.mvpdemo.util.Utils;

/**
 * Created by kenneth on 2016/12/28.
 */

public class LoginPresenter implements LoginContract.Presenter {
  private final LoginContract.View mView;
  private final LoginDataSource mDataSource;

  public LoginPresenter(@NonNull LoginDataSource dataSource,
      @NonNull LoginContract.View view) {
    this.mView = view;
    this.mDataSource = dataSource;
  }

  @Override public void start() {

  }

  @Override public void attemptLogin(String email, String password) {
    if (Utils.isEmpty(password) || !Utils.isPasswordValid(password)) {
      mView.showPasswordError();
      return;
    }

    if (Utils.isEmpty(email) || !Utils.isEmailValid(email)) {
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

  @Override public void forgetPassword() {
    mView.showForgetPassword();
  }
}
