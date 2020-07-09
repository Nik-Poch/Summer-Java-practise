package etu.leti.gui;

import etu.leti.field.Cell;
import etu.leti.gui.gridhandler.GridWorker;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

public class FieldVisualizer {

    private GridWorker gridWorker;
    private ClassLoader classLoader;
    private int fieldWidth;
    private int fieldHeight;

    public FieldVisualizer(GridPane mainVisualField, ClassLoader classLoader, int fieldWidth, int fieldHeight) {
        gridWorker = new GridWorker(mainVisualField);
        this.classLoader = classLoader;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    public void fillGridByCell(Cell @NotNull [] cells) {
        gridWorker.fillGridByCell(cells, classLoader);
    }

    public void resetField() {
        gridWorker.cleanGridCells();
    }
}
