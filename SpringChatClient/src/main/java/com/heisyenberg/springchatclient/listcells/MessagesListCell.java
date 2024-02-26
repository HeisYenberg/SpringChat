package com.heisyenberg.springchatclient.listcells;

import com.heisyenberg.springchatclient.models.Message;
import com.heisyenberg.springchatclient.session.UserSession;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MessagesListCell extends ListCell<Message> {
    private static final int MESSAGE_WIDTH = 400;

    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);
        setStyle("-fx-background-color: #0e1621;");
        if (item == null || empty) {
            setText(null);
            return;
        }
        HBox hBox = new HBox();
        Text text = new Text(
                item.getSender().getUsername() + ": " + item.getText());
        text.setFill(Color.WHITE);
        text.setFont(Font.font(18));
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(MESSAGE_WIDTH);
        textFlow.setPadding(new Insets(5, 5, 5, 5));
        if (item.getSender().equals(UserSession.getInstance().getUser())) {
            textFlow.setStyle("-fx-background-color: #2b5278; " +
                    "-fx-background-radius: 20px;");
            hBox.setAlignment(Pos.CENTER_RIGHT);
        } else {
            textFlow.setStyle("-fx-background-color: #182533; " +
                    "-fx-background-radius: 20px;");
            hBox.setAlignment(Pos.CENTER_LEFT);
        }
        hBox.getChildren().add(textFlow);
        setGraphic(hBox);
    }
}
