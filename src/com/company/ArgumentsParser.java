package com.company;

import org.apache.commons.cli.*;

public class ArgumentsParser {


    public FindArgs Parse(String[] args) {

        Options options = new Options();

        Option input = new Option("r", "recursive", false, "is recursive");
        input.setRequired(false);
        options.addOption(input);

        Option output = new Option("d", "directory", true, "directory");
        output.setRequired(false);
        options.addOption(output);

        FindArgs s = new FindArgs();

        CommandLineParser parser = new BasicParser();

        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            return null;
        }

        s.setIsRecursive(cmd.hasOption('r'));
        s.setDirectory(cmd.getOptionValue("directory"));

        if (cmd.getArgs().length != 1) {
            System.out.println("Нет имени файла \n");
            return null;
        }

        s.setFileName(cmd.getArgs()[0]);

        //Использовалось для тестирования аргументов
        //System.out.println(s);

        return s;
    }

}
