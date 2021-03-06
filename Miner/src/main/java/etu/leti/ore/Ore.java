package etu.leti.ore;

import com.google.gson.annotations.Expose;
import org.jetbrains.annotations.NotNull;

public class Ore {
    @Expose(serialize = false, deserialize = true)
    protected String imgFileName;
    @Expose
    protected OreTypes oreType;

    public Ore() {}

    public Ore(@NotNull Ore ore) {
        this.imgFileName = ore.imgFileName;
        this.oreType = ore.oreType;
    }

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
