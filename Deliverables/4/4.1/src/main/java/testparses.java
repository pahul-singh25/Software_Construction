import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Scanner;

public class testparses {

    int place=0;
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

        for(int h=1;h<=t.length();++h) {

            String sub = t.substring(in,h);
            if(isJson(sub) && !(sub.equals(" "))){
                in = h;
                Rule_Checker ob= new Rule_Checker();
                JsonElement res=ob.startPlay(sub);
                jarr.add(res);
                }
            }
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