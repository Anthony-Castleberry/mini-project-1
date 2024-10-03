package edu.grinnell.csc207.util;

/**
 * A project for CSC-207 2024fa
 *  
 * helper methods to create viginere and ceaser ciphers
 * 
 * @author Anthony Castleberry
 */
public class CipherUtils {

  /**whitespace ascii code*/
  private static final int WHITESPACE= 32;

  /**value that corrects the ASCII value so tha mod 26 works when making a char an int.*/
  private static final int ASCII_OFFSET_CHAR = 19;

  /**value that corrects the int value so tha mod 26 works when making an int a char.*/
  private static final int ASCII_OFFSET_INT = 97;
  /**size of the alphabet.*/
  private static final int ALPHABET_SIZE = 26;

  /**Takes an char and returns the corresponding int for a key.
   * @param letter the char to be transformed.
   * @return int
  */
  private static int letter2int(char letter) {
    int i = letter;
    i = (i - ASCII_OFFSET_CHAR) % ALPHABET_SIZE;
    return i;
  } // letter2int

  /**Takes an int and returns the corresponding char for a key.
  * @param i the int to be transformed.
  * @return char
  */
  private static char int2letter(int i) {
    if (i < 0) {
      i += ALPHABET_SIZE;
    } // if statement
    char c = (char) ((i % ALPHABET_SIZE) + ASCII_OFFSET_INT);
    return c;
  } // int2letter


  /**encrypts a word with a letter according to the ceaser cipher.
   * @param str word to be incrypted.
   * @param letter key.
  */
  public static String caesarEncrypt(String str, char letter) {
    String Plaintext = str;
    int len = Plaintext.length();
    int key = CipherUtils.letter2int(letter);

    char[] chararray = new char[len];
    Plaintext.getChars(0, len, chararray, 0);
    for (int i = 0; i < len; i++) {
      int n = CipherUtils.letter2int(chararray[i]);
      n += key;
      chararray[i] = CipherUtils.int2letter(n);
    } // for
    return String.copyValueOf(chararray);
  } // ceaserEncrypt

  /**decrypts a word with a letter according to the ceaser cipher.
   * @param str word to be decrypted.
   * @param letter key.
  */
  public static String caesarDecrypt(String str, char letter) {
    String Ciphertext = str;
    int len = Ciphertext.length();
    int key = CipherUtils.letter2int(letter);

    char[] chararray = new char[len];
    Ciphertext.getChars(0, len, chararray, 0);
    for (int i = 0; i < len; i++) {
      int n = CipherUtils.letter2int(chararray[i]);
      n -= key;
      if (n < 0) {
        n += 26;
      } // if
      chararray[i] = CipherUtils.int2letter(n);
    } // for
    return String.copyValueOf(chararray);
  } // ceaserDecrypt


  /**encrypts a word with another word according to the viginere cipher.
   * @param str word to be incrypted.
   * @param key key.
  */
  public static String vigenereEncrypt(String str, String key) {
    int len = str.length();
    int keylen = key.length();
    char[] plainarray = new char[len];
    str.getChars(0, len, plainarray, 0);
    char[] textarray = new char[len];
    char[] cipherarray = new char[len];

    if (keylen < len) {
      int i = 0;
      for (; (i + keylen) < len; i += keylen) {
        key.getChars(0, keylen, textarray, i);
      } //for
      key.getChars(0, len - i, textarray, i);
    } else {
      key.getChars(0, len, textarray, 0);
    } // if

    for (int j = 0; j < len; j++) {
      int n;
      int m;
      char c;
      n = textarray[j];
      m = plainarray[j];
      if (n == WHITESPACE || m == WHITESPACE) { 
        System.err.println("invalid key");
      } // if
      n = CipherUtils.letter2int((char) n);
      m = CipherUtils.letter2int((char) m);
      c = CipherUtils.int2letter(n + m);
      cipherarray[j] = c;
    } // for
    return String.copyValueOf(cipherarray);
  } // vigenereEncrypt


  /**decrypts a word with another word according to the viginere cipher.
   * @param str word to be incrypted.
   * @param key key.
  */
  public static String vigenereDecrypt(String str, String key) {
    int len = str.length();
    int keylen = key.length();
    char[] plainarray = new char[len];
    str.getChars(0, len, plainarray, 0);
    char[] textarray = new char[len];
    char[] cipherarray = new char[len];

    if (keylen < len) {
      int i = 0;
      for (; (i + keylen) < len; i += keylen) {
        key.getChars(0, keylen, textarray, i);
      } // for
      key.getChars(0, len - i, textarray, i);
    } else {
      key.getChars(0, len, textarray, 0);
    } //if

    for (int j = 0; j < len; j++) {
      int n;
      int m;
      char c;
      n = CipherUtils.letter2int(plainarray[j]);
      m = CipherUtils.letter2int(textarray[j]);
      c = CipherUtils.int2letter(n - m);
      cipherarray[j] = c;
    } // for
    return String.copyValueOf(cipherarray);
  } // vigenereDecrypt
} // CipherUtils
