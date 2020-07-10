package etu.leti.generatorTest;

import etu.leti.field.Cell;
import etu.leti.ore.Ore;
import etu.leti.ore.OreTypes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import etu.leti.generator.MapGenerator;

public class GeneratorTest {
    private int x,y;
    private MapGenerator mapGenerator;
    private Cell[][] testField;

    @Before
    public void setUp(){
        x = 20;
        y = 21;
        mapGenerator = new MapGenerator(x, y);
    }

    @Test
    public void MapGeneratorHellSetup() {
        boolean hellDetected = false;
        testField = mapGenerator.generateMap();
        for (int i = 0; i<x; i++) {
            if (testField[i][y-1].getOre().getOreType()== OreTypes.HELL) {
                hellDetected = true;
            }
        }
        Assert.assertTrue("Hell's cell is not on the ground floor.",hellDetected);
    }

    @Test
    public void MapGeneratorAllFilled() {
        testField = mapGenerator.generateMap();
        for (int i = 0; i<x; i++) {
            for (int j = 0; j<y;j++)
                Assert.assertNotNull("One of the map cells was not generated.",testField[i][j]);
        }
    }

    @Test
    public void MapGeneratorVeinsCreated() {
        boolean oreDetected = false;
        testField = mapGenerator.generateMap();
        for (int i = 0; i<x; i++) {
            for (int j = 0; j<y;j++)
                if (!(testField[i][j].getOre().getOreType()== OreTypes.HELL || testField[i][j].getOre().getOreType()== OreTypes.GROUND)){
                    oreDetected = true;
                }

        }
        Assert.assertTrue("Not a single ore cell has been created.",oreDetected);
    }

    @Test
    public void MapGeneratorNotNull() {
        testField = mapGenerator.generateMap();
        Assert.assertNotNull("The generator didn't generate the map.",testField);
    }
}
