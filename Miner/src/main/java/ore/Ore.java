package main.java.ore;

public abstract class Ore {
    protected Image img;
    protected boolean movable;

    public Ore() {
    }

    public Ore(Image img, boolean movable) {
        this.img = img;
        this.movable = movable;
    }

    public Image getPath() {
        return img;
    }

    public void setPath(Image img) {
        this.img= img;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }
}
