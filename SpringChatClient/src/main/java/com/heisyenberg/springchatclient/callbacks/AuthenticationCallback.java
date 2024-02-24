package com.heisyenberg.springchatclient.callbacks;

import com.heisyenberg.springchatclient.ChatApplication;
import com.heisyenberg.springchatclient.models.User;
import com.heisyenberg.springchatclient.session.UserSession;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class AuthenticationCallback implements Callback<User> {
    private final Button button;

    public AuthenticationCallback(Button button) {
        this.button = button;
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()) {
            User user = response.body();
            if (user == null) {
               ChatApplication.showError(
                       "User already exist, or invalid credentials!");
            } else {
                UserSession.getInstance().setUser(user);
                Platform.runLater(() -> {
                    Stage stage = (Stage) button.getScene().getWindow();
                    try {
                        ChatApplication.loadScene(stage, "chatrooms-view.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable throwable) {
       ChatApplication.showError("Unable to connect to server!");
    }
}
