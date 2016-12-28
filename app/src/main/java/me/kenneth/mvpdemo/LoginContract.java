package me.kenneth.mvpdemo;

import me.kenneth.mvpdemo.model.User;

/**
 * Created by kenneth on 2016/12/28.
 */

public interface LoginContract {
  interface View extends BaseView<Presenter> {
    void showPasswordError();

    void showEmailError();

    void showProgress(boolean show);

    void showPersonalCenter(User user);
  }

  interface Presenter extends BasePresenter {
    void attemptLogin(String email, String password);
  }
}
