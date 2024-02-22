package com.heisyenberg.springchatclient.listcells;

import com.heisyenberg.springchatclient.models.Message;
import com.heisyenberg.springchatclient.session.UserSession;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;

public class MessagesListCell extends ListCell<Message> {
    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            setText(item.getSender().getUsername() + ": " + item.getText());
            if (item.getSender().equals(UserSession.getInstance().getUser())) {
                setAlignment(Pos.BASELINE_RIGHT);
            } else {
                setAlignment(Pos.BASELINE_LEFT);
            }
        } else {
            setText(null);
        }
        setStyle("-fx-background-color: #0e1621; " +
                "-fx-text-fill: white ; " +
                "-fx-font-size: 18;");
    }
}
