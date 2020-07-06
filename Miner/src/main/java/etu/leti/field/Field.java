package etu.leti.field;

import etu.leti.ore.OreTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class Field {
    private Cell[][] cells;
    private int height;
    private int width;
    private HashMap<OreTypes, ArrayList<Cell>> veins;

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

    public HashMap<OreTypes, ArrayList<Cell>> getVeins() {
        return veins;
    }

    public void findVeins() {
        ArrayList<Cell> goldCells = new ArrayList<>();
        ArrayList<Cell> ironCells = new ArrayList<>();
        ArrayList<Cell> stoneCells = new ArrayList<>();
        ArrayList<Cell> hellCells = new ArrayList<>();

        for (Cell[] horizontalCells : cells) {
            for (Cell cell : horizontalCells) {
                if (cell.getOre().getOreTypes() == OreTypes.GOLD_ORE) {
                    goldCells.add(cell);
                } else if (cell.getOre().getOreTypes() == OreTypes.IRON_ORE) {
                    ironCells.add(cell);
                } else if (cell.getOre().getOreTypes() == OreTypes.STONE_ORE) {
                    stoneCells.add(cell);
                } else if (cell.getOre().getOreTypes() == OreTypes.HELL) {
                    hellCells.add(cell);
                }
            }
        }
        veins.put(OreTypes.GOLD_ORE, goldCells);
        veins.put(OreTypes.IRON_ORE, ironCells);
        veins.put(OreTypes.STONE_ORE, stoneCells);
        veins.put(OreTypes.HELL, hellCells);
    }
}
