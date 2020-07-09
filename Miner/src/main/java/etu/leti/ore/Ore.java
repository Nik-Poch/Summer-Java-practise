package etu.leti.ore;

public class Ore {
    protected String imgFileName;
    protected OreTypes oreType;

    public Ore() {}

    public Ore(OreTypes oreType){
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

    public OreTypes getOreType() {
        return oreType;
    }

    public void setOreType(OreTypes oreType) {
        this.oreType = oreType;
    }
}
