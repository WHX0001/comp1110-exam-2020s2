package comp1110.exam;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * COMP1110 Final Exam, Question 2
 *
 * 5 Marks
 */
public class Q2Caps {

  /**
   *
   * Q2 Part I (2 marks)
   *
   * Open the specified input file.  If the caps boolean is false, simply
   * copy all bytes of the input file to the specified output file.  Part II
   * covers the case where the caps boolean is true.
   *
   *
   * Q2 Part II (3 marks)
   *
   * Open the specified input file.  If the caps boolean is true then copy all
   * bytes of the input file directly to the output file, except any word of
   * twenty characters or less that is immediately followed by an exclamation
   * mark ('!').  Such words should be converted to all caps before copied to
   * the output file.  Words longer than 20 characters are copied directly,
   * without capitalizing.
   *
   * A word is defined as a sequence of alphabetical characters ('a' .. 'z' and
   * 'A' .. 'Z') preceded and followed by a non-alphabetical character.
   *
   * Examples (input file contents on left, output on right):
   *  "hi" -> "hi"
   *  "hi!" -> "HI!"
   *  "hi !" -> "hi !"
   *  "Hi there!" -> "Hi THERE!"
   *  "6hi!  Foo" -> "6HI!  Foo"
   *  "6hi4!  Foo" -> "6hi4!  Foo"
   *  "super!" -> "SUPER!"
   *  "supercalifragilisticexpialidocious!" -> "supercalifragilisticexpialidocious!"
   *
   * @param input The name of the input file
   * @param output The name of the output file
   * @param caps if true, capitalize words 20 characters or less if they
   *             are followed by an exclamation mark.
   */
  public static void capitalize(String input, String output, boolean caps) {
    // FIXME complete this method
    try{
      InputStream in = new FileInputStream(input);
      OutputStream out = new FileOutputStream(output);
      byte[] contentByByte = new byte[(int) new File(input).length()];
      in.read(contentByByte);

      String contentByString = "";
      for(byte c: contentByByte){
        contentByString += (char)c;
      }

      if(caps){
        String[] contentByArray = contentByString.split(" ");
        System.out.println(Arrays.toString(contentByArray));
        String res ="";
        for(String element: contentByArray){
          String elementRes = element;
          if(element.length() != 0 && element.length()<=21 && element.charAt(element.length()-1) == '!'){
            elementRes = convertToCaps(element);
          }
          res += elementRes;
        }
        int positionOfRes = 0;
        for(int i = 0; i < contentByByte.length; i++){
          if(contentByByte[i]!= ' '){
            contentByByte[i] = (byte)res.charAt(positionOfRes);
            positionOfRes++;
          }
        }
      }

      out.write(contentByByte);
    }catch(IOException e){
      e.printStackTrace();
    }

  }
  public static String convertToCaps(String input) {
    String res = "";
    for (int i = 0; i < input.length(); i++) {
      char cap = input.charAt(i);
      if(cap >= 97 && input.charAt(i) <= 122){
        cap = (char)(input.charAt(i) - 32);
      }
      res += cap;
    }
    return res;
  }
}
