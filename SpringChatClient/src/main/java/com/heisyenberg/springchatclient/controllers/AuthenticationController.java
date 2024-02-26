package com.heisyenberg.springchatclient.controllers;

import com.heisyenberg.springchatclient.ChatApplication;
import com.heisyenberg.springchatclient.callbacks.AuthenticationCallback;
import com.heisyenberg.springchatclient.models.User;
import com.heisyenberg.springchatclient.services.AuthenticationService;
import com.heisyenberg.springchatclient.services.RetrofitService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthenticationController {
    @FXML
    private TextField inputUsername;
    @FXML
    private PasswordField inputPassword;
    @FXML
    private Button signIn;
    @FXML
    private Button signUp;

    @FXML
    void onSignIn() {
        try {
            getUserService().signIn(getUser())
                    .enqueue(new AuthenticationCallback(signIn));
        } catch (RuntimeException e) {
            ChatApplication.showError(e.getMessage());
        }
    }

    @FXML
    void onSignUp() {
        try {
            getUserService().signUp(getUser()).
                    enqueue(new AuthenticationCallback(signUp));
        } catch (RuntimeException e) {
            ChatApplication.showError(e.getMessage());
        }
    }

    private AuthenticationService getUserService() {
        RetrofitService service = new RetrofitService();
        return service.getRetrofit().create(AuthenticationService.class);
    }

    private User getUser() {
        String username = inputUsername.getText().trim();
        String password = inputPassword.getText().trim();
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }
        if (username.length() > 40 || password.length() > 40) {
            throw new RuntimeException("Username or Password " +
                    "is out of bounds, 40 characters is the limit!");
        }
        return new User(null, username, password);
    }
}
