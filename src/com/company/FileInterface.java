package com.company;

import java.util.List;

public interface FileInterface {
     Boolean isDirectory();
     String getName();
     Boolean isFile();
     List<FileInterface> listFiles();
     String getAbsolutePath();
}
