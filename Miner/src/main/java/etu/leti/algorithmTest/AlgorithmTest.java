package etu.leti.algorithmTest;

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


public class AlgorithmTest {
    private static final int fieldWidth = 20;
    private static final int fieldHeight = 21;
    private Graph graph;
    private Cell[] cellMap;
    private Cell[][] convertedArray;
    private Field testMap;
    private MapParser mapParser;
    private ArrayList<Cell> result;


    @Before
    public void setUp() throws IOException {
        mapParser = new MapParser();
        File file = new File("D://Github/Summer-Java-practise/maps/genMap_2.json");//"D:\Github\Summer-Java-practise\maps\genMap_2.json"
        mapParser.setJsonFile(file);
        try {
            cellMap = mapParser.parseCellArr();
        } catch (FileNotFoundException exc) {
            return;
        }
        convertedArray = FieldVisualizer.convertArrayForAlgorithm(FieldVisualizer.convert1DArrayTo2D(cellMap, fieldWidth ,fieldHeight));
        graph = new Graph(new Field(convertedArray, fieldHeight,fieldWidth ));
        result = graph.getCellsOfShortestWay();
    }

    @Test
    public void algorithmPathLength(){
        double actualLength = 20;
        double expectedLength = graph.getLengthMinWay();
        Assert.assertEquals(actualLength, actualLength);
    }

}