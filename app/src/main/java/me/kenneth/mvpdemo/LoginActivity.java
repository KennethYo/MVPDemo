package me.kenneth.mvpdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.kenneth.mvpdemo.data.remote.LoginRemoteDataSource;
import me.kenneth.mvpdemo.model.User;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {

  // UI references.
  @BindView(R.id.email) AutoCompleteTextView mEmailView;
  @BindView(R.id.password) EditText mPasswordView;
  @BindView(R.id.login_progress) View mProgressView;
  @BindView(R.id.login_form) View mLoginFormView;
  @BindView(R.id.email_sign_in_button) Button mEmailSignInButton;
  private LoginPresenter mPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);

    mPresenter = new LoginPresenter(new LoginRemoteDataSource(), this);
  }

  @OnClick(R.id.email_sign_in_button) public void attemptLogin() {
    mEmailView.setError(null);
    mPasswordView.setError(null);

    String email = mEmailView.getText().toString();
    String password = mPasswordView.getText().toString();
    mPresenter.attemptLogin(email, password);
  }

  @Override public void showPasswordError() {
    mPasswordView.setError(getString(R.string.error_invalid_password));
    mPasswordView.requestFocus();
  }

  @Override public void showEmailError() {
    mEmailView.setError(getString(R.string.error_invalid_email));
    mEmailView.requestFocus();
  }

  @Override public void showProgress(final boolean show) {
    int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    mLoginFormView.animate()
        .setDuration(shortAnimTime)
        .alpha(show ? 0 : 1)
        .setListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
          }
        });

    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    mProgressView.animate()
        .setDuration(shortAnimTime)
        .alpha(show ? 1 : 0)
        .setListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
          }
        });
  }

  @Override public void showPersonalCenter(User user) {
    final Snackbar snackbar =
        Snackbar.make(mEmailView, R.string.personal_center, Snackbar.LENGTH_INDEFINITE);
    snackbar.setAction(android.R.string.ok, new View.OnClickListener() {
      @Override public void onClick(View v) {
        snackbar.dismiss();
      }
    });

    snackbar.show();
  }

  @Override public boolean isActive() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
      return !isFinishing();
    } else {
      return !isDestroyed();
    }
  }
}

