package etu.leti.algorithm;

import etu.leti.field.Cell;

public class Way {
    private Cell from;
    private Cell to;
    private Integer numberVeinFrom;
    private Integer numberVeinTo;

    public Way() {
    }

    public Way(Cell from, Cell to, Integer numberVeinFrom, Integer numberVeinTo) {
        this.from = from;
        this.to = to;
        this.numberVeinFrom = numberVeinFrom;
        this.numberVeinTo = numberVeinTo;
    }

    public Cell getFrom() {
        return from;
    }

    public void setFrom(Cell from) {
        this.from = from;
    }

    public Cell getTo() {
        return to;
    }

    public void setTo(Cell to) {
        this.to = to;
    }

    public Integer getNumberVeinFrom() {
        return numberVeinFrom;
    }

    public void setNumberVeinFrom(Integer numberVeinFrom) {
        this.numberVeinFrom = numberVeinFrom;
    }

    public Integer getNumberVeinTo() {
        return numberVeinTo;
    }

    public void setNumberVeinTo(Integer numberVeinTo) {
        this.numberVeinTo = numberVeinTo;
    }
}
