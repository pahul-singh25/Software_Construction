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

        Scanner sc = new Scanner(System.in);

        boolean ans;

        String t = "";
        List<JsonElement> arr = new ArrayList<>();

        List<JsonArray> a1 = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();

        //File text = new File("C:\\Users\\khaak\\Downloads\\softw_ass2\\sample.txt");
        //Creating Scanner instnace to read File in Java
        //Scanner scnr = new Scanner(text);

        int lineNumber = 0;
        String rem = "";
        char c;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line.trim();
            if (!(line.isEmpty())) t = t+line+" ";
        }
        String s;int in = 0, js = 0, st = 0, sp = 1,num=0,ig=1;

        if (t.charAt(0) == '{') js = 1;
        else if (t.charAt(0) == '"') st = 1;
        else num = 1;


        for (int i = 1; i < t.length(); ++i) {
            c = t.charAt(i);

            if (num > 0) {
                if (c == '{') {
                    num = 0; js = 1;
                    s = t.substring(in, i);
                    in = i;
                    ans = isJson(s);
                    if (ans) {
                        arr.add((jsonParser.parse(s)));
                        ++lineNumber;
                    }
                }
                if (c == '"') {
                    num = 0;
                    st = 1;

                    s = t.substring(in, i);
                    in = i;
                    ans = isJson(s);
                    if (ans) {
                         arr.add((jsonParser.parse(s)));
                        ++lineNumber;

                    }
                }
                if (c == ' ' || c=='\n') {
                    num = 0;
                    sp = 1;
                    s = t.substring(in,i);
                    in=i;
                    ans = isJson(s);
                    if (ans) {
                        arr.add((jsonParser.parse(s)));
                        ++lineNumber;
                    }
                }
            } else if (st > 0) {
                char e=t.charAt(i-1);
                if (c == '"' && e!='\\') {
                    st = 0;
                    s = t.substring(in, i + 1);
                    in = i;
                    ans = isJson(s);
                    if (ans) {
                        arr.add((jsonParser.parse(s)));
                        ++lineNumber;
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
            else if (js > 0) {
                if (c == '{' && ig>=1) ++js;

                else if (c == '}'&& ig>=1) {
                    --js;
                    if (js == 0) {
                        s = t.substring(in, i + 1);
                        ans = isJson(s);
                        if (ans) {
                            arr.add((jsonParser.parse(s)));
                            ++lineNumber;
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
                        arr.add((jsonParser.parse(s)));
                        ++lineNumber;
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

        int p=1;
            System.out.print("[");
            for (JsonArray t_arr : a1) {
                System.out.print(t_arr);
                if (p <= a1.size() - 1) System.out.print(",");
                ++p;
            }
            System.out.print("]");

        }

    public List<JsonArray> line(List<JsonElement> arr,List<JsonArray>a1)
    {
        List<JsonElement> arr1 = sort.ListSort(arr);

        JsonArray jarr = new JsonArray();
        for (JsonElement elem : arr1) jarr.add(elem);
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
