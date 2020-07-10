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
import java.util.Arrays;
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
        this.workingMap = convert1DArrayTo2D(workingMap, fieldWidth, fieldHeight);
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
        return convert2DArrayTo1D(workingMap);
    }

    public void showResultWay() {
        Cell[][] convertedArray = convertArrayForAlgorithm(convertCoordsOf2DArray(workingMap));
        Graph graph = new Graph(new Field(convertedArray, fieldHeight, fieldWidth));
        ArrayList<Cell> result = graph.getCellsOfShortestWay();
        Cell[] resultArr = result.toArray(new Cell[0]);
        gridWorker.visualizeShortestWay(convertCoordsOf1DArray(resultArr));
    }

    /**
     * @param cells
     * TWO dimensions array of Cell
     * @return
     * NEW TWO dimensions array of Cell with REVERSED X and Y coords
     */
    public static Cell[] @NotNull [] convertCoordsOf2DArray(Cell[] @NotNull [] cells) {
        Cell[][] resultCells = new Cell[cells.length][];
        for(int i = 0; i < cells.length; ++i) {
            resultCells[i] = Arrays.stream(cells[i]).
                    map(cell -> cell == null ? null : new Cell(cell)).toArray(Cell[]::new);
        }

        for(Cell[] cellArr : resultCells) {
            for(Cell cell : cellArr) {
                cell.swapCoords();
            }
        }

        return resultCells;
    }

    /**
     * @param cells
     * TWO dimensions array of Cell
     * @return
     * NEW TWO dimensions array of Cell with REVERSED X and Y coords AND rotated map
     */
    public static Cell[] @NotNull [] convertArrayForAlgorithm(Cell[] @NotNull [] cells) {
        Cell[][] resultCells = new Cell[cells[0].length][cells.length];
        for(Cell[] cellArr : cells) {
            for(Cell cell : cellArr) {
                resultCells[cell.getPosX()][cell.getPosY()] = cell;
            }
        }

        return resultCells;
    }

    /**
     * @param cells
     * ONE dimensions array of Cell
     * @return
     * NEW ONE dimensions array of Cell with REVERSED X and Y coords
     */
    public static Cell[] convertCoordsOf1DArray(Cell @NotNull [] cells) {
        Cell[] resultCells = Arrays.stream(cells).
                            map(cell -> cell == null ? null : new Cell(cell)).toArray(Cell[]::new);
        for(Cell cell : resultCells) {
            cell.swapCoords();
        }

        return resultCells;
    }

    /**
     * @param arr
     * TWO dimensions array of Cell
     * @return
     * ONE dimension array of Cell
     */
    public static Cell @NotNull [] convert2DArrayTo1D(Cell[] @NotNull [] arr) {
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
     * @param arr
     * ONE dimension array of Cell
     * @return
     * TWO dimensions array of Cell
     */
    public static Cell @NotNull [] @NotNull [] convert1DArrayTo2D(Cell @NotNull [] arr, int width, int height) {
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
