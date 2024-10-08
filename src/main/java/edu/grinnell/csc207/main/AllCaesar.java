package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * A project for CSC-207 2024fa
 *
 * Prints all the ceaserciphers possible using
 * each indivdual lower case letter as a key.
 *
 * @author Anthony Castleberry
 */
public class AllCaesar {
  /** runs the code to print out all the ceaserciphers.
   * @param args strings that correspond to text and encode/decode.
  */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    if (args.length != 2) {
      System.err.println("Error: not enough arguments");
    } else if (args[0] != "encode" && args[0] != "decode") {
      System.err.println("Error: invalid option:" + args[0]
        + "valid options are 'encode' or 'decode'");
    } else if (args[0] == "encode") {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(args[1], ch));
      } // for
    } else {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(args[1], ch));
      } // for
    } // if

    pen.close();
  } // main
} // Allceaser
