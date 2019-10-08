import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FrontEnd1 {

    public void takeInp()throws FileNotFoundException
    {
//        System.out.println("Enter inputs");
        Scanner sc = new Scanner(System.in);
//        String l=sc.nextLine();
//        Gson s=new Gson();
//        String test=s.toJson(l);
//        System.out.println(test);
        boolean ans;
        //boolean ans = isJson("{name:");
        String t="";
        //System.out.println(ans);
        List<JsonElement> arr = new ArrayList<>();
        List<JsonElement> arr1 = new ArrayList<>();
        List<List<JsonElement>> arr2 =new ArrayList<>();
        List<JsonArray> a1 = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        //File text = new File("C:\\Users\\khaak\\Downloads\\softw_ass2\\sample.txt");
        //Creating Scanner instnace to read File in Java
        //Scanner scnr = new Scanner(text);

        //Reading each line of file using Scanner class
        int lineNumber = 0;
        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            line.trim();
            t=t+line;
//            System.out.println("tis"+t);
            ans=isJson(t);
            if(ans)
            {
//                System.out.println("json is"+t);
                line=t;
                t="";
                arr.add((jsonParser.parse(line)));
                ++lineNumber;
                if (lineNumber == 10) {
                    arr1 = sort.ListSort(arr);
//                    for (JsonElement jo : arr1) System.out.println(jo);
                    JsonArray jarr = new JsonArray();
                    for(JsonElement elem : arr1)jarr.add(elem);
//                    arr2.add(arr1);
//                    JsonArray a = new JsonArray();
//                    JsonArray b = new JsonArray();
//                    a.add(b);
//                    //System.out.print(a);
                    //System.out.print(jarr);
//                    System.out.println(jarr);
                    a1.add(jarr);
                    arr2.add(arr1);
                    lineNumber = 0;
                    arr.clear();

                    //arr1.clear();
                }

            }

            else
            {
                if(t.charAt(0)!='{' && (t.equals(line))) {
                    String ar[] = line.split(" ");
                    t="";
                    for (int i = 0; i < ar.length; ++i) {
                        ans = isJson(ar[i]);
                        if (ans) {
//                            System.out.println("json is" + ar[i]);
                            arr.add((jsonParser.parse(ar[i])));
                            ++lineNumber;
                        }
                        else t = t + ar[i];
                    }
                }
            }
        }
//        if(lineNumber<10)
//        {
//            sort ob =new sort();
//            arr1=ob.ListSort(arr);
//            for(JsonElement jo: arr1)System.out.println(jo);
//            arr2.add(arr1);
//            lineNumber=0;
//            arr.clear();
//            arr1.clear();
//        }
        /*
        System.out.println("printing output"+arr2.size());
       // for(JsonElement jo: arr2.get(0))System.out.println(jo);
        //System.out.println("print"+arr2.get(0).get(0));
        for(JsonElement jo: (arr2.get(0))){System.out.println(jo);System.out.println("running");}
        List<JsonElement>[] arraylists;
        for(List<JsonElement> l1: arr2)
        {
            System.out.println("entered");
            for(JsonElement jo: l1)System.out.println(jo);
        }
        //sort ob =new sort();
        //arr1=ob.ListSort(arr);
        //System.out.println(arr1.get(1).getJsonVal());
        */

//        StringBuilder arr_output = new StringBuilder();
//        arr_output.append("[");
//        for(JsonArray t_arr : a1){
//            arr_output.append(t_arr.toString());
//        }
//        arr_output.append("]");
//        StringBuilder s=arr_output.replace();
//        System.out.print(s);
        System.out.print("[");
        for(JsonArray t_arr : a1){
         System.out.print(t_arr);
        }
        System.out.print("]");
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
