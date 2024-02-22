package com.heisyenberg.springchatclient.listcells;

import com.heisyenberg.springchatclient.ChatApplication;
import com.heisyenberg.springchatclient.models.ChatRoom;
import com.heisyenberg.springchatclient.session.UserSession;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatRoomsListCell extends ListCell<ChatRoom> {
    @Override
    protected void updateItem(ChatRoom item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            setText(item.getName());
        } else {
            setText(null);
        }
        setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && item != null) {
                UserSession.getInstance().setChatRoom(item);
                Stage stage = (Stage) this.getScene().getWindow();
                try {
                    ChatApplication.loadScene(stage, "messages-view.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        setStyle("-fx-background-color: #0e1621; " +
                "-fx-border-color: #1d242d; " +
                "-fx-text-fill: white ; " +
                "-fx-font-size: 18;");
    }
}
