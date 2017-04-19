package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ConfigManager configManager = new ConfigManager();
        Config config = configManager.getConfig();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.initConfig(config);


        primaryStage.setTitle("AES Encryptor and Decryptor");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
