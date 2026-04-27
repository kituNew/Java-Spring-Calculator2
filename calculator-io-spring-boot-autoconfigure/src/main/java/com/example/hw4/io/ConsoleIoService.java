package com.example.hw4.io;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIoService implements IoService {

    private final Scanner scanner;
    private final PrintStream out;

    public ConsoleIoService() {
        this(new Scanner(System.in), System.out);
    }

    ConsoleIoService(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void print(String text) {
        out.print(text);
    }

    @Override
    public void println(String text) {
        out.println(text);
    }
}
