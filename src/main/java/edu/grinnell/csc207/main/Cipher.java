package edu.grinnell.csc207.main;

import java.io.PrintWriter;

public class Cipher {
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    for (int j = 0; j < args.length; j++) {
      pen.printf("args[%d] = \"%s\"\n", j, args[j]);
    }
    pen.close();
    System.err.println("Error: Invalid parameters");
  }
} // m
