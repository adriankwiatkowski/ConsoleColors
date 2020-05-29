package com.example.logcolor;

import com.example.logcolor.cli.CommandLine;
import com.example.logcolor.models.AnsiColor;
import com.example.logcolor.models.TextAlignment;
import com.example.logcolor.utils.AppExecutors;
import com.example.logcolor.utils.PrintableFactory;
import com.example.logcolor.utils.PrintableManager;
import com.example.logcolor.utils.log.Log;
import com.example.logcolor.utils.printers.Printer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        AppExecutors.getInstance().mainThread().execute(() -> {
            setLookAndFeel();

            PrintableManager printableManager = PrintableManager.getInstance();
//            printableManager.setPrintableConsole(PrintableFactory.PrintableType.WINDOW);
//            printableManager.setDayTheme();
//
//            printSampleText();
//            printAllLevelLogsTest();

//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            printableManager.setNightTheme();
            printableManager.setPrintableConsole(PrintableFactory.PrintableType.CONSOLE);
            printSampleText();
            printAllLevelLogsTest();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AppExecutors.getInstance().shutdownNowExecutors();

//            startCommandLineInterface();

//            AppExecutors.getInstance().shutdownMainThread();
        });
    }

    private void startCommandLineInterface() {
        new CommandLine().readCommands();
    }

    private void printSampleText() {
        AnsiColor fg = AnsiColor.ANSI_BRIGHT_BLUE;
        AnsiColor bg = AnsiColor.ANSI_BRIGHT_BG_WHITE;
        TextAlignment center = TextAlignment.CENTER;
        int space = 10;
        String msg = "Message.";
        Printer.println(msg);
        Printer.println(fg, msg);
        Printer.println(fg, bg, msg);
        Printer.println(center, space, msg);
        Printer.println(fg, center, space, msg);
        Printer.println(fg, bg, center, space, msg);
        Printer.print(msg);
        Printer.print(fg, msg);
        Printer.print(fg, bg, msg);
        Printer.print(center, space, msg);
        Printer.print(fg, center, space, msg);
        Printer.print(fg, bg, center, space, msg);

        Printer.println();
        Printer.println();
    }

    private void printAllLevelLogsTest() {
        Log.v("Verbose tag", "Useless verbose message.");
        Log.i("Info tag", "Informative message.");
        Log.d("Debug tag", "Something not working here.");
        Log.w("Warning tag", "Warning, take care!");
        Log.e("Error tag", "Behold almighty error...");
        Log.w("Warning tag that will exceed max character limit.",
                "Friendly warning message.");
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}