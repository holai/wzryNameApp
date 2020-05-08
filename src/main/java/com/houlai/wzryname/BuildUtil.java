package com.houlai.wzryname;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class BuildUtil {
    private static String[] charArray= {"\u1701","\u1702","\u1703","\u1704","\u1705","\u1706","\u1707","\u1708","\u1709","\u170a","\u170b","\u170c","\u170d","\u170e","\u170f","\u1710","\u1711","\u1712","\u1713","\u1714","\u1715","\u1717","\u1718","\u1719","\u171a","\u171b","\u171c","\u171d","\u171e","\u171f","\u1716","\u2000","\u2001","\u2002","\u2003","\u2004","\u2005","\u2006","\u2007","\u2008","\u2009","\u200a","\u200b","\u2430","\u2431","\u2432","\u2433","\u2434","\u2435","\u2436","\u2437","\u2438","\u2439","\u243a","\u243b","\u243c","\u243d","\u243e","\u243f","\u244f","\u2450","\u2451","\u2452","\u2453","\u2454","\u2455","\u2456","\u2457","\u2458","\u2459","\u245a","\u245b","\u245c","\u245d","\u245e","\u245f","\u2060","\u2061","\u2062","\u2063","\u2064","\u2065","\u206a","\u206b","\u206c","\u206d","\u206e","\u206f","\ufff0","\ufff1","\ufff2","\ufff3","\ufff4","\ufff5","\ufff6","\ufff7","\ufff8","\ufff9"};
    private static ArrayList<String> name = new ArrayList<>();
    static ArrayList<String> build(Context context,String type, String content){
        name.clear();
        int charlength = charArray.length-1;
        int  time = 6;
        switch (type){
            case "center":
                //贵族居中 要求  6个字符长度的空白符
                for(int t=0;t<time;t++) {
                    String str = "";
                    for (int i = 0; i < 6; i++) {
                        int count = randomNum(0, charlength);
                        str += charArray[count];
                    }
                    name.add(str);
                }
                break;
            case "blank":
                //空白 随机生成 3-5
                for (int t = 0; t < time; t++) {
                    int j = randomNum(3, 5);
                    String str= "";
                    for (int i = 0; i < j; i++) {
                        int count = randomNum(0, charlength);
                        str += charArray[count];
                    }
                    name.add(str);
                }
                break;
            case "reuse":
                //重复名  将content 添加空白符
                for (int t = 0; t < time; t++) {
                    int k = 12 - charCount(content);
                    String str = content;
                    for (int i = 0; i < Math.floor(k/2); i++) {
                        int count = randomNum(0, charlength);
                        str += charArray[count];
                    }
                    name.add(str);
                }
                break;
            case "sun":
                sc(context,content,"\u0489");  // ҉
                break;
            case "ly":
                sc(context,content,"\u0336");    // ̶
                break;
            case "round":
                sc(context,content,"\u20e0");        //  ⃠
                break;
            case "flower":
                sc(context,content,"\u0361");        // ͡
                break;
            case "rhombus":
                sc(context,content,"\u20df");            //  ⃟
                break;
        }
        return name;
    }

     static void sc(Context context,String content,String fh){
        int strLength = 12- charCount(content);
        if(strLength <= 1){
            Toast.makeText(context,"太长了哦",Toast.LENGTH_SHORT).show();
            return;
        }
         if(content.length() == 0){
             Toast.makeText(context,"太短了 填几个字吧",Toast.LENGTH_SHORT).show();
             return;
         }
         StringBuilder builder = new StringBuilder(content);
         if(strLength >= 6){
            int j=1;
            for(int i =0;i<content.length() && (charCount(builder.toString())<=12)  ;i++){
                builder.insert(j,fh);
                j+=2;
            }
        }
         int y = 12 -  charCount(builder.toString());
         for(int i = 0;i<y;i+=2){
             builder.append(charArray[randomNum(0,charArray.length-1)]);
         }
         name.add(builder.toString());

         for (int t=0;t<4;t++){
            StringBuilder builder_3 = new StringBuilder(content);
//            builder_3.insert(randomNum(0,content.length()),fh);
            int s =(int)Math.floor(strLength/2);  //  可放字符数
            int d = randomNum(0,s);               //  随机生成 放几个 fh
             Log.e("DDD", "sc:    总："+s+"     fh:"+d );
            for (int i =0;i<d;i++){             //
//                builder_3.append(fh);
                builder_3.insert( randomNum(1,content.length()),fh);
            }
            for(int i =0;i<s-d;i++){            // 添加 可放字符数-以放fh数量
                builder_3.append(charArray[randomNum(0,charArray.length-1)]);
            }
            name.add(builder_3.toString());
        }
    }

    static int randomNum(int min, int max){
        return (int) Math.round(Math.random()*(max-min)+min);
    }

    static int charCount(String s){
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

}
