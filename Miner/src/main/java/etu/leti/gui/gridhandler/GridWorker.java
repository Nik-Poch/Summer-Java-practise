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
    private ImageCell lastImage;

    public GridWorker(GridPane mainVisualField, @NotNull ClassLoader classLoader) {
        this.mainVisualField = mainVisualField;
        this.classLoader = classLoader;
        lastImage = null;
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

        playerImg = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/player.png"))));
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
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/ground.jpg"))));
                        break;
                    case GOLD_ORE:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/gold.jpg"))));
                        break;
                    case IRON_ORE:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/iron.jpg"))));
                        break;
                    case STONE_ORE:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/stone.jpg"))));
                        break;
                    case HELL:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/hell.jpeg"))));
                        break;
                    case AIR:
                        fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/air.png"))));
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

    public void deleteCharacter() {
        mainVisualField.getChildren().remove(playerImg);
    }

    public void showOneStep(Cell @NotNull [] shortestWay, int shortestWayCurrPos) {
        if(shortestWayCurrPos == shortestWay.length - 1) {
            return;
        }

        if(playerImg != null) {
            mainVisualField.getChildren().remove(playerImg);
            playerImg = null;
        }

        int x = shortestWay[shortestWayCurrPos].getPosX();
        int y = shortestWay[shortestWayCurrPos].getPosY();
        int extraX, extraY;

        if(lastImage != null) {
            extraX = shortestWay[shortestWayCurrPos + 1].getPosX();
            extraY = shortestWay[shortestWayCurrPos + 1].getPosY();
            fillGridImages[extraX][extraY] = lastImage;
            mainVisualField.add(fillGridImages[extraX][extraY], extraX, extraY);
        }

        lastImage = fillGridImages[x][y];
        mainVisualField.getChildren().remove(fillGridImages[x][y]);

        switch (shortestWay[shortestWayCurrPos].getOreInCellType()) {
            case GROUND:
                fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/move/ground.png"))));
                break;
            case GOLD_ORE:
                fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/move/gold.png"))));
                break;
            case IRON_ORE:
                fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/move/iron.png"))));
                break;
            case STONE_ORE:
                fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/move/stone.png"))));
                break;
            case HELL:
                fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/move/hell.png"))));
                break;
            case AIR:
                fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/move/air.png"))));
        }
        mainVisualField.add(fillGridImages[x][y], x, y);
    }

    public void visualizeShortestWay(Cell @NotNull [] shortWayCells) {
        int x, y;

        for(Cell cell : shortWayCells) {
            x = cell.getPosX();
            y = cell.getPosY();
            if (x == 0 & y == 0) {
                continue;
            }
            mainVisualField.getChildren().remove(fillGridImages[x][y]);

            switch (cell.getOreInCellType()) {
                case GROUND:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/choose/ground.png"))));
                    break;
                case GOLD_ORE:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/choose/gold.png"))));
                    break;
                case IRON_ORE:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/choose/iron.png"))));
                    break;
                case STONE_ORE:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/choose/stone.png"))));
                    break;
                case AIR:
                    fillGridImages[x][y] = new ImageCell(new Image(Objects.requireNonNull(classLoader.getResourceAsStream("img/choose/air.png"))));
            }
            mainVisualField.add(fillGridImages[x][y], x, y);
        }
    }

    public void cleanGridCells() {
//        if(fillGridImages == null) {
//            return;
//        } else {
//            for(ImageCell[] cellArr : fillGridImages) {
//                for (ImageCell cell : cellArr) {
//                    if(cell != null) {
//                        mainVisualField.getChildren().remove(cell);
//                    }
//                }
//            }
//        }
        mainVisualField.getChildren().retainAll(mainVisualField.getChildren().get(0));
        if(playerImg != null) {
            mainVisualField.getChildren().remove(playerImg);
        }
        if(lastImage != null) {
            mainVisualField.getChildren().remove(lastImage);
            lastImage = null;
        }
    }
}
