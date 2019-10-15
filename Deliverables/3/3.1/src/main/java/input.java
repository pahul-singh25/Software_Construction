import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import java.util.Scanner;

public class input {
    public static  void main(String [] args) {
       // Scanner sc=new Scanner(System.in);
      //  int n=sc.nextInt();
        String string="";
        System.out.println("hello");
//        while(sc.hasNext())
//        {
//            String line = sc.nextLine();
//            string=string+line;
//        }
//        Gson g = new Gson();
//        //JSONObject jsonObj = new JSONObject(string);
//      //  System.out.println("json object is"+jsonObj.toString());
//        String json = g.toJson(string);
//        System.out.println("json object is"+json);
//        System.out.println("Lets check if it is json "+isJson(string));
//        System.out.println(" sc is " + string);






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
