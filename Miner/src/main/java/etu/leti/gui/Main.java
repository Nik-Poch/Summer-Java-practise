package etu.leti.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

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
    public void start(@NotNull Stage primaryStage) throws Exception{
        window = primaryStage;

        URL scene = getClass().getClassLoader().getResource("canvas/miner.fxml");
        assert scene != null;

        FXMLLoader fxmlLoader = new FXMLLoader(scene);
        Parent root = (Parent) fxmlLoader.load();
        ((Controller) fxmlLoader.getController()).setStage(primaryStage);

        primaryStage.setTitle("Miner");
        primaryStage.setScene(new Scene(root));

        // Block resizing
        primaryStage.setMaxHeight(primaryStage.getHeight());
        primaryStage.setMaxWidth(primaryStage.getWidth());
        primaryStage.setResizable(false);

        // Set application icon
        primaryStage.getIcons().add(new Image("img/icon.png"));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}