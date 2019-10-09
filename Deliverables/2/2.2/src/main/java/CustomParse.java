import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomParse {

    public void takeInp()throws FileNotFoundException {
        //System.out.println("Enter inputs");
        Scanner sc = new Scanner(System.in);
//        String l=sc.nextLine();
//        Gson s=new Gson();
//        String test=s.toJson(l);
//        System.out.println(test);
        boolean ans;
        //boolean ans = isJson("{name:");
        String t = "";
        //System.out.println(ans);
        List<JsonElement> arr = new ArrayList<>();
        List<JsonElement> arr1 = new ArrayList<>();
        List<List<JsonElement>> arr2 = new ArrayList<>();
        List<JsonArray> a1 = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();

        //File text = new File("C:\\Users\\khaak\\Downloads\\softw_ass2\\sample.txt");
        //Creating Scanner instnace to read File in Java
        //Scanner scnr = new Scanner(text);

        //Reading each line of file using Scanner class
        int lineNumber = 0;
        String rem = "";
        char c;
        //String t;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line.trim();
            if (!(line.isBlank())) t = t+line+" ";
        }
        //System.out.println("tis"+t);
        String s;
        int in = 0, js = 0, st = 0, sp = 1,num=0,ig=1;
        if (t.charAt(0) == '{') js = 1;
        else if (t.charAt(0) == '"') st = 1;
        else num = 1;
        //System.out.println("num is"+num);

        for (int i = 1; i < t.length(); ++i) {
            c = t.charAt(i);
            //Scanner sc = new Scanner(System.in);
            //System.out.println("c is"+c+num);
            if (num > 0) {
                if (c == '{') {
                    num = 0;
                    js = 1;
                    //in = i;
                    s = t.substring(in, i);
                    in = i;
                    ans = isJson(s);
                    if (ans) {
                        arr.add((jsonParser.parse(s)));
                        //System.out.println("sis" + s);
                        ++lineNumber;
                        //if(lineNumber==10)a1=line(arr,a1);
                        //arr.clear();
                    }
                }
                if (c == '"') {
                    num = 0;
                    st = 1;

                    s = t.substring(in, i);
                    in = i;
                    ans = isJson(s);
                    if (ans) {
                        //System.out.println("sis" + s);
                        arr.add((jsonParser.parse(s)));
                        ++lineNumber;
                        //if(lineNumber==10)a1=line(arr,a1);
                        //arr.clear();
                       //
                    }
                }
                if (c == ' ' || c=='\n') {
                    num = 0;
                    sp = 1;
                    //System.out.println("Entered here");
                    s = t.substring(in,i);
                    //System.out.println("sis" + s);
                    in=i;
                    ans = isJson(s);
                    if (ans) {
                        arr.add((jsonParser.parse(s)));
                        //System.out.println("sis" + s);
                        ++lineNumber;
                        //if(lineNumber==10)a1=line(arr,a1);
                        //arr.clear();
                    }
                }
            } else if (st > 0) {
                char e=t.charAt(i-1);
                if (c == '"' && e!='\\') {
                    st = 0;
                    //in = i;
                    s = t.substring(in, i + 1);
                    in = i;
                    ans = isJson(s);
                    if (ans) {
                        arr.add((jsonParser.parse(s)));
                       // System.out.println("sis" + s);
                        ++lineNumber;
                        //if(lineNumber==10)a1=line(arr,a1);
                        //arr.clear();
                    }
                    char d = t.charAt(i + 1);
                    if (d == '{') js = 1;
                    else if (d == '"') st = 1;
                    else if (d == ' ') sp = 1;
                    else num = 1;
                    i = i + 1;
                    in = i;
                }
            } else if (js > 0) {
                if (c == '{' && ig>=1) ++js;

                else if (c == '}'&& ig>=1) {
                    --js;
                    if (js == 0) {
                        s = t.substring(in, i + 1);
                        ans = isJson(s);
                        if (ans) {
                            //System.out.println("sis" + s);
                            arr.add((jsonParser.parse(s)));
                            ++lineNumber;
                            //if(lineNumber==10)a1=line(arr,a1);
                            //arr.clear();
                        }
                        char d = t.charAt(i + 1);
                        if (d == '{') js = 1;
                        else if (d == '"') st = 1;
                        else if (d == ' ') sp = 1;
                        else num = 1;
                        i = i + 1;
                        in = i;
                    }
                }
                else if(c=='"')ig=ig*-1;

            }

          else if (c==' ') {

                if(i==t.length()-1)
                {
                    s = t.substring(in, i);
                    ans = isJson(s);
                    if (ans) {
                        //System.out.println("sis" + s);
                        arr.add((jsonParser.parse(s)));
                        ++lineNumber;
                        //if(lineNumber==10)a1=line(arr,a1);
                        //arr.clear();
                    }
                }
                else if (!(js > 0 || st > 0)) {
                    char d = t.charAt(i + 1);
                    if (d == '{') js = 1;
                    else if (d == '"') st = 1;
                    else if (d == ' ') sp = 1;
                    else num = 1;
                    i = i + 1;
                    in = i;
                }
            }
            else if(sp>0)
            {
                if (c == '{') js = 1;
                else if (c == '"') st = 1;
                else if (c == ' ') sp = 1;
                else num = 1;
                in = i;
                sp=0;
            }
        if(lineNumber==10)
        {
            a1=line(arr,a1);
            arr.clear();
            lineNumber=0;
        }
        }
//             if(t.charAt(i))
//            if(!(line.length()==0)&& (!(line.equals(" "))))
//            {
//                System.out.print("len"+line.length());
//                System.out.print("pre t" + t);
//                System.out.println("rem" + rem);
//                rem = t;
//                t = t + line;
//                System.out.println("tis1" + t);
//                ans = isJson(t);
//                if (ans) {
//                    System.out.println("json is" + t);
//                    line = t;
//                    t = "";
//                    arr.add((jsonParser.parse(line)));
//                    ++lineNumber;
//                    if (lineNumber == 10) {
//                        arr1 = sort.ListSort(arr);
////                    for (JsonElement jo : arr1) System.out.println(jo);
//                        JsonArray jarr = new JsonArray();
//                        for (JsonElement elem : arr1) jarr.add(elem);
////                    arr2.add(arr1);
////                    JsonArray a = new JsonArray();
////                    JsonArray b = new JsonArray();
////                    a.add(b);
////                    //System.out.print(a);
//                        //System.out.print(jarr);
//                        System.out.println(jarr);
//                        a1.add(jarr);
//                        arr2.add(arr1);
//                        lineNumber = 0;
//                        arr.clear();
//
//                        //arr1.clear();
//                    }
//
//                } else {
//                    //if(t.charAt(0)!='{' && (t.equals(line))) {
//                    String ar[] = line.split(" ");
//                    //t="";
//                    t = rem;
//                    for (int i = 0; i < ar.length; ++i) {
//                        System.out.println("arr" + ar[i]);
//                        t = t + ar[i];
//                        System.out.println("tis2" + t);
//                        ans = isJson(t);
//                        if (ans) {
//                            System.out.println("json is" + t);
//                            arr.add((jsonParser.parse(t)));
//                            t = "";
//                            ++lineNumber;
//                            if (lineNumber == 10) {
//                                arr1 = sort.ListSort(arr);
////                    for (JsonElement jo : arr1) System.out.println(jo);
//                                JsonArray jarr = new JsonArray();
//                                for (JsonElement elem : arr1) jarr.add(elem);
////                    arr2.add(arr1);
////                    JsonArray a = new JsonArray();
////                    JsonArray b = new JsonArray();
////                    a.add(b);
////                    //System.out.print(a);
//                                //System.out.print(jarr);
//                                System.out.println(jarr);
//                                a1.add(jarr);
//                                arr2.add(arr1);
//                                lineNumber = 0;
//                                arr.clear();
//                                //arr1.clear();
//                            }
//                        }
//                        //else t = t + ar[i];
//                    }
//                    //}
//                }
//            }
//        }
////        if(lineNumber<10)
////        {
////            sort ob =new sort();
////            arr1=ob.ListSort(arr);
////            for(JsonElement jo: arr1)System.out.println(jo);
////            arr2.add(arr1);
////            lineNumber=0;
////            arr.clear();
////            arr1.clear();
////        }
//        /*
//        System.out.println("printing output"+arr2.size());
//       // for(JsonElement jo: arr2.get(0))System.out.println(jo);
//        //System.out.println("print"+arr2.get(0).get(0));
//        for(JsonElement jo: (arr2.get(0))){System.out.println(jo);System.out.println("running");}
//        List<JsonElement>[] arraylists;
//        for(List<JsonElement> l1: arr2)
//        {
//            System.out.println("entered");
//            for(JsonElement jo: l1)System.out.println(jo);
//        }
//        //sort ob =new sort();
//        //arr1=ob.ListSort(arr);
//        //System.out.println(arr1.get(1).getJsonVal());
//        */
//
//        //StringBuilder arr_output = new StringBuilder();
//        //arr_output.append("[");
        int p=1;
           // System.out.println("size" + a1.size());
            System.out.print("[");
            for (JsonArray t_arr : a1) {
                System.out.print(t_arr);
                if (p <= a1.size() - 1) System.out.print(",");
                ++p;
                //else System.out.print(",");
                //arr_output.append(t_arr.toString());
            }
            //arr_output.append("]");
            System.out.print("]");
            //System.out.print(arr_output);
            //arr1=sort.ListSort(arr);
//            JsonArray jarr = new JsonArray();
//            for (JsonElement elem : arr1) jarr.add(elem);
//            System.out.println(jarr);
        }

    public List<JsonArray> line(List<JsonElement> arr,List<JsonArray>a1)
    {
        List<JsonElement> arr1 = sort.ListSort(arr);
//                    for (JsonElement jo : arr1) System.out.println(jo);
        JsonArray jarr = new JsonArray();
        for (JsonElement elem : arr1) jarr.add(elem);
//                    arr2.add(arr1);
//                    JsonArray a = new JsonArray();
//                    JsonArray b = new JsonArray();
//                    a.add(b);
//                    //System.out.print(a);
        //System.out.print(jarr);
        //System.out.println(jarr);
        a1.add(jarr);
        return a1;
    }

    public static boolean isJson(String Json) {
        Gson gson = new Gson();
        try {
            gson.fromJson(Json, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }
}
