package field;

public class Field {
    private Cell[][] cells;
    private int height;
    private int widht;

    public Field() {
    }

    public Field(Cell[][] cells, int height, int widht) {
        this.cells = cells;
        this.height = height;
        this.widht = widht;
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

    public int getWidht() {
        return widht;
    }

    public void setWidht(int widht) {
        this.widht = widht;
    }
}
