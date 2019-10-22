import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class boardDriver {

    int place=0;
    JsonArray jarr=new JsonArray();

    public void takeInp()throws FileNotFoundException {
        //    System.out.println("Enter inputs");
        Scanner sc = new Scanner(System.in);

        boolean ans;
        //boolean ans = isJson("{name:");
        String t = "";
        JsonParser jsonParser = new JsonParser();
        //List<JsonElement> arr = new ArrayList<>();
        //arr.add();
        int lineNumber = 0;
        String rem = "";

        String elements[] = new String[20];
        String board[][] = new String[19][19];
        char c;
        //String t;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line.trim();
            if ((line.isEmpty())) ++lineNumber;
            else t = t + line;
        }
        String pat="\\[\\[\\[";
        String fg[] = t.split(pat);
        for (int va = 0; va < fg.length; ++va) {
            System.out.println("el" + fg[va]);
            if (!(fg[va].isEmpty())) {
                System.out.println("run" + lineNumber);
                ++lineNumber;
                int in = 0, p = 0;
                t = "[[["+fg[va];
                in = t.length();
                for (int i = t.length() - 1; i >= 0; --i) {
                    c = t.charAt(i);
                    if (c == ']') in = i;
                    if (c == '[') {
                        String val = t.substring(i + 1, in);
                        //    System.out.println("entered+"+val);
                        ans = isJson(t);
                        if (!(val.isEmpty()))
                            elements[p] = val;
                        in = i;
                        ++p;
                    }
                }
                Board1 ob=new Board1();
                JsonElement jel = ob.takeInp(elements);
                jarr.add(jel);
                System.out.println(jarr);
            }
        }
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