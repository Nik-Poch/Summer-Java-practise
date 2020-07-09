package etu.leti.parser;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import etu.leti.field.*;
import etu.leti.gui.gridhandler.ImageCell;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

public class MapParser {

    private File jsonFile;
    private final Gson gson;

    public MapParser() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public File getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public Cell[] parseCellArr() throws IOException {

        if(jsonFile == null) {
            throw new FileNotFoundException("No json file was choose");
        }

        BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
        StringBuilder userJson = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            userJson.append(line);
        }
        reader.close();

        Cell[] cells = gson.fromJson(userJson.toString(), Cell[].class);

        if(cells == null) {
            throw new JsonParseException("Empty JSON map file");
        }

        initFileNames(cells);

        return cells;
    }

    public void writeCellArray(Cell[] cellArray) throws IOException {

        if(jsonFile == null) {
            throw new FileNotFoundException("No json file was choose");
        }

        if(jsonFile.exists()) {
            jsonFile.delete();
        }
        jsonFile.createNewFile();

        String jsonStr = gson.toJson(cellArray);

        BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
        writer.write(jsonStr);
        writer.close();
    }

    public static void initFileNames(Cell @NotNull [] cells) {
        int i = 0;
        for(Cell cell : cells) {
            if(cell == null) {
                throw new JsonParseException("Incorrect data for " + i + " element in Cell array from file");
            }
            switch (cell.getOreInCellType()) {
                case GROUND:
                    cell.setOreImg("ground.jpg");
                    break;
                case GOLD_ORE:
                    cell.setOreImg("gold.jpg");
                    break;
                case IRON_ORE:
                    cell.setOreImg("iron.jpg");
                    break;
                case STONE_ORE:
                    cell.setOreImg("stone.jpg");
                    break;
                case HELL:
                    cell.setOreImg("hell.jpeg");
                    break;
                default:
                    cell.setOreImg("air.png");
            }
            i++;
        }
    }
}
