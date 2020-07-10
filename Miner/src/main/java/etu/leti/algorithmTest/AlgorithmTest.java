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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class AlgorithmTest {
    private Graph graph;
    private Cell[] cellMap;
    private Field testMap;
    private MapParser mapParser;


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

    }
}