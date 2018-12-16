package main.java;

public class Files {
    private String name;
    private String path;

    public Files (String path) {
        this.path = path;
        this.name = setNameFromPath(path);
    }

    private String setNameFromPath(String path) {
        String [] parts = path.split("/");
        return parts[parts.length-1];
    }

    public String getName () {
        return this.name;
    }

    public String getPath () {
        return this.path;
    }
}