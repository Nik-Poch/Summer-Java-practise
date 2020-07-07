package etu.leti.algorithm;

import etu.leti.field.Cell;
import etu.leti.field.Field;
import etu.leti.ore.OreTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private Field field;
    private Cell hell;
    private HashMap<Integer, ArrayList<Cell>> veins;
    private ArrayList<Way> ways;

    public Graph(Field field) {
        this.field = field;
        ways = new ArrayList<>();
        veins = new HashMap<>();
    }

    /**
     * Method, which find all veins and add its to HashMap<Integer, ArrayList<Cell>> veins
     */

    public void findVeins() {
        Integer i = 1;
        for (Cell[] horizontalCells : field.getCells()) {
            for (Cell cell : horizontalCells) {
                if (cell.isChecked()) {
                    continue;
                }
                if (cell.getOre().getOreType() == OreTypes.GOLD_ORE ||
                        cell.getOre().getOreType() == OreTypes.IRON_ORE ||
                        cell.getOre().getOreType() == OreTypes.STONE_ORE) {

                    ArrayList<Cell> vein = new ArrayList<>();
                    findWayFromCell(cell.getPosX(), cell.getPosY(), cell.getOre().getOreType(), vein);
                    veins.put(i, vein);
                    i++;

                } else if (cell.getOre().getOreType() == OreTypes.HELL) {
                    this.hell = cell;
                }
            }
        }
    }

    /**
     * Method, which find all cells belong to one vein
     *
     * @param x    - pos X of cell
     * @param y    - pos Y of cell
     * @param type - type Ore in the cell
     * @param vein - ArrayList, where storage all cells belong to one vein
     */
    private void findWayFromCell(int x, int y, OreTypes type, ArrayList<Cell> vein) {
        Cell cell = field.getCells()[x][y];
        if (!cell.isChecked()) {
            cell.setChecked(true);
            vein.add(cell);
        }
        if (cell.getPosX() + 1 < field.getHeight()) {
            Cell downCell = field.getCells()[cell.getPosX() + 1][cell.getPosY()];
            if (!downCell.isChecked() && downCell.getOre().getOreType() == type) {
                findWayFromCell(cell.getPosX() + 1, cell.getPosY(), type, vein);
            }
        }
        if (cell.getPosY() + 1 < field.getWidth()) {
            Cell rightCell = field.getCells()[cell.getPosX()][cell.getPosY() + 1];
            if (!rightCell.isChecked() && rightCell.getOre().getOreType() == type) {
                findWayFromCell(cell.getPosX(), cell.getPosY() + 1, type, vein);
            }
        }
        if (cell.getPosY() - 1 >= 0) {
            Cell leftCell = field.getCells()[cell.getPosX()][cell.getPosY() - 1];
            if (!leftCell.isChecked() && leftCell.getOre().getOreType() == type) {
                findWayFromCell(cell.getPosX(), cell.getPosY() - 1, type, vein);
            }
        }
        if (cell.getPosX() - 1 >= 0) {
            Cell upCell = field.getCells()[cell.getPosX() - 1][cell.getPosY()];
            if (!upCell.isChecked() && upCell.getOre().getOreType() == type) {
                findWayFromCell(cell.getPosX() - 1, cell.getPosY(), type, vein);
            }
        }
    }

    /**
     * Method, which find two coming cells between vein1 and vein two
     * @param vein1 - ArrayList<Cell> of vein, from which the path is sought
     * @param vein2 - ArrayList<Cell> of vein, to which the path is sought
     * @param numberVein1 - Integer number of vein, from which the path is sought
     * @param numberVein2 - Integer number of vein, to which the path is sought
     */
    private void findMinWayBetweenTwoVeins(ArrayList<Cell> vein1, ArrayList<Cell> vein2, Integer numberVein1, Integer numberVein2) {
        double minWay = Double.MAX_VALUE;
        Cell fromVein1 = new Cell();
        Cell toVein2 = new Cell();
        for (Cell cell1 : vein1) {
            for (Cell cell2 : vein2) {
                double dist = Math.pow((cell1.getPosX() - cell2.getPosX()), 2) +
                        Math.pow((cell1.getPosY() - cell2.getPosY()), 2);
                if (minWay > dist) {
                    fromVein1 = cell1;
                    toVein2 = cell2;
                    minWay = dist;
                }
            }
        }
        Way way = new Way(fromVein1, toVein2, numberVein1, numberVein2);
        ways.add(way);
    }

    /**
     * Method, which find min way between all veins. Way - two coming cells
     */
    public void findMinWayBetweenVeins() {
        Integer k = 2;
        ways = new ArrayList<>();
        for (HashMap.Entry<Integer, ArrayList<Cell>> entry : veins.entrySet()) {
            for (Integer i = k; i <= veins.size(); i++) {
                findMinWayBetweenTwoVeins(entry.getValue(), veins.get(i), entry.getKey(), i);
            }
            k++;
        }
    }

}
