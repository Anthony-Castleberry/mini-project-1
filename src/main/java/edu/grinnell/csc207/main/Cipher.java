package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**encodes/decodes with a vigenere or ceaser cipher.*/
public class Cipher {

  /**number of arguments expected.*/
  private static final int EXPECTED_NUM_PARAMS = 4;

  /** runs the code to do what cipher states.
   * @param args strings that correspond to encode/decode, cipher, text, and key.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    for (int j = 0; j < args.length; j++) {
      pen.printf("args[%d] = \"%s\"\n", j, args[j]);
    } // for

    if (args.length != EXPECTED_NUM_PARAMS) {
      System.err.println("Incorrect number of parameters, 4 expected" + args.length + " recieved");
    } // if

    if (Cipher.doesinclude(args, "-vigenere") || Cipher.doesinclude(args, "-ceaser")) {
      String text;
      String vkey;
      char ckey;
      text = findtext(args, "");

      if (text.equals("")) {
        System.err.println("empty String invalid");
      } //if

      if (Cipher.doesinclude(args, "-ceaser")) {
        ckey = Cipher.findkey(args);
        if (ckey == 'Z') {
          System.err.println("Invalid key, ceaser keys need to be 1 chracter");
        } // if

        if (Cipher.doesinclude(args, "-encode")) {
          pen.println(CipherUtils.caesarEncrypt(text, ckey));
        } else {
          if (Cipher.doesinclude(args, "-decode")) {
            pen.println(CipherUtils.caesarDecrypt(text, ckey));
          } else {
            System.err.println("invalid action, valid actions are either '-encode' or '-decode'");
          } // if
        } // if
      } // if

      if (Cipher.doesinclude(args, "-vigenere")) {
        vkey = Cipher.findtext(args, text);
        if (Cipher.doesinclude(args, "-encode")) {
          if (vkey.length() > text.length()) {
            pen.println(CipherUtils.vigenereEncrypt(vkey, text));
          } else {
            pen.println(CipherUtils.vigenereEncrypt(text, vkey));
          } // if
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
      } else {
        System.err.println("no cipher type specified, valid ciphers are '-viginere' and '-ceaser'");
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
      if (!(word.equals("-encode")) && !(word.equals("-decode")) && !(word.equals("-ceaser")) && !(word.equals("-vigenere"))) {
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
      if (!(word.equals("-encode")) && !(word.equals("-decode")) && !(word.equals("-cease")) && !(word.equals("-vigenere"))) {
        if (word.length() == 1) {
          return word.charAt(0);
        } // if
      } // if
    } // for
    return 'Z';
  } // findkey
} // Cipher
