package etu.leti.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.JsonParseException;
import etu.leti.field.Cell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import etu.leti.parser.MapParser;
import org.w3c.dom.Text;

public class Controller implements Initializable {

    private static final int fieldWidth = 20;
    private static final int fieldHeight = 21;

    private MapParser mapParser;
    private FieldVisualizer fieldVisualizer;
    Stage primaryStage;

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
    @FXML
    private TextArea algAboutTextArea;

    // MENU BAR ITEMS

    @FXML
    private MenuItem saveButton;
    @FXML
    private MenuItem loadButton;
    @FXML
    private MenuItem resetButton;
    @FXML
    private MenuItem generateMapButton;
    @FXML
    private MenuItem aboutAlgButton;
    @FXML
    private MenuItem aboutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();

        mapParser = new MapParser();
        fieldVisualizer = new FieldVisualizer(mainVisualField, this.getClass().getClassLoader(), fieldWidth, fieldHeight);
    }

    public void runAlgorithm(ActionEvent event) {

    }

    public void madeOneStep(ActionEvent event) {

    }

    public void loadFile(@NotNull ActionEvent event) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainStage.getScene().getWindow());

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");
        alert.setHeaderText("JSON map file");

        mapParser.setJsonFile(file);
        Cell[] cellField;
        try {
            cellField = mapParser.parseCellArr();
        } catch (FileNotFoundException exc) {
            alert.setContentText("No valid file with map was choosen!");
            alert.showAndWait();
            return;
        } catch (JsonParseException exc) {
            alert.setContentText("JSON file data is incorrect!");
            alert.showAndWait();
            return;
        }

        try {
            fieldVisualizer.fillGridByCell(cellField);
        } catch (JsonParseException exc) {
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        }
    }

    public void saveFile(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainStage.getScene().getWindow());
    }

    public void resetField(ActionEvent event) {
        fieldVisualizer.resetField();
    }

    public void generateMap(ActionEvent event) {
        fieldVisualizer.createNewMap();
    }

    public void getAlgInformation(ActionEvent event) throws IOException {
        ExtraWindowShower.showProgInfoWindow(primaryStage, getClass().getClassLoader());
    }

    public void getAuthorInformation(ActionEvent event) {
        ExtraWindowShower.showAboutWindow(primaryStage);
    }

    public void setStage(Stage primaryStage){
        this.primaryStage = primaryStage;
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
