package com.guigarage;

import com.canoo.dolphin.client.ClientConfiguration;
import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.ClientContextFactory;
import com.canoo.dolphin.client.javafx.JavaFXConfiguration;
import com.guigarage.view.MyViewBinder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApplication extends Application {

    private ClientContext clientContext;

    @Override
    public void init() throws Exception {
        ClientConfiguration config = new JavaFXConfiguration("http://localhost:8080/dolphin");
        clientContext = ClientContextFactory.connect(config).get();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(ClientApplication.class.getResource("view.fxml"));
        loader.setController(new MyViewBinder(clientContext));

        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
