package main.java.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    /**
     *
     * @param primaryStage
     * The surrounding window, which is used as the initial canvas and contains the remaining components
     * @throws Exception
     * For load method
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("miner.fxml"));
        primaryStage.setTitle("Miner");
        primaryStage.setScene(new Scene(root, 1024, 786));

        primaryStage.setMaxHeight(primaryStage.getHeight());
        primaryStage.setMaxWidth(primaryStage.getWidth());
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
