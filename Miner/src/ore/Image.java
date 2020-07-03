package ore;

public class Image {
    private String path;
    private int height;
    private int width;

    public Image() { }

    public Image(String path, int height, int width) {
        this.path = path;
        this.height = height;
        this.width = width;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
}
