package cn.skq.test.io;

/**
 * IO流

 字节流
 InputStream
     |--FileInputStream
         构造函数
             FileInputStream(File file)
             FileInputStream(String name)
 OutputStream
     |--FileOutputStream
         构造函数
             FileOutputStream(File file)
             FileOutputStream(String name)
             FileOutputStream(File file,boolean append)
             FileOutputStream(String name,boolean append)

 字符流
 Reader
     |--InputStreamReader  转换输入流
        构造函数
             InputStreamReader(InputStream in)
             InputStreamReader(InputStream in,String charsetName)
     |--便捷类
        FileReader
            构造函数
             FileReader(String name)  默认编码表（GBK）
 Writer
     |--OutputStreamWriter  转换输出流
         构造函数
             OutputStreamWriter(OutputStream out)
             OutputStreamWriter(OutputStream out,String charsetName)
     |--便捷类
         FileWriter
            构造函数
            FileWriter(String name)	 默认编码表（GBK）

 是否高效？
 BufferedInputStream
 BufferedInputStream(InputStream in)
 BufferedOutputStream
 BufferedOutputStream(OutputStream out)
 BufferedReader
 BufferedReader(Reader r)
 BufferedWriter
 BufferedWriter(Writer w)
 */
public class IOTest {

    public static void main(String[] args) {



    }
}
