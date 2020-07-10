package etu.leti.algorithm;

import etu.leti.field.Cell;
import etu.leti.field.Field;
import etu.leti.ore.OreTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Graph {
    private Field field;
    private Integer hell;
    private Cell hellCell;
    private HashMap<Integer, ArrayList<Cell>> veins;
    private Integer start;
    private Cell startCell;
    private HashMap<Integer, ArrayList<Node>> graph;
    private double lengthMinWay;
    private ArrayList<Node> nodesOfShortestWay;
    private ArrayList<Cell> cellsOfShortestWay;

    public Graph(Field field) {
        this.field = field;
        veins = new HashMap<>();
        graph = new HashMap<>();
        nodesOfShortestWay = new ArrayList<>();
        cellsOfShortestWay = new ArrayList<>();
    }

    /**
     * Method, which find all cells belong to one vein
     *
     * @param x    - pos X of cell
     * @param y    - pos Y of cell
     * @param type - type Ore in the cell
     * @param vein - ArrayList, where storage all cells belong to one vein
     */
    private void findVein(int x, int y, OreTypes type, ArrayList<Cell> vein) {
        Cell cell = field.getCells()[x][y];
        if (!cell.isChecked()) {
            cell.setChecked(true);
            vein.add(cell);
        }
        if (cell.getPosX() + 1 < field.getHeight()) {
            Cell downCell = field.getCells()[cell.getPosX() + 1][cell.getPosY()];
            if (!downCell.isChecked() && downCell.getOre().getOreType() == type) {
                findVein(cell.getPosX() + 1, cell.getPosY(), type, vein);
            }
        }
        if (cell.getPosY() + 1 < field.getWidth()) {
            Cell rightCell = field.getCells()[cell.getPosX()][cell.getPosY() + 1];
            if (!rightCell.isChecked() && rightCell.getOre().getOreType() == type) {
                findVein(cell.getPosX(), cell.getPosY() + 1, type, vein);
            }
        }
        if (cell.getPosY() - 1 >= 0) {
            Cell leftCell = field.getCells()[cell.getPosX()][cell.getPosY() - 1];
            if (!leftCell.isChecked() && leftCell.getOre().getOreType() == type) {
                findVein(cell.getPosX(), cell.getPosY() - 1, type, vein);
            }
        }
        if (cell.getPosX() - 1 >= 0) {
            Cell upCell = field.getCells()[cell.getPosX() - 1][cell.getPosY()];
            if (!upCell.isChecked() && upCell.getOre().getOreType() == type) {
                findVein(cell.getPosX() - 1, cell.getPosY(), type, vein);
            }
        }
    }

    /**
     * Method, which find all veins and add its to HashMap<Integer, ArrayList<Cell>> veins
     */

    private void findVeins() {
        Integer i = 0;
        for (Cell[] horizontalCells : field.getCells()) {
            for (Cell cell : horizontalCells) {
                if(cell.getPosX() == 0 && cell.getPosY() == 0 && cell.getOre().getOreType() == OreTypes.AIR){
                    ArrayList<Cell> vein = new ArrayList<>();
                    this.start = i;
                    this.startCell = cell;
                    vein.add(cell);
                    veins.put(i, vein);
                    i++;
                    continue;
                }
                if (cell.isChecked()) {
                    continue;
                }
                if (cell.getOre().getOreType() == OreTypes.GOLD_ORE ||
                        cell.getOre().getOreType() == OreTypes.IRON_ORE ||
                        cell.getOre().getOreType() == OreTypes.STONE_ORE ||
                        cell.getOre().getOreType() == OreTypes.HELL) {

                    ArrayList<Cell> vein = new ArrayList<>();
                    if (cell.getOre().getOreType() == OreTypes.HELL) {
                        this.hell = i;
                        this.hellCell = cell;
                        vein.add(cell);
                        veins.put(i, vein);
                        i++;
                        continue;
                    }
                    findVein(cell.getPosX(), cell.getPosY(), cell.getOre().getOreType(), vein);
                    veins.put(i, vein);
                    i++;

                }
            }
        }
    }

    private void setNodesToGraph(Integer numberVein1, Integer numberVein2, Cell fromVein1, Cell toVein2, double minWay){
        if(graph.get(numberVein1) == null){
            ArrayList<Node> nodes = new ArrayList<>();
            nodes.add(new Node(numberVein2, fromVein1, toVein2, minWay));
            graph.put(numberVein1, nodes);
        }else{
            graph.get(numberVein1).add(new Node(numberVein2, fromVein1, toVein2, minWay));
        }
    }

    /**
     * Method, which find two coming cells between vein1 and vein two
     *
     * @param vein1       - ArrayList<Cell> of vein, from which the path is sought
     * @param vein2       - ArrayList<Cell> of vein, to which the path is sought
     * @param numberVein1 - Integer number of vein, from which the path is sought
     * @param numberVein2 - Integer number of vein, to which the path is sought
     */
    private void findMinWayBetweenTwoVeins(ArrayList<Cell> vein1, ArrayList<Cell> vein2, Integer numberVein1, Integer numberVein2) {
        double minWay = Double.MAX_VALUE;
        Cell fromVein1 = new Cell();
        Cell toVein2 = new Cell();
        for (Cell cell1 : vein1) {
            for (Cell cell2 : vein2) {
                double dist = Math.abs(cell1.getPosX() - cell2.getPosX()) + Math.abs(cell1.getPosY() - cell2.getPosY()) - 1;
                if (minWay > dist) {
                    fromVein1 = cell1;
                    toVein2 = cell2;
                    minWay = dist;
                }
            }
        }
        setNodesToGraph(numberVein1, numberVein2, fromVein1, toVein2, minWay);
        setNodesToGraph(numberVein2, numberVein1, toVein2, fromVein1, minWay);
    }

    /**
     * Method, which find min way between all veins. Way - two coming cells
     */
    private void findMinWayBetweenVeins() {
        findVeins();
        int k = 1;
        for (HashMap.Entry<Integer, ArrayList<Cell>> entry : veins.entrySet()) {
            for (int i = k; i < veins.size(); i++) {
                findMinWayBetweenTwoVeins(entry.getValue(), veins.get(i), entry.getKey(), i);
            }
            k++;
        }
    }

    private void dijkstra(){
        findMinWayBetweenVeins();
        PriorityQueue<Node> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(new Node(start, startCell));
        while (!vertexQueue.isEmpty()){
            Node vertex = vertexQueue.poll();
            for(Node node : graph.get(vertex.getVertex())){
                double weight = node.getWeight();
                double distance = vertex.getMinDistance() + weight;
                if(distance < node.getMinDistance()){
                    for(Node buf : graph.get(node.getVertex())){
                        if(buf.getVertex().equals(vertex.getVertex())){
                            buf.setMinDistance(distance);
                            buf.setPrev(vertex);
                            break;
                        }
                    }
                    node.setMinDistance(distance);
                    node.setPrev(vertex);
                    vertexQueue.add(node);
                }
            }
        }
    }

    private void setCellsOfGround(Cell from, Cell to){
        int x;
        int y;
        int dist;
        int startPos;
        if(from.getPosY() == to.getPosY()){
            y = to.getPosY();
            dist = Math.abs(from.getPosX() - to.getPosX());
            startPos = Math.min(from.getPosX(), to.getPosX());
            for(int i = 1; i < dist; i++){
                cellsOfShortestWay.add(field.getCells()[++startPos][y]);
            }
        }else if(from.getPosX() == to.getPosX()){
            x = to.getPosX();
            dist = Math.abs(from.getPosY() - to.getPosY());
            startPos = Math.min(from.getPosY(), to.getPosY());
            for(int i = 1; i < dist; i++){
                cellsOfShortestWay.add(field.getCells()[x][++startPos]);
            }
        }else {
            int distY = Math.abs(from.getPosY() - to.getPosY()) - 1;
            int distX = Math.abs(from.getPosX() - to.getPosX());
            int startPosY;
            int startPosX;
            if(from.getPosX() < to.getPosX()){
                startPosY = from.getPosY();
                startPosX = from.getPosX();
            }else{
                startPosY = to.getPosY();
                startPosX = to.getPosX();
            }
            for(int i = 0; i < distX; i++){
                cellsOfShortestWay.add(field.getCells()[++startPosX][startPosY]);
            }
            startPosY = Math.min(from.getPosY(), to.getPosY());
            for (int i = 0; i < distY; i++){
                cellsOfShortestWay.add(field.getCells()[startPosX][++startPosY]);
            }
        }
    }

    private void shortestWay(){
        dijkstra();

        double minDist = Double.MAX_VALUE;

        Node minNode = new Node();
        for(Node node : graph.get(hell)){
            if(minDist > node.getMinDistance()){
                minDist = node.getMinDistance();
                minNode = node;
            }
        }

        lengthMinWay = minDist;

        nodesOfShortestWay.add(new Node(hell, minNode.getTo(), hellCell));

        while (minNode.getPrev() != null){
            nodesOfShortestWay.add(minNode.getPrev());
            minNode = minNode.getPrev();
        }

        Collections.reverse(nodesOfShortestWay);

        for(Node node : nodesOfShortestWay){
            if(!node.getVertex().equals(start)){
                cellsOfShortestWay.add(node.getFrom());
                setCellsOfGround(node.getFrom(), node.getTo());
                cellsOfShortestWay.add(node.getTo());
            }

        }
    }

    public HashMap<Integer, ArrayList<Cell>> getVeins() {
        return veins;
    }

    public HashMap<Integer, ArrayList<Node>> getGraph() {
        return graph;
    }

    public double getLengthMinWay() {
        return lengthMinWay;
    }

    public ArrayList<Cell> getCellsOfShortestWay() {
        shortestWay();
        return cellsOfShortestWay;
    }
}
