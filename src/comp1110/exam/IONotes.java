package comp1110.exam;

import java.io.*;
import java.util.Arrays;

public class IONotes {
    public static void main(String[]args) throws IOException {

        File inputFile = new File("assets/inTest.txt");
        File outputFile = new File("assets/outTest.txt");

        InputStream inStream = new FileInputStream(inputFile);
        Reader inReader = new FileReader(inputFile);
        OutputStream outStream = new FileOutputStream(outputFile);
        Writer outWriter = new FileWriter(outputFile);

        byte[] byteArrayUsedByStream= new byte[(int)inputFile.length()];
        char[] charArrayUsedByReaderAndWriter= new char[(int) inputFile.length()];

        inStream.read(byteArrayUsedByStream);
        inReader.read(charArrayUsedByReaderAndWriter);
        outStream.write(byteArrayUsedByStream);
        outWriter.write(charArrayUsedByReaderAndWriter);

        //利用buffer提高读写效率，buffer相当于缓冲器，不用的话需要逐字节/字符读取写入
        inStream = new BufferedInputStream(inStream);
        inReader = new BufferedReader(inReader);
        outStream = new BufferedOutputStream(outStream);
        outWriter = new BufferedWriter(outWriter);

        //创建Reader时，InputStreamReader类型可以将字节流转化为字符流
        Reader reader= new InputStreamReader(inStream);
        //read() 可以直接返回本次读取的长度,或者-1（代表已读取完毕）
        int sizeOfInStream = inStream.read(byteArrayUsedByStream);
        int sizeOfReader = inReader.read(charArrayUsedByReaderAndWriter);
        System.out.println("Context of byteArrayUsedByStream"+ Arrays.toString(byteArrayUsedByStream));
        System.out.println("Context of charArrayUsedByReaderAndWriter"+ Arrays.toString(charArrayUsedByReaderAndWriter));

        //检查文件是否存在
        System. out.println(inputFile.exists());
        //创建文件目录,若父目录不存在则返回false
        File file2 = new File( "d:/fatherDir/subDir");
        boolean dirCreated = file2.mkdir();
        System. out.println( dirCreated);
        //创建文件目录,若父目录不存则连同父目录一起创建
        File file3 = new File( "d:/fatherDir/subDir2");
        boolean dirCreated2 = file3.mkdirs();
        System. out.println( dirCreated2);
        File file4= new File( "d:/test.txt");
        //判断长度
        long length = file4.length();
        //重命名文件
        boolean isRenamed = file4.renameTo( new File("d:/test2.txt"));
        //删除文件
        boolean isDeleted = file4.delete();
        File file5= new File( "d:/fatherDir/subDir");
        //是否是目录
        boolean isDirectory = file5.isDirectory();
        //列出文件名
        String[] fileNames = file5.list();
        //列出目录
        File[]  files = file4.listFiles();

        inStream.close();
        inReader.close();
        outStream.close();
        outWriter.close();
    }
}
