package etu.leti.ore;

public class Ore {
    protected String imgFileName;
    protected boolean movable;
    protected OreTypes oreType;

    public Ore() {}

    public Ore(OreTypes oreType){
        this.oreType = oreType;
    }

    public Ore(String imgFileName, boolean movable, OreTypes oreType) {
        this.imgFileName = imgFileName;
        this.movable = movable;
        this.oreType = oreType;
    }

    public Ore(String imgFileName, OreTypes oreType) {
        this.imgFileName = imgFileName;
        this.oreType = oreType;
    }

    public String getPath() {
        return imgFileName;
    }

    public void setPath(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public OreTypes getOreType() {
        return oreType;
    }

    public void setOreType(OreTypes oreType) {
        this.oreType = oreType;
    }
}
