package etu.leti.ore;

public class Ore {
    protected Image img;
    protected boolean movable;
    protected OreTypes oreTypes;

    public Ore() {
    }

    public Ore(OreTypes oreTypes){
        this.oreTypes = oreTypes;
    }

    public Ore(Image img, boolean movable, OreTypes oreTypes) {
        this.img = img;
        this.movable = movable;
        this.oreTypes = oreTypes;
    }

    public Image getPath() {
        return img;
    }

    public void setPath(Image img) {
        this.img = img;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public OreTypes getOreTypes() {
        return oreTypes;
    }

    public void setOreTypes(OreTypes oreTypes) {
        this.oreTypes = oreTypes;
    }
}
