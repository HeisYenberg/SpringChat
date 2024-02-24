package com.heisyenberg.springchatclient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    public static void loadScene(Stage stage, String resource)
            throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(ChatApplication.class.getResource(resource));
        Scene scene = new Scene(fxmlLoader.load(), 480, 640);
        stage.setScene(scene);
    }

    public static void showError(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(message);
            alert.showAndWait();
        });
    }

    @Override
    public void start(Stage stage) throws IOException {
        ChatApplication.loadScene(stage, "authentication-view.fxml");
        stage.setTitle("Spring Chat");
        stage.show();
    }
}
