package algorithmTest;


import etu.leti.parser.MapParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import etu.leti.field.Cell;
import etu.leti.algorithm.Graph;
import etu.leti.field.Field;
import etu.leti.gui.FieldVisualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmTest {
    private static final int fieldWidth = 20;
    private static final int fieldHeight = 21;
    private static final Graph[] graph = new Graph[3];
    private Cell[] cellMap;
    private Cell[][] convertedArray;
    private MapParser mapParser;
    private List<String> allPaths;
    String path1 = "src/test/resources/genMap_2.json";
    String path2 = "src/test/resources/genMap_1.json";
    String path3 = "src/test/resources/super_test.json";

    @Before
    public void setUp() throws IOException {
        allPaths = new ArrayList<>();
        allPaths.add(path1);
        allPaths.add(path2);
        allPaths.add(path3);
        mapParser = new MapParser();
        for(int i=0; i<3;i++) {
            File file1 = new File(allPaths.get(i));
            mapParser.setJsonFile(file1);
            try {
                cellMap = mapParser.parseCellArr();
            } catch (FileNotFoundException exc) {
                return;
            }
            convertedArray = FieldVisualizer.convertArrayForAlgorithm(FieldVisualizer.convertCoordsOf2DArray(FieldVisualizer.convert1DArrayTo2D(cellMap, fieldWidth, fieldHeight)));
            graph[i] = new Graph(new Field(convertedArray, fieldHeight, fieldWidth));
            graph[i].getCellsOfShortestWay();
        }
    }

    @Test
    public void algorithmPathLength(){
        double [] actualLength = {20,20,17};
        for (int i = 0;i < 3;i++) {
            double expectedLength = graph[i].getLengthMinWay();
            Assert.assertEquals(actualLength[i], expectedLength, 0.0001);
        }
    }

    @Test
    public void algorithmVeinsCount(){
        int [] actualOreCellsCount = {6,7,8};
        int expectedOreCellsCount;
        for (int i = 0;i < 3;i++) {
        expectedOreCellsCount = graph[i].getVeins().size()-2;
        Assert.assertEquals(actualOreCellsCount[i], expectedOreCellsCount);
        }
    }

    @Test
    public void algorithmHellLocation(){
        int [] actualHellLocation = {17,19,1};
        int expectedHellLocation;
        for (int i = 0;i < 3;i++) {
            expectedHellLocation = graph[i].getHellCell().getPosY();
            Assert.assertEquals(actualHellLocation[i], expectedHellLocation);
        }
    }

}