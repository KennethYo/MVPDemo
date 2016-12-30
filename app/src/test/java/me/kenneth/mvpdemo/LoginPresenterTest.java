package me.kenneth.mvpdemo;

import me.kenneth.mvpdemo.data.LoginDataSource;
import me.kenneth.mvpdemo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kenneth on 2016/12/29.
 */
public class LoginPresenterTest {
  @Mock private LoginContract.View view;
  @Mock private LoginDataSource source;
  @Captor private ArgumentCaptor<LoginDataSource.LoginCallback> loginCallbackCaptor;
  private LoginPresenter presenter;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    presenter = new LoginPresenter(source, view);

    when(view.isActive()).thenReturn(true);
  }

  @Test public void attemptLogin_email_error() {
    String email = "1234";
    String password = "12345";

    presenter.attemptLogin(email, password);

    verify(view).showEmailError();
  }

  @Test public void attemptLogin_password_error() {
    String email = "1234@";
    String password = "1234";

    presenter.attemptLogin(email, password);

    verify(view).showPasswordError();
  }

  @Test public void attemptLogin_success() {
    String email = "1234@";
    String password = "12345";
    User user = new User();

    presenter.attemptLogin(email, password);

    InOrder inOrder = inOrder(view);
    inOrder.verify(view).showProgress(true);

    verify(source).login(anyString(), anyString(), loginCallbackCaptor.capture());
    loginCallbackCaptor.getValue().onLoginSuccess(user);

    inOrder.verify(view).showProgress(false);
    verify(view).showPersonalCenter(user);
  }

  @Test public void attemptLogin_error() {
    String email = "1234@";
    String password = "12345";

    presenter.attemptLogin(email, password);

    verify(view).showProgress(true);

    verify(source).login(anyString(), anyString(), loginCallbackCaptor.capture());

    loginCallbackCaptor.getValue().onLoginFailure();

    verify(view).showProgress(false);
    verify(view).showPasswordError();
  }

  @Test public void attemptLogin_error_with_answer() {
    String email = "1234@";
    String password = "12345";

    doAnswer(new Answer() {
      @Override public Object answer(InvocationOnMock invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        LoginDataSource.LoginCallback callback = (LoginDataSource.LoginCallback) arguments[2];
        callback.onLoginFailure();
        return null;
      }
    }).when(source).login(anyString(), anyString(), any(LoginDataSource.LoginCallback.class));

    presenter.attemptLogin(email, password);

    verify(view).showProgress(true);
    verify(view).showProgress(false);
    verify(view).showPasswordError();
  }

  @Test public void forgetPassword() {
    presenter.forgetPassword();

    verify(view).showForgetPassword();
  }
}