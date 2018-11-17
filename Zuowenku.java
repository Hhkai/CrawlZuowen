import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Zuowenku {
	public int page;
	public StringBuffer ctt;
	public int floor;
	private static String charset = "gb2312";
    public String outHtml;
    
    Zuowenku(String outHtml, int i) {
        this.outHtml = outHtml;
        this.page = i;
    }
    
    public Set<String> eleLink(String html) {
        Set<String> elements = new HashSet<String>();
        String page = WebC.httpRequest(html, charset);
        Pattern p = Pattern.compile("<a href='https://www.unjs.com/zuowenku/(\\d*?).html'");
        Matcher m = p.matcher(page);
        while (m.find()) {
            elements.add("https://www.unjs.com/zuowenku/" + m.group(1) + ".html");
        }
        /*
        Iterator i = elements.iterator();
        while (i.hasNext()) {
            System.out.println("" + i.next());
        }
        */
        return elements;
    }
    
    public String article(String html) {
        String page = WebC.httpRequest(html, charset);
        //Pattern p = Pattern.compile("<div class=\"con_content\">\r\n(.*?)<p>(.*?)</p>\r\n(.*?)</div>");
        Pattern p = Pattern.compile("<td height=\"300\" valign=\"top\">([\\s\\S]*?)</td>");
        Matcher m = p.matcher(page);
        //Main.textWrite("QAQ.txt", page, false);
        String content = null;
        while (m.find()) {
            content = m.group(1);
        }
        if (content == null) {
            System.out.println("error: " + html);
            return "";
        }
        Pattern br = Pattern.compile("(<(.*?)>)"); 
        Matcher matcher = br.matcher(content); 
        StringBuffer sb = new StringBuffer();
        boolean result = matcher.find(); 
        while(result) {
            matcher.appendReplacement(sb, "\r\n"); 
            result = matcher.find(); 
        }
        matcher.appendTail(sb); 
        //System.out.println(sb.toString());
        
        return sb.toString();
    }
    
    public void go(String fileName) {
        Set<String> pages = eleLink(outHtml);
        Iterator i = pages.iterator();
        int end = 999;
        int cnt = 0;
        File fileText;
        FileWriter fwr;
        try {
            fileText = new File(fileName);
            fwr = new FileWriter(fileText, true);
        
            while (i.hasNext()) {
                System.out.println(cnt + "-" + page);
                String a = i.next().toString();
                System.out.println(a);
                cnt++;
                /////
                //Main.textWrite("zzz.txt", article(a), true);
                fwr.write(article(a));
                
                //
                if (cnt == end) break;
            }
            fwr.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}