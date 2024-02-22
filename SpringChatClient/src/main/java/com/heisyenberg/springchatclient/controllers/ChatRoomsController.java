package com.heisyenberg.springchatclient.controllers;

import com.heisyenberg.springchatclient.ChatApplication;
import com.heisyenberg.springchatclient.callbacks.ChatRoomsCallback;
import com.heisyenberg.springchatclient.models.ChatRoom;
import com.heisyenberg.springchatclient.models.User;
import com.heisyenberg.springchatclient.services.ChatRoomsService;
import com.heisyenberg.springchatclient.services.RetrofitService;
import com.heisyenberg.springchatclient.session.UserSession;
import com.heisyenberg.springchatclient.websocketclients.ChatRoomsWebSocketClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retrofit2.Call;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatRoomsController implements Initializable {
    @FXML
    private ListView<ChatRoom> roomsList;
    @FXML
    private TextField newRoomName;
    @FXML
    private Label username;
    @FXML
    private Button backButton;
    private ChatRoomsWebSocketClient webSocketClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getRooms();
        username.setText(UserSession.getInstance().getUser().getUsername());
        webSocketClient = new ChatRoomsWebSocketClient(this);
        webSocketClient.connect();
    }

    private ChatRoomsService getService() {
        RetrofitService retrofitService = new RetrofitService();
        return retrofitService.getRetrofit().create(ChatRoomsService.class);
    }

    public void getRooms() {
        Call<List<ChatRoom>> call = getService().getRooms();
        call.enqueue(new ChatRoomsCallback(roomsList));
    }

    @FXML
    public void onBackButton() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        try {
            ChatApplication.loadScene(stage, "authentication-view.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        webSocketClient.disconnect();
    }

    @FXML
    public void onCreateButton() {
        String roomName = newRoomName.getText();
        if (roomName.isEmpty() || webSocketClient == null) {
            return;
        }
        User owner = UserSession.getInstance().getUser();
        ChatRoom room = new ChatRoom(null, roomName, owner);
        webSocketClient.send("/app/createRoom", room);
        newRoomName.clear();
    }
}
