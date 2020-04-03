package com.company;

public class FindArgs {

    private boolean isRecursive;
    private String directory;
    private String fileName;


    public boolean getIsRecursive() {
        return isRecursive;
    }

    public void setIsRecursive(Boolean isRecursive) {
        this.isRecursive = isRecursive;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return String.format("directory:     %s\nisRecursive:   %s\nfileName:      %s",directory,isRecursive,fileName);
    }
}
