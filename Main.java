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
        //String aa = WebC.httpRequest("https://www.unjs.com/zuowenku/78905.html", charset);
        //Main.textWrite("html.txt", aa, false);
        //String aa = WebC.httpRequest("https://www.unjs.com/zuowenku/zuowen/List_375.html", charset);
        //Main.textWrite("out.txt", aa, false);
        
        for (int i = 1; i <= 99; ++i) {
            String html = "https://www.unjs.com/zuowenku/zuowen/List_" + i + ".html";
            Zuowenku a = new Zuowenku(html, i);
            a.go("zuowenku4.txt");
        }
        
	    System.out.println("main finish");
    }
}