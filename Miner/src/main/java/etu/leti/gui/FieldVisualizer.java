package etu.leti.gui;

import etu.leti.generator.MapGenerator;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import etu.leti.field.Cell;
import etu.leti.gui.gridhandler.GridWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FieldVisualizer {

    private final GridWorker gridWorker;
    private final ClassLoader classLoader;
    private final MapGenerator mapGenerator;
    private int fieldWidth;
    private int fieldHeight;

    public FieldVisualizer(GridPane mainVisualField, ClassLoader classLoader, int fieldWidth, int fieldHeight) {
        gridWorker = new GridWorker(mainVisualField);
        mapGenerator = new MapGenerator(fieldWidth, fieldHeight);
        this.classLoader = classLoader;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    public void fillGridByCell(Cell @NotNull [] cells) {
        gridWorker.fillGridByCell(cells, classLoader);
    }

    public void resetField() {
        gridWorker.cleanGridCells();
    }

    public void setSize(int width, int height) {
        fieldWidth = width;
        fieldHeight = height;
    }

    public void createNewMap() {
        Cell[] generatedMap = convertArray(mapGenerator.generateMap());
//        Cell[][] generatedMap = mapGenerator.generateMap();
        resetField();
        fillGridByCell(generatedMap);
    }

    private Cell @NotNull [] convertArray(Cell[] @NotNull [] arr) {
        List<Cell> list = new ArrayList<>();
        for (Cell[] cells : arr) {
            Collections.addAll(list, cells);
        }

        Cell[] finalArray = new Cell[list.size()];
        for (int i = 0; i < finalArray.length; i++) {
            finalArray[i] = list.get(i);
        }

        return finalArray;
    }
}
