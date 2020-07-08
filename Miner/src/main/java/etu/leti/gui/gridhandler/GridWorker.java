package etu.leti.gui.gridhandler;

import com.google.gson.JsonParseException;
import etu.leti.field.Cell;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class GridWorker {

    /**
     * Get a specific node from gridpane by is column and row index
     * @param gridPane
     * Grid for searching
     * @param col
     * Column index
     * @param row
     * Row index
     * @return
     * Node with specified coordinates
     */
    public static @Nullable Node getNodeFromGridPane(@NotNull GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public static void fillGridByCell(GridPane mainVisualField, int width, int height, Cell @NotNull [] cells, ClassLoader classLoader) {

        int x, y, i = 0;

        ImageCell[] fillGridImages = new ImageCell[cells.length];

        for(Cell cell : cells) {

            if(cell == null) {
                break;
            }

            x = cell.getPosX();
            y = cell.getPosY();
            switch(cell.getOreInCellType()) {
                case GROUND:
                    fillGridImages[i] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("ground.jpg"))));
                    break;
                case GOLD_ORE:
                    fillGridImages[i] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("gold.jpg"))));
                    break;
                case IRON_ORE:
                    fillGridImages[i] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("iron.jpg"))));
                    break;
                case STONE_ORE:
                    fillGridImages[i] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("stone.jpg"))));
                    break;
                case HELL:
                    fillGridImages[i] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("hell.jpg"))));
                    break;
                default:
                    fillGridImages[i] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("air.png"))));
            }
            mainVisualField.add(fillGridImages[i], x, y);

            ++i;
        }

        if(i == cells.length) {
            mainVisualField.add(new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("player.png")))), 0, 0);
            return;
        }

        for(int j = 0; j < i; j++) {
            mainVisualField.getChildren().remove(fillGridImages[j]);
        }
        throw new JsonParseException("Incorrect data for " + i + " element in Cell array from file");
    }
}
