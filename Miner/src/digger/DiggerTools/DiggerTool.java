package digger.DiggerTools;

import ore.OreTypes;

public abstract class DiggerTool {
    protected OreTypes[] oreos;

    public OreTypes[] getOreos() {
        return oreos;
    }

    public void setOreos(OreTypes[] oreos) {
        this.oreos = oreos;
    }

    public DiggerTool() {
    }

    public DiggerTool(OreTypes[] oreos) {
        this.oreos = oreos;
    }

    public void dig(){}
}
