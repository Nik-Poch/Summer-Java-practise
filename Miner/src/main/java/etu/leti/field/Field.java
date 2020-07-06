package etu.leti.field;

import etu.leti.ore.OreTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class Field {
    private Cell[][] cells;
    private int height;
    private int width;
    private Cell hell;
    private HashMap<Integer, ArrayList<Cell>> veins;

    public Field() {
        veins = new HashMap<>();
    }

    public Field(Cell[][] cells, int height, int width) {
        this.cells = cells;
        this.height = height;
        this.width = width;
        veins = new HashMap<>();
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

    /**
     * Method, which find all veins and add its to HashMap<Integer, ArrayList<Cell>> veins
     */

    public void findVeins() {
        Integer i = 1;
        for (Cell[] horizontalCells : cells) {
            for (Cell cell : horizontalCells) {
                if (cell.isChecked()) {
                    continue;
                }
                if (cell.getOre().getOreTypes() == OreTypes.GOLD_ORE ||
                        cell.getOre().getOreTypes() == OreTypes.IRON_ORE ||
                        cell.getOre().getOreTypes() == OreTypes.STONE_ORE) {

                    ArrayList<Cell> vein = new ArrayList<>();
                    findWayFromCell(cell.getPosX(), cell.getPosY(), cell.getOre().getOreTypes(), vein);
                    veins.put(i, vein);
                    i++;

                } else if (cell.getOre().getOreTypes() == OreTypes.HELL) {
                    this.hell = cell;
                }
            }
        }
    }

    /**
     * Method, which find all cells belong to one vein
     * @param x - pos X of cell
     * @param y - pos Y of cell
     * @param type - type Ore in the cell
     * @param vein - ArrayList, where storage all cells belong to one vein
     */
    private void findWayFromCell(int x, int y, OreTypes type, ArrayList<Cell> vein) {
        Cell cell = cells[x][y];
        if (!cell.isChecked()) {
            cell.setChecked(true);
            vein.add(cell);
        }
        if (cell.getPosX() + 1 < height) {
            Cell downCell = cells[cell.getPosX()+1][cell.getPosY()];
            if (!downCell.isChecked() && downCell.getOre().getOreTypes() == type) {
                findWayFromCell(cell.getPosX() + 1, cell.getPosY() , type, vein);
            }
        }
        if (cell.getPosY() + 1 < width) {
            Cell rightCell = cells[cell.getPosX()][cell.getPosY() + 1];
            if (!rightCell.isChecked() && rightCell.getOre().getOreTypes() == type) {
                findWayFromCell(cell.getPosX(), cell.getPosY() + 1, type, vein);
            }
        }
        if (cell.getPosY() - 1 >= 0) {
            Cell leftCell = cells[cell.getPosX()][cell.getPosY() - 1];
            if (!leftCell.isChecked() && leftCell.getOre().getOreTypes() == type) {
                findWayFromCell(cell.getPosX(), cell.getPosY() - 1, type, vein);
            }
        }
        if (cell.getPosX() - 1 >= 0) {
            Cell upCell = cells[cell.getPosX() - 1][cell.getPosY()];
            if (!upCell.isChecked() && upCell.getOre().getOreTypes() == type) {
                findWayFromCell(cell.getPosX() - 1, cell.getPosY(), type, vein);
            }
        }
    }

}
