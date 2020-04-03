package com.company;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class Find {

    public static void main(String[] args) {

        List<String> result = new ArrayList<String>();

        String currentFolder = System.getProperty("user.dir"); //текущ каталог

        FindArgs s = new ArgumentsParser().Parse(args);

        if (s == null) {
            System.out.println("Не все необходимые данные были введены \n");
            System.exit(0);
        }

        String directory =  s.getDirectory();
        System.out.println("Используются параметры:");
        System.out.println(String.format("directory:      %s", directory == null ? "Не задано, будет использоваться текущий каталог" : directory));
        System.out.println(String.format("isRecursive:    %s", s.getIsRecursive()));
        System.out.println(String.format("fileName:       %s", s.getFileName()));


        File f = new File(directory == null ? currentFolder : directory);

        FileWrapper wrapper = new FileWrapper(f);

        if (!f.isDirectory()) {
            System.out.println(String.format("Ошибка: %s не является каталогом", f.getAbsolutePath()));
            System.exit(0);
        }

        search(s.getFileName(), wrapper, s.getIsRecursive(), result);

        if (result.isEmpty())
            System.out.println("Результат: В данной директории нет такого файла \n");
        else {
            System.out.println(String.format("Результат:      Всего найдено %d файлов:\n", result.size()));
            int i = 1;
            for (final String filePath: result ) {
                System.out.println(String.format("%d) %s", i++, filePath));
            }
        }

    }

    public static void search(final String fileName, final  FileInterface folder,  final boolean isRecursive, List<String> result) {

        if (folder == null) {
            System.out.println("Не все необходимые данные были введены \n");
            System.exit(0);
        }

        for (final FileInterface f : Objects.requireNonNull(folder.listFiles())) {

            if (f.isDirectory() && isRecursive) {
                search(fileName, f, true, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(fileName)) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }

}
