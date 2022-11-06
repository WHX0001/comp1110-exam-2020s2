package comp1110.exam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * COMP1110 Final Exam, Question 2
 *
 * 5 Marks
 */
public class Q2Checksum {

  /**
   * Q2 Part I (2 marks)
   *
   * Open the specified input file.  If the checksum boolean is false, simply
   * copy all bytes of the input file to the specified output file.  Part II
   * covers the case where the checksum boolean is true.
   *
   *
   * Q2 Part II (3 marks)
   *
   * Open the specified input file.  If the checksum boolean is true and there
   * is at least 1 byte read in then copy all bytes of the input file to the
   * output file, inserting checksum letters before every ten (10) bytes, or
   * less if the end of file is reached. Otherwise simply copy all bytes of the
   * input file to the specified output file.
   *
   * The checksum is a letter from 'a' to 'z' which represents the integer
   * remainder of the sum of the next ten bytes (or all remaining bytes if
   * there are less than ten).
   *
   * So, if the file contained one byte, which had the value 78 ('N'), then
   * the checksum would 78 % 26 = 0, so the checksum character would be 'a',
   * and the output file would contain two bytes: 'a' followed by 'N'.
   *
   * If the input file contained two bytes which had the values 78 and 103
   * ('Ng'), then the checksum would be (78 + 103) % 26 = 25, so the checksum
   * character would be 'z', and the output file would contain three bytes: 'z'
   * followed by 'Ng'.
   *
   *
   * @param input The name of the input file
   * @param output The name of the output file
   * @param checksum if true, include checksums in the output file
   */
    public static void checksum(String input, String output, boolean checksum) {
    // FIXME complete this method
    try{
      FileInputStream in = new FileInputStream(input);
      FileOutputStream out = new FileOutputStream(output);

      byte [] temp = new byte[10];
      int byte_read;
      int count = 0;
      boolean indicator = false;
      while((byte_read = in.read()) != -1){
        if(byte_read != '\r'){
          temp[count%10] = (byte)byte_read;
          count++;
          indicator = true;
        }
        if(count%10 == 0 && indicator){
          if(checksum){
            int sum = 0;
            for(int i = 0; i < 10; i++){
              sum += temp[i];
            }
            out.write((byte)(sum%26+97));
          }
          out.write(temp);
          indicator = false;
        }
      }
      if(checksum){
        int sum = 0;
        for(int i = 0; i < count%10; i++){
          sum += temp[i];
        }
        out.write((byte)(sum%26+97));
      }
      out.write(temp,0,count%10);

      in.close();
      out.close();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}

