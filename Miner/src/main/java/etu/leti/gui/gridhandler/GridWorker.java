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

    private final GridPane mainVisualField;
    private ImageCell[][] fillGridImages;
    private ImageCell playerImg;
    private final ClassLoader classLoader;

    public GridWorker(GridPane mainVisualField, ClassLoader classLoader) {
        this.mainVisualField = mainVisualField;
        this.classLoader = classLoader;
    }

    /**
     * Get a specific node from GridPane by is column and row index
     * @param col
     * Column index
     * @param row
     * Row index
     * @return
     * Node with specified coordinates
     */
    public @Nullable Node getNodeFromGridPane(int col, int row) {
        for (Node node : mainVisualField.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void fillGridByCell(Cell[] @NotNull [] cells) {

        playerImg = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("player.png"))));
        int x, y, i = 0;
        fillGridImages = new ImageCell[cells.length][cells[0].length];

        for(Cell[] cellArr : cells) {
            for (Cell cell : cellArr) {
                if (cell == null) {
                    break;
                }

                x = cell.getPosX();
                y = cell.getPosY();
                switch (cell.getOreInCellType()) {
                    case GROUND:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("ground.jpg"))));
                        break;
                    case GOLD_ORE:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("gold.jpg"))));
                        break;
                    case IRON_ORE:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("iron.jpg"))));
                        break;
                    case STONE_ORE:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("stone.jpg"))));
                        break;
                    case HELL:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("hell.jpeg"))));
                        break;
                    default:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("air.png"))));
                }
                mainVisualField.add(fillGridImages[x][y], x, y);

                ++i;
            }
        }

        if(i == cells.length * cells[0].length) {
            mainVisualField.add(playerImg, 0, 0);
            return;
        }

        cleanGridCells();
        throw new JsonParseException("Incorrect data for " + i + " element in Cell array from file");
    }

    public void visualizeShortestWay(Cell @NotNull [] shortWayCells) {
        int x, y;

        for(Cell cell : shortWayCells) {
            x = cell.getPosX();
            y = cell.getPosY();
            mainVisualField.getChildren().remove(fillGridImages[x][y]);

            switch (cell.getOreInCellType()) {
                case GROUND:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("choose/ground.png"))));
                    break;
                case GOLD_ORE:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("choose/gold.png"))));
                    break;
                case IRON_ORE:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("choose/iron.png"))));
                    break;
                case STONE_ORE:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("choose/stone.png"))));
                    break;
                case AIR:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("choose/air.png"))));
            }
            mainVisualField.add(fillGridImages[x][y], x, y);
        }
    }

    public void cleanGridCells() {
        if(fillGridImages == null) {
            return;
        }
        for(ImageCell[] cellArr : fillGridImages) {
            for (ImageCell cell : cellArr) {
                mainVisualField.getChildren().remove(cell);
            }
        }
        if(playerImg != null) {
            mainVisualField.getChildren().remove(playerImg);
        }
    }
}
