package edu.grinnell.csc207.util;

public class CipherUtils {

  private static int letter2int(char letter) {
    int i = letter;
    i = (i - 19) % 26;
    return i;
  } // method letter2int

  private static char int2letter(int i) {
    if (i < 0) {
      i += 26;
    } // if statement
    char c = (char)((i % 26) + 97);
    return c;
  } // method int2letter

  public static String caesarEncrypt(String str, char letter) {
    String Plaintext = str;
    int len = Plaintext.length();
    int key = CipherUtils.letter2int(letter);

    char[] chararray = new char[len];
    Plaintext.getChars(0, len, chararray, 0);
    for(int i = 0; i < len; i++) {
      int n = CipherUtils.letter2int(chararray[i]);
      n += key;
      chararray[i] = CipherUtils.int2letter(n);
    } // for loop
    return String.copyValueOf(chararray);
  } // method ceaserEncrypt

  public static String caesarDecrypt(String str, char letter) {
    String Ciphertext = str;
    int len = Ciphertext.length();
    int key = CipherUtils.letter2int(letter);

    char[] chararray = new char[len];
    Ciphertext.getChars(0, len, chararray, 0);
    for(int i = 0; i < len; i++) {
      int n = CipherUtils.letter2int(chararray[i]);
      n -= key;
      if (n < 0) {
        n += 26;
      } // if statement
      chararray[i] = CipherUtils.int2letter(n);
    } // for loop
    return String.copyValueOf(chararray);
  } // method ceaserDecrypt

  public static String vigenereEncrypt(String str, String key) {
    int len = str.length();
    int keylen = key.length();
    char[] plainarray = new char[len];
    str.getChars(0, len, plainarray, 0);
    char textarray[] = new char[len];
    char cipherarray[] = new char[len];

    if (keylen < len){
      int i = 0;
      for(; (i + keylen) < len; i += keylen){
        key.getChars(0, keylen, textarray, i);
      } //for loop
        key.getChars(0, len - i, textarray, i);
    } else {
      key.getChars(0, len, textarray, 0);
    }

    for(int j = 0; j < len; j++){
      int n, m;
      char c;
      n = CipherUtils.letter2int(plainarray[j]);
      m = CipherUtils.letter2int(textarray[j]);
      c = CipherUtils.int2letter(n + m);
      cipherarray[j] = c;
    } // for loop
    return String.copyValueOf(cipherarray);
  } // method vigenereEncrypt

  public static String vigenereDecrypt(String str, String key) {
    int len = str.length();
    int keylen = key.length();
    char[] plainarray = new char[len];
    str.getChars(0, len, plainarray, 0);
    char textarray[] = new char[len];
    char cipherarray[] = new char[len];

    if(keylen < len){
      int i = 0;
      for(; (i + keylen) < len; i += keylen){
        key.getChars(0, keylen, textarray, i);
      } // for loop
        key.getChars(0, len - i, textarray, i);
    } else {
      key.getChars(0, len, textarray, 0);
    }

    for(int j = 0; j < len; j++){
      int n, m;
      char c;
      n = CipherUtils.letter2int(plainarray[j]);
      m = CipherUtils.letter2int(textarray[j]);
      c = CipherUtils.int2letter(n - m);
      cipherarray[j] = c;
    } // for loop
    return String.copyValueOf(cipherarray);
  } // method vigenereDecrypt
} // Class CipherUtils
