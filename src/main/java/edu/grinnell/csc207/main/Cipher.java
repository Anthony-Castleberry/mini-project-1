package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;


/**
 * A project for CSC-207 2024fa
 *
 * encodes/decodes with a vigenere or caesar cipher.
 *
 * @author Anthony Castleberry
 */
public class Cipher {

  /**number of arguments expected.*/
  private static final int EXPECTED_NUM_PARAMS = 4;

  /**number of arguments expected.*/
  private static final int ASCII_UPPERBOUND = 122;

  /**number of arguments expected.*/
  private static final int ASCII_LOWERBOUND = 97;

  /** runs the code to do what cipher states.
   * @param args strings that correspond to encode/decode, cipher, text, and key.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    if (args.length != EXPECTED_NUM_PARAMS) {
      System.err.println("Error: incorrect number of parameters, 4 expected"
        + args.length + " recieved");
    } else if (Cipher.doesinclude(args, "-vigenere") || Cipher.doesinclude(args, "-caesar")) {
      String text;
      text = findtext(args, "");
      String vkey;
      char ckey;

      if (text.equals("")) {
        System.err.println("empty String invalid");
      } // if

      if (!valid(text)) {
        System.err.println("Error: invalid text, caesar keys need to be 1 chracter");
      } else if (Cipher.doesinclude(args, "-caesar") && valid(text)) {
        ckey = Cipher.findkey(args);
        if (ckey == 'Z') {
          System.err.println("Invalid key, caesar keys need to be 1 chracter");
        } else if (Cipher.doesinclude(args, "-encode")) {
          pen.println(CipherUtils.caesarEncrypt(text, ckey));
        } else {
          if (Cipher.doesinclude(args, "-decode")) {
            pen.println(CipherUtils.caesarDecrypt(text, ckey));
          } else {
            System.err.println("invalid action, valid actions are either '-encode' or '-decode'");
          } // if
        } // if
      } else if (Cipher.doesinclude(args, "-vigenere") && valid(text)) {
        vkey = Cipher.findtext(args, text);
        if (!valid(vkey)) {
          System.err.println("Error: invalid key");
        } else if (Cipher.doesinclude(args, "-encode")) {
          pen.println(CipherUtils.vigenereEncrypt(text, vkey));
        } else {
          if (Cipher.doesinclude(args, "-decode")) {
            if (vkey.length() > text.length()) {
              pen.println(CipherUtils.vigenereDecrypt(vkey, text));
            } else {
              pen.println(CipherUtils.vigenereDecrypt(text, vkey));
            } // if
          } else {
            System.err.println("invalid action, valid actions are either '-encode' or '-decode'");
          } // if
        } // if
      } // if
    } // if
    pen.close();
  } // main

  private static boolean doesinclude(String[] para, String str) {
    for (int i = 0; i < para.length; i++) {
      if (str.equals(para[i])) {
        return true;
      } // if
    } // for
    return false;
  } // doesinclude

  private static String findtext(String[] para, String str) {
    for (int i = 0; i < EXPECTED_NUM_PARAMS; i++) {
      String word = para[i];
      if (!(word.equals("-encode")) && !(word.equals("-decode"))
        && !(word.equals("-caesar")) && !(word.equals("-vigenere"))) {
        if (word.length() > 1 && !(word.equals(str))) {
          return word;
        } // if
      } // if
    } // for
    return "";
  } // findtext

  private static char findkey(String[] para) {
    for (int i = 0; i < EXPECTED_NUM_PARAMS; i++) {
      String word = para[i];
      if (!(word.equals("-encode")) && !(word.equals("-decode")) 
        && !(word.equals("-cease")) && !(word.equals("-vigenere"))) {
        if (word.length() == 1) {
          return word.charAt(0);
        } // if
      } // if
    } // for
    return 'Z';
  } // findkey

  private static boolean valid(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) < ASCII_LOWERBOUND || str.charAt(i) > ASCII_UPPERBOUND) {
        return false;
      } // if
    } // for
    return true;
  } // valid
} // Cipher
