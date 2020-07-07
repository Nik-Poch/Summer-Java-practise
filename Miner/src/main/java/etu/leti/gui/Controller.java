package etu.leti.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import etu.leti.gui.gridhandler.ImageCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.jetbrains.annotations.NotNull;

public class Controller implements Initializable {

    @FXML
    private VBox mainStage;

    // For ChooseBox variants
    ObservableList<String> listOfModesNames = FXCollections.observableArrayList();

    @FXML
    private GridPane mainVisualField;

    @FXML
    private Button runButton;

    @FXML
    private Button stepButton;

    @FXML
    private ChoiceBox<String> modeChooseBox;

    @FXML
    private TextArea logTextField;

    // MENU BAR ITEMS

    @FXML
    private MenuItem saveButton;

    @FXML
    private MenuItem loadButton;

    @FXML
    private MenuItem resetButton;

    @FXML
    private MenuItem aboutAlgButton;

    @FXML
    private MenuItem aboutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    public void runAlgorithm(ActionEvent event) throws FileNotFoundException {

    mainVisualField.add(new ImageCell(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("ground.jpg")))), 0, 0);

    }

    public void madeOneStep(ActionEvent event) {

    }

    public void loadFile(@NotNull ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainStage.getScene().getWindow());
    }

    public void saveFile(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainStage.getScene().getWindow());
    }

    public void resetField(ActionEvent event) {

    }

    public void getAlgInformation(ActionEvent event) {

    }

    public void getAuthorInformation(ActionEvent event) {

    }

    private void loadData() {
        // delete all possible data for avoid duplication
        listOfModesNames.removeAll();
        String stepByStepStr = "Step by step";
        String justResultStr = "Just result";
        listOfModesNames.addAll(stepByStepStr, justResultStr);
        modeChooseBox.getItems().addAll(listOfModesNames);
    }
}
