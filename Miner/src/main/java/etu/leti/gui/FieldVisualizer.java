package etu.leti.gui;

import etu.leti.algorithm.Graph;
import etu.leti.field.Field;
import etu.leti.generator.MapGenerator;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.Contract;
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

    private Cell[][] workingMap;
    private int fieldWidth;
    private int fieldHeight;

    public Cell[][] getWorkingMap() {
        return workingMap;
    }

    public void setWorkingMap(Cell[][] workingMap) {
        this.workingMap = workingMap;
    }

    public void setWorkingMap(Cell[] workingMap) {
        this.workingMap = convertArray(workingMap, fieldWidth, fieldHeight);
    }

    public FieldVisualizer(GridPane mainVisualField, ClassLoader classLoader, int fieldWidth, int fieldHeight) {
        gridWorker = new GridWorker(mainVisualField, classLoader);
        mapGenerator = new MapGenerator(fieldWidth, fieldHeight);
        this.classLoader = classLoader;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        workingMap = null;
    }

    public void fillGridByCell() {
        gridWorker.fillGridByCell(workingMap);
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

    public void showResultWay() {
        Cell[][] convertedArray = convertArrayForAlgorithm(convertCoordsOfArray(workingMap));
        Graph graph = new Graph(new Field(convertedArray, fieldHeight, fieldWidth));
        ArrayList<Cell> result = graph.getCellsOfShortestWay();
        Cell[] resultArr = result.toArray(new Cell[0]);
        gridWorker.visualizeShortestWay(convertCoordsOfArray(resultArr));
    }

    private Cell[][] convertCoordsOfArray(Cell[] @NotNull [] cells) {
        Cell[][] resultCells = cells.clone();
        for(Cell[] cellArr : resultCells) {
            for(Cell cell : cellArr) {
                cell.swapCoords();
            }
        }

        return resultCells;
    }

    @Contract(pure = true)
    private Cell[] @NotNull [] convertArrayForAlgorithm(Cell[] @NotNull [] cells) {
        Cell[][] resultCells = new Cell[cells[0].length][cells.length];
        for(Cell[] cellArr : cells) {
            for(Cell cell : cellArr) {
                resultCells[cell.getPosX()][cell.getPosY()] = cell;
            }
        }

        return resultCells;
    }

    private Cell[] convertCoordsOfArray(Cell @NotNull [] cells) {
        Cell[] resultCells = cells.clone();
        for(Cell cell : resultCells) {
            cell.swapCoords();
        }

        return resultCells;
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
     * Convert 1D array to the 2D array
     * @param arr
     * 1D array of Cells
     * @param height
     * 1st par of result array
     * @param width
     * 2nd par of result array
     * @return
     * 2D Array without empty cells
     */
    private Cell @NotNull [] @NotNull [] convertArray(Cell @NotNull [] arr, int width, int height) {
        Cell[][] result = new Cell[width][height];
        int checkCount = 0;
        for(Cell cell : arr) {
            result[cell.getPosX()][cell.getPosY()] = cell;
            checkCount++;
        }
        if(checkCount != height * width) {
            throw new ArrayIndexOutOfBoundsException("There are not enough elements to convert a " +
                                                    "one-dimensional array to a two-dimensional one");
        }

        return result;
    }
}
