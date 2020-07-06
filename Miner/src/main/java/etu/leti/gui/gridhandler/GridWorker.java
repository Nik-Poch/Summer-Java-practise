package etu.leti.gui.gridhandler;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    static @Nullable Node getNodeFromGridPane(@NotNull GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

}
