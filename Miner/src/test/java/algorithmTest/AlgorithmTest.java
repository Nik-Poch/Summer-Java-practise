package algorithmTest;

import etu.leti.generator.MapGenerator;
import etu.leti.parser.MapParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import etu.leti.field.Cell;
import etu.leti.ore.Ore;
import etu.leti.ore.OreTypes;
import etu.leti.algorithm.Graph;
import etu.leti.field.Field;
import etu.leti.gui.FieldVisualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlgorithmTest {
    private static final int fieldWidth = 20;
    private static final int fieldHeight = 21;
    private Graph[] graph;
    private Cell[] cellMap;
    private Cell[][] convertedArray;
    private MapParser mapParser;
    private ArrayList<Cell> result;
    private List<String> allPaths;
    //private List<Graph> allGraph;
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
            result = graph[i].getCellsOfShortestWay();
        }
    }

    @Test
    public void algorithmPathLength(){
        double actualLength = 20;
        double expectedLength = graph[0].getLengthMinWay();
        Assert.assertEquals(actualLength, expectedLength, 0.0001);
    }

    @Test
    public void algorithmVeinsCount(){
        int actualOreCellsCount = 6;
        int expectedOreCellsCount;
        expectedOreCellsCount = graph[0].getVeins().size()-2;
        Assert.assertEquals(actualOreCellsCount, expectedOreCellsCount);
    }

    @Test
    public void algorithmHellLocation(){
        int actualHellLocation = 17;
        int expectedHellLocation;
        expectedHellLocation = graph[0].getHellCell().getPosY();
        Assert.assertEquals(actualHellLocation, expectedHellLocation);
    }

}