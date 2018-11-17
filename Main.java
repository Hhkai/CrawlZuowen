import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String charset = "gb2312";
	public static void textWrite(final String fileName, final String cont, boolean append) {
		try {
			File fileText = new File(fileName);
			FileWriter fwr = new FileWriter(fileText, append);
			fwr.write(cont);
			fwr.close();
            /*
            FileOutputStream fos = new FileOutputStream("test.txt"); 
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8"); 
            osw.write(cont); 
            osw.flush();
            */
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
    /*
	public static String filterUtf8mb4(String str) {
        final int LAST_BMP = 0xFFFF;
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            int codePoint = str.codePointAt(i);
            if (codePoint < LAST_BMP) {
                sb.appendCodePoint(codePoint);
            } else {
                i++;
            }
        }
        return sb.toString();
    }
    */
	public static void main(String[] args) {
        //String aa = WebC.httpRequest("http://www.zuowen.com/e/20180809/5b6c05871778b.shtml", charset);
        //Main.textWrite("html.txt", aa);
        //String aa = WebC.httpRequest("http://www.zuowen.com/gaokaozw/manfen/index_9.shtml", charset);
        //Main.textWrite("out.txt", aa);
        int end = 80;
        for (int i = 1; i <= end; ++i) {
            String html = "http://www.zuowen.com/gaokaozw/manfen/index_" + i + ".shtml";
            Zuowen a = new Zuowen(html);
            a.go("zzz.txt");
        }
	    System.out.println("main finish");
    }
}