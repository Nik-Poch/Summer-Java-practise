package etu.leti.gui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExtraWindowShower {
    public static void showAboutWindow(Stage primaryStage) {
        StackPane authorInfLayout = new StackPane();
        Scene authInfScene = new Scene(authorInfLayout, 230, 100);

        Text text = new Text();
        text.setText("Hello, world!");
        authorInfLayout.getChildren().add(text);
//        scene.getStylesheets().add("style.css");

        Stage authInfWindow = new Stage();
        authInfWindow.setTitle("About authors");
        authInfWindow.setScene(authInfScene);

        authInfWindow.initModality(Modality.WINDOW_MODAL);

        authInfWindow.initOwner(primaryStage);

        authInfWindow.setX(primaryStage.getX() + 350);
        authInfWindow.setY(primaryStage.getY() + 350);

        authInfWindow.setMaxHeight(200);
        authInfWindow.setMaxWidth(300);
        authInfWindow.setResizable(false);

        authInfWindow.show();
    }
}
