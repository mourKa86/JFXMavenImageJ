package com.myGroup;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    public static void main (String[] args){
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }
}
