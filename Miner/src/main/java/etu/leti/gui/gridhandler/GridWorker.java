package etu.leti.gui.gridhandler;

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
        int x, y;
        for(Cell cell : cells) {
            x = cell.getPosX();
            y = cell.getPosY();
            switch(cell.getOreInCellType()) {
                case GROUND:
                    mainVisualField.add(new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("ground.jpg")))), x, y);
                    break;
                case GOLD_ORE:
                    mainVisualField.add(new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("gold.jpg")))), x, y);
                    break;
                case IRON_ORE:
                    mainVisualField.add(new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("iron.jpg")))), x, y);
                    break;
                case STONE_ORE:
                    mainVisualField.add(new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("stone.jpg")))), x, y);
                    break;
                case HELL:
                    mainVisualField.add(new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("hell.jpg")))), x, y);
                    break;
            }
        }
    }
}
