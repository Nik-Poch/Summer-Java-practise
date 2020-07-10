package etu.leti.gui;

import etu.leti.generator.MapGenerator;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import etu.leti.field.Cell;
import etu.leti.gui.gridhandler.GridWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieldVisualizer {

    private final GridWorker gridWorker;
    private final ClassLoader classLoader;
    private final MapGenerator mapGenerator;

    public Cell[][] getWorkingMap() {
        return workingMap;
    }

    public void setWorkingMap(Cell[][] workingMap) {
        this.workingMap = workingMap;
    }

    public void setWorkingMap(Cell[] workingMap) {
        this.workingMap = convertArray(workingMap, fieldHeight, fieldWidth);
    }

    private Cell[][] workingMap;
    private int fieldWidth;
    private int fieldHeight;

    public FieldVisualizer(GridPane mainVisualField, ClassLoader classLoader, int fieldWidth, int fieldHeight) {
        gridWorker = new GridWorker(mainVisualField);
        mapGenerator = new MapGenerator(fieldWidth, fieldHeight);
        this.classLoader = classLoader;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        workingMap = null;
    }

    public void fillGridByCell() {
        gridWorker.fillGridByCell(convertArray(workingMap), classLoader);
    }

    public void resetField() {
        gridWorker.cleanGridCells();
    }

    public void setSize(int width, int height) {
        fieldWidth = width;
        fieldHeight = height;
    }

    public void createNewMap() {
        workingMap = mapGenerator.generateMap();
        resetField();
        mapGenerator.reset();
        fillGridByCell();
    }

    public Cell[] getWorkingMapAsArray() {
        return convertArray(workingMap);
    }

    private Cell @NotNull [] convertArray(Cell[] @NotNull [] arr) {
        List<Cell> list = new ArrayList<>();
        for (Cell[] cells : arr) {
            Collections.addAll(list, cells);
        }

        Cell[] finalArray = new Cell[list.size()];
        for (int i = 0; i < finalArray.length; ++i) {
            finalArray[i] = list.get(i);
        }

        return finalArray;
    }

    /**
     * Convert 1D array to the 2D array applicable for find way method
     * @param arr
     * 1D array of Cells
     * @param height
     * 1st par of result array
     * @param width
     * 2nd par of result array
     * @return
     * Applicable array for dejistra algorithm
     */
    private Cell @NotNull [] @NotNull [] convertArray(Cell @NotNull [] arr, int height, int width) {
        Cell[][] result = new Cell[height][width];
        int checkCount = 0;
        for(Cell cell : arr) {
            result[cell.getPosY()][cell.getPosX()] = cell;
            checkCount++;
        }
        if(checkCount != height * width) {
            throw new ArrayIndexOutOfBoundsException("There are not enough elements to convert a " +
                                                    "one-dimensional array to a two-dimensional one");
        }

        return result;
    }
}
