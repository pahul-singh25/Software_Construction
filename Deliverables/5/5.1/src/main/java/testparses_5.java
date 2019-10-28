import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.Scanner;

public class testparses_5 {

    int place=0;
    String stone;
    boolean register=false;
    JsonArray jarr=new JsonArray();

    public void takeInp()throws IllegalStateException, IOException {

        Scanner sc = new Scanner(System.in);
        int lineNumber=0;
        String t = "";
        int in=0;
        Gson gs = new Gson();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line=line.trim();
            if ((line.isEmpty())) ++lineNumber;
            else t = t + line;
        }
      //  System.out.println("t"+t);
        Rule_Checker_5 obj=new Rule_Checker_5();
        jarr=obj.readInput(t);
        System.out.println(jarr);
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