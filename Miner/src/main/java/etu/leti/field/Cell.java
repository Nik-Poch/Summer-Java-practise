package etu.leti.field;

import com.google.gson.annotations.Expose;
import etu.leti.ore.Ore;
import etu.leti.ore.OreTypes;

public class Cell {
    @Expose
    private int posX;
    @Expose
    private int posY;
    @Expose(serialize = false, deserialize = true)
    private boolean checked;
    @Expose
    private Ore ore;

    public Cell() { }

    public Cell(int posX, int posY, Ore ore) {
        this.posX = posX;
        this.posY = posY;
        this.ore = ore;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Ore getOre() {
        return ore;
    }

    public void setOre(Ore ore) {
        this.ore = ore;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public OreTypes getOreInCellType() {
        return ore.getOreType();
    }

    public void setOreImg(String fileName) {
        ore.setPath(fileName);
    }

    public void swapCoords() {
        int temp = posX;
        posX = posY;
        posY = temp;
    }
}
