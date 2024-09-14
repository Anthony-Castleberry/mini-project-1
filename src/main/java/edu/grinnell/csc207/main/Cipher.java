package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

public class Cipher {
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    //for (int j = 0; j < args.length; j++) {
     // pen.printf("args[%d] = \"%s\"\n", j, args[j]);
    //}
    if(args.length != 4) {
      System.err.println("Incorrect number of parameters, 4 expected" + args.length + " recieved");
    }

    if(Cipher.doesinclude(args, "-vigenere") || Cipher.doesinclude(args, "-ceaser")){
      String text, vkey;
      char ckey;
      text = findtext(args, "");

      if(text.equals("")) {
        System.err.println("empty String invalid");
      }

      if(Cipher.doesinclude(args, "-ceaser")) {
        ckey = Cipher.findkey(args);
        if (ckey == 'Z') {
          System.err.println("Invalid key, ceaser keys need to be 1 chracter");
        }

        if(Cipher.doesinclude(args, "-encode")) {
          System.out.println(CipherUtils.caesarEncrypt(text, ckey));
        } else 
        if(Cipher.doesinclude(args, "-decode")) {
          System.out.println(CipherUtils.caesarDecrypt(text, ckey));
        } else {
          System.err.println("invalid action, valid actions are either '-encode' or '-decode'");
        }
      }

      if(Cipher.doesinclude(args, "-vigenere")) {
        vkey = Cipher.findtext(args, text);
        if(Cipher.doesinclude(args, "-encode")) {
          if(vkey.length() > text.length()) {
            System.out.println(CipherUtils.vigenereEncrypt(vkey, text));
          } else {
            System.out.println(CipherUtils.vigenereEncrypt(text, vkey));
          }
        } else 
        if(Cipher.doesinclude(args, "-decode")) {
          if(vkey.length() > text.length()) {
            System.out.println(CipherUtils.vigenereDecrypt(vkey, text));
          } else {
            System.out.println(CipherUtils.vigenereDecrypt(text, vkey));
          }
        } else {
          System.err.println("invalid action, valid actions are either '-encode' or '-decode'");
        }
      }

    } else {
      System.err.println("no cipher type specified, valid ciphers are '-viginere' and '-ceaser'");
    }
    pen.close();
  }

  private static boolean doesinclude(String para[] , String str) {
    for(int i = 0; i < para.length; i++) {
      if(str.equals(para[i])) {
        return true;
      }
    }
    return false;
  }

  private static String findtext(String para[], String str) {
    for(int i = 0; i < 4; i++) {
      String word = para[i];
      if(!(word.equals("-encode")) && !(word.equals("-decode")) && !(word.equals("-cease")) && !(word.equals("-vigenere"))) {
        if(word.length() > 1 && !(word.equals(str))) {
          return word;
        }
      }
    }
    return "";
  }

  private static char findkey(String para[]) {
    for(int i = 0; i < 4; i++) {
      String word = para[i];
      if(!(word.equals("-encode")) && !(word.equals("-decode")) && !(word.equals("-cease")) && !(word.equals("-vigenere"))) {
        if(word.length() == 1) {
          return word.charAt(0);
        }
      }
    }
    return 'Z';
  }
}
