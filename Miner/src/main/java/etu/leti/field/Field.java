package etu.leti.field;

public class Field {
    private Cell[][] cells;
    private int height;
    private int width;

    public Field() {
    }

    public Field(Cell[][] cells, int height, int width) {
        this.cells = cells;
        this.height = height;
        this.width = width;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
