package src.program03;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 谢益文 on 2017/7/10.
 */
public class DealFile {

    public static void main(String[] args){
//        deal_1("E:\\work\\WeX\\数据仓储资料","用户理财文本对话-1.csv");
        try {
            deal_2("E:\\work\\WeX\\数据仓储资料","用户理财文本对话-1(1).csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       /*String line = "6 2101663744 最近可以关注哪些股票型基金呢？想做定投 5 0.00  <p>您好！目前等指数盘整稳定以后可关注指数型基金等。不喜欢风险的也可关注的固定收益类产品。谢谢提问，祝君好运！个人观点仅供参考</p><p><br/>";


        String str = line.split("<p>")[0];
        System.out.println(str);

        String patten = "[0-9]{10}";
        Pattern compile = Pattern.compile(patten);
        Matcher matcher = compile.matcher(str);


        if(matcher.find()) {
            String trim = str.substring(str.indexOf(matcher.group(0)) + 10).trim();
            System.out.println(trim);
            System.out.println(trim.substring(0,trim.length()-6));
        }
*/
        /*String line = "10338275 5210562921 000589后期如何 3 0.00 000589从技术上看在底部振荡，应该持有等待突破5.6元的平台，从股东上看，可能涉及到国企制度改革，从流通股本看不超过5亿，总体来说，值得持有，应该守股。 5 ";

        System.out.println(line.indexOf("?"));*/

        /* System.out.println(line.indexOf(matcher.group(0)));
        System.out.println(line);*/

    }

    //去除文件中的标签
    public static void deal_1(String filePath,String fileName){
        File file = new File(filePath,fileName);
        File file1 = new File(filePath,fileName+".bak");
        if(! file.exists()){
            System.out.println("文件不存在!");
            return;
        }
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1,false)));
            char[] word = new char[1];
            boolean canWrite = true;
            while(-1 != (reader.read(word))){
                if("<".equals(new String(word))){
                    canWrite = false;
                }
                else if(">".equals(new String(word))){
                    canWrite = true;
                    continue;
                }
                if(canWrite){
                    writer.write(word);
                    writer.flush();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader == null){
                try {
                    reader.close();
                    if(writer == null){
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //统计文本中的问题
    public static void deal_2(String filePath,String fileName) throws FileNotFoundException{
        File file = new File(filePath,fileName);
        File file1 = new File(filePath,fileName+".question.txt");
        if(! file.exists()){
            System.out.println("文件不存在!");
            throw new FileNotFoundException("文件不存在");
        }
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1,false)));

            String line = "";
            String patten = "[0-9]{10}";
            Pattern compile = Pattern.compile(patten);

            while((line = reader.readLine()) != null){

                while(! line.contains("<p>")){
                    String s = reader.readLine();
                    if(s == null){
                        break;
                    }
                    line += s;
                }

                String str = line.split("<p>")[0];


                Matcher matcher = compile.matcher(str);


                if(matcher.find()) {
                    String trim = str.substring(str.indexOf(matcher.group(0)) + 10).trim();
                    if(trim.length() <= 7){
                        continue;
                    }
                    String question = trim.substring(0, trim.length() - 7).trim();
                    writer.write(question+ "\n");
                }/*else{
                    int index = line.indexOf("?");
                    if(index == -1){
                        continue;
                    }
                    writer.write(line.substring(0, index+1) + "\n");
                }*/
                writer.flush();
            }

            reader.close();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader == null){
                try {
                    reader.close();
                    if(writer == null){
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
