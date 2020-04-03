package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class FindTest {

    @Test
    void searchEmptyFolder() {
        FileInterface fi= Mockito.mock(FileInterface.class);

        List<FileInterface> y = new ArrayList<>();

        Mockito.when(fi.listFiles()).thenReturn(y);

        List<String> result = new ArrayList<>();

        Find.search("jsondata_test.go", fi, false, result);
        Assertions.assertLinesMatch(new ArrayList<String>(), result);
    }

    @Test
    void searchFileName() {
        FileInterface fi= Mockito.mock(FileInterface.class);

        FileInterface f1= Mockito.mock(FileInterface.class);
        Mockito.when(f1.getName()).thenReturn("test.go");
        Mockito.when(f1.isFile()).thenReturn(true);
        Mockito.when(f1.getAbsolutePath()).thenReturn("c:\\test.go");

        FileInterface f2= Mockito.mock(FileInterface.class);
        Mockito.when(f2.getName()).thenReturn("jsondata_test.go");
        Mockito.when(f2.isFile()).thenReturn(true);
        Mockito.when(f2.getAbsolutePath()).thenReturn("c:\\jsondata_test.go");

        List<FileInterface> y = new ArrayList<FileInterface>();
        y.add(f1);
        y.add(f2);

        Mockito.when(fi.listFiles()).thenReturn(y);

        List<String> result = new ArrayList<>();

        Find.search("jsondata_test.go", fi, false, result);
        Assertions.assertLinesMatch(new ArrayList<String>(Arrays.asList("c:\\jsondata_test.go")), result);

        Find.search("test.go", fi, false, result);
        Assertions.assertLinesMatch(new ArrayList<String>(Arrays.asList("c:\\jsondata_test.go", "c:\\test.go")), result);

        result.clear();
        Find.search("test1.go", fi, false, result);
        Assertions.assertLinesMatch(new ArrayList<String>(), result);
    }

    @Test
    void searchIsRecursive() {
        FileInterface fi= Mockito.mock(FileInterface.class);

        FileInterface f1= Mockito.mock(FileInterface.class);
        Mockito.when(f1.isDirectory()).thenReturn(true);

        FileInterface f2= Mockito.mock(FileInterface.class);
        Mockito.when(f2.getName()).thenReturn("jsondata_test.go");
        Mockito.when(f2.isFile()).thenReturn(true);
        Mockito.when(f2.getAbsolutePath()).thenReturn("c:\\test.go\\jsondata_test.go");
        Mockito.when(f2.isDirectory()).thenReturn(false);

        Mockito.when(f1.listFiles()).thenReturn(new ArrayList<>(Arrays.asList(f2)));

        Mockito.when(fi.listFiles()).thenReturn(new ArrayList<>(Arrays.asList(f1)));

        List<String> result = new ArrayList<>();


        Find.search("test.go", fi, true, result);
        Assertions.assertLinesMatch(new ArrayList<String>(), result);

        Find.search("jsondata_test.go", fi, true, result);
        Assertions.assertLinesMatch(new ArrayList<String>(Arrays.asList("c:\\test.go\\jsondata_test.go")), result);
        result.clear();

        Find.search("jsondata_test.go", fi, false, result);
        Assertions.assertLinesMatch(new ArrayList<String>(), result);

    }
}