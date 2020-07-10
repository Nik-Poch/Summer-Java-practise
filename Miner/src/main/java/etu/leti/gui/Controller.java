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
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import org.jetbrains.annotations.NotNull;

import etu.leti.parser.MapParser;

public class Controller implements Initializable {

    enum Mode {
        STEP_BY_STEP, JUST_RESULT, NO_MODE
    }

    private static final int fieldWidth = 20;
    private static final int fieldHeight = 21;

    private MapParser mapParser;
    private FieldVisualizer fieldVisualizer;
    private Mode currentMode = Mode.NO_MODE;

    // For save/load file
    private Alert alert;

    @FXML
    private VBox mainStage;
    @FXML
    private GridPane mainVisualField;
    @FXML
    private Button runButton;
    @FXML
    private Button stepButton;
    @FXML
    private ChoiceBox<String> modeChoiceBox;
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
    private MenuItem generateMapButton;
    @FXML
    private MenuItem aboutAlgButton;
    @FXML
    private MenuItem aboutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDataForChoiceBox();
        initAlert();

        modeChoiceBox.setOnAction(this::modeChanged);
        mapParser = new MapParser();
        fieldVisualizer = new FieldVisualizer(mainVisualField, this.getClass().getClassLoader(), fieldWidth, fieldHeight);
    }

    public void runAlgorithm(ActionEvent event) {
        if(currentMode == Mode.NO_MODE) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mode alert");
            alert.setHeaderText("Selected mode");
            alert.setContentText("You didn't select the mode in which the algorithm should work. " +
                                "Please do this and try again.");

            alert.showAndWait();
        } else if(currentMode == Mode.JUST_RESULT) {
            fieldVisualizer.showResultWay();
        } else {
            fieldVisualizer.stepByStepWay();
            runButton.setDisable(true);
        }
    }

    public void madeOneStep(ActionEvent event) {
        if(fieldVisualizer.makeOneStep()) {
            runButton.setDisable(false);
        }
    }

    public void loadFile(@NotNull ActionEvent event) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll((new FileChooser.ExtensionFilter("JSON", "*.json")));
        File file = fileChooser.showOpenDialog(mainStage.getScene().getWindow());

        mapParser.setJsonFile(file);
        Cell[] cellFieldFromFile;

        try {
            cellFieldFromFile = mapParser.parseCellArr();
        } catch (FileNotFoundException exc) {
            alert.setContentText("No valid file with map was choosen!");
            alert.showAndWait();
            return;
        } catch (JsonParseException exc) {
            alert.setContentText("JSON file data is incorrect!");
            alert.showAndWait();
            return;
        }

        fieldVisualizer.setWorkingMap(cellFieldFromFile);

        try {
            fieldVisualizer.fillGridByCell();
        } catch (JsonParseException | ArrayIndexOutOfBoundsException exc) {
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        }
    }

    public void saveFile(ActionEvent event) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll((new FileChooser.ExtensionFilter("JSON", "*.json")));
        File file = fileChooser.showSaveDialog(mainStage.getScene().getWindow());

        mapParser.setJsonFile(file);

        if(file == null) {
            alert.setContentText("No valid file was choosen");
            alert.showAndWait();
            return;
        }

        mapParser.writeCellArray(fieldVisualizer.getWorkingMapAsArray());
    }

    public void resetField(ActionEvent event) {
        fieldVisualizer.resetField();
    }

    public void generateMap(ActionEvent event) {
        fieldVisualizer.resetField();
        fieldVisualizer.createNewMap();
    }

    public void getAlgInformation(ActionEvent event) {

    }

    public void getAuthorInformation(ActionEvent event) {

    }

    private void loadDataForChoiceBox() {
        // For ChoiceBox variants
        ObservableList<String> listOfModesNames = FXCollections.observableArrayList();
        // delete all possible data for avoid duplication
        listOfModesNames.removeAll();
        String stepByStepStr = "Step by step";
        String justResultStr = "Just result";
        listOfModesNames.addAll(stepByStepStr, justResultStr);
        modeChoiceBox.getItems().addAll(listOfModesNames);
    }

    private void initAlert() {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");
        alert.setHeaderText("JSON map file");
    }

    @FXML
    private void modeChanged(ActionEvent event) {
        if(modeChoiceBox.getValue().equals("Step by step")) {
            currentMode = Mode.STEP_BY_STEP;
        } else if(modeChoiceBox.getValue().equals("Just result")) {
            runButton.setDisable(false);
            currentMode = Mode.JUST_RESULT;
        }
    }
}
