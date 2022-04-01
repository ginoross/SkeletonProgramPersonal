package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

//scanner import is useless
import java.util.Scanner;

public class Console {

    public Console() {
    }

    //reads character with no prompt - unused
    public char readChar() {
        return readChar("");
    }

    //prints a prompt and then reads char
    public char readChar(String prompt) {
        return readLine(prompt).charAt(0);
    }

    //reads a byte
    public byte readByte(String prompt) {
        try {
            return Byte.parseByte(readLine(prompt).substring(0, 1));
        } catch (NumberFormatException nfe) {
            println(nfe.toString() + "Parsing a Byte");
        } catch (Exception e) {
            println("another exception" + e.toString());
        }
        return -1;
    }

    public byte readByte() {
        return readByte("");
    }

    public String readLine() {
        return readLine("");
    }

    //standard reading and writing methods for console
    public String readLine(String prompt) {
        String input = "";
        System.out.print(prompt);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            input = br.readLine();
        } catch (IOException ioe) {
            println("IO Error reading from command line.");
            System.exit(1);
        }
        return input;
    }

    public int readInteger(String prompt) {
        try {
            return (int) Integer.parseInt(readLine(prompt));
        } catch (NumberFormatException nfe) {
            System.out.println(" Number format excpetion.");
            System.out.println(nfe.toString());
        }
        return -1;
    }

    //uses print instead of println
    public void print(Object o) {
        String output = String.valueOf(o);
        System.out.print(output);
    }

    //prints a blank line
    public void println() {
        println("");
    }

    //badly written println method
    public void println(Object o) {
        String output = String.valueOf(o);
        System.out.println(output);
    }

    //writes something - just prints to the screen - is the same as other methods?
    public void write(Object o) {
        print(o);
    }

    public void writeLine() {
        println();
    }

    public void writeLine(Object o) {
        println(o);
    }

    public void printf(String format, Object args) {
        System.out.printf(format, args);
    }

    //prints leader board - needs fixing - fixed by only outputting the item at position i in scores instead of the whole array
    public void printLeaderBoard(){
        ArrayList<String> scores = readFile();
        System.out.println("The current high scores are: ");
        for (int i = 0; i < scores.size(); i++) {
            System.out.println(scores.get(i));
        }
    }

    //writes scores to file
    public void writeFile(String scores) {
        try {
            FileWriter writeToFile = new FileWriter(System.getProperty("user.dir") + "\\HighScores.txt", true);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            printToFile.println(scores);
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public ArrayList<String> readFile() {
        ArrayList<String> scores = new ArrayList<>();
        String inputLine;
        try {
            BufferedReader read = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\HighScores.txt"));
            while ((inputLine = read.readLine()) != null) {
                scores.add(inputLine);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return scores;
    }
}