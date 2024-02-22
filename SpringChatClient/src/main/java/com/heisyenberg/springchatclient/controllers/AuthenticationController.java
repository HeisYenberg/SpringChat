package com.heisyenberg.springchatclient.controllers;

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
        getUserService().signIn(getUser())
                .enqueue(new AuthenticationCallback(signIn));
    }

    @FXML
    void onSignUp() {
        getUserService().signUp(getUser()).
                enqueue(new AuthenticationCallback(signUp));
    }

    private AuthenticationService getUserService() {
        RetrofitService service = new RetrofitService();
        return service.getRetrofit().create(AuthenticationService.class);
    }

    private User getUser() {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }
        return new User(null, username, password);
    }
}
