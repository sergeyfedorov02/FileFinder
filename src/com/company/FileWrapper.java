package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileWrapper implements  FileInterface {
    private File f;

    public FileWrapper(File f) {
        this.f = f;
    }

    @Override
    public Boolean isDirectory() {
        return f.isDirectory();
    }

    @Override
    public String getName() {
        return f.getName();
    }

    @Override
    public Boolean isFile() {
        return f.isFile();
    }

    @Override
    public List<FileInterface> listFiles() {
        List<FileInterface> result = new ArrayList<FileInterface>();

        for (File fa : f.listFiles()) {
            result.add(new FileWrapper(fa));
        }
        return result;
    }

    @Override
    public String getAbsolutePath() {
       return f.getAbsolutePath();
    }
}
