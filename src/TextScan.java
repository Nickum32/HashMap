// TextScan.java
// Opens text file supplied on the command line
// Usage:  java TextScan filename
// Counts all tokens found (a token is anything delimited by a space character)
// Displays each token found to the screen
// Code may be used in part for Project 5 with proper citing.

// This code was partially written by Chris Dovolis for use in University of Minnesota CSCI 1933 Lecture

import java.util.Scanner;
import java.io.*;

public class TextScan {
    int count = 0;
    String[] ret = new String[10];
    Scanner readFile = null;
    String s;

    public TextScan(String filename) {

        System.out.println();
        System.out.println("Attempting to read from file: " + filename);
        try {
            readFile = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + filename + " not found");
            System.exit(1);
        }

        System.out.println("Connection to file: " + filename + " successful");
        System.out.println();
        while (readFile.hasNext()) {
            s = readFile.next();
            ret[count] = s;
            count++;
            if (count == ret.length) {
                String[] tempArray = new String[ret.length*2 + 1];
                for (int i=0; i<ret.length; i++) {
                    tempArray[i] = ret[i];
                }
                ret = tempArray;
            }
        }
    }

    public String[] getWords() {
        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();
        return ret;
    }

    public static void main(String[] args) {
//
//        args = new String[1];
//        args[0] = "C:/Users/Nick/IdeaProjects/Lab5/src/proverbs.txt";
        String[] ret = new String[10];
        Scanner readFile = null;
        String s;
        int count = 0;

        System.out.println();
        System.out.println("Attempting to read from file: " + args[0]);
        try {
            readFile = new Scanner(new File(args[0]));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + args[0] + " not found");
            System.exit(1);  
        }

        System.out.println("Connection to file: " + args[0] + " successful");
        System.out.println();
        while (readFile.hasNext()) {
            s = readFile.next();
            ret[count] = s;
            if (count == ret.length) {
                String[] tempArray = new String[ret.length*2 + 1];
                for (int i=0; i<ret.length; i++) {
                    tempArray[i] = ret[i];
                }
                ret = tempArray;
            }
            count++;
        }
        
        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();

    }  // main

}  // TextScan
