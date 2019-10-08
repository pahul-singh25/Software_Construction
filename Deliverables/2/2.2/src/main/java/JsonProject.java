import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonProject {

    public static  void main(String [] args){
        //      "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        BufferedReader br;
        try {
            FrontEnd1 jv=new FrontEnd1();
            jv.takeInp();
            // System.out.println(new File("input.txt").getAbsolutePath());
            //File f = new File("input.txt");

//            br = new BufferedReader(new FileReader("/Users/pahul/Desktop/Software Construction Project/src/Input.txt"));
//            String line = br.readLine();
//            List<JsonElement> list= new ArrayList<>();
//            while(line!=null){
//                JsonParser jsonParser = new JsonParser();
//                list.add((jsonParser.parse(line)));
//                line = br.readLine();
//            }
//          sort.ListSort(list);
//          for (JsonElement jo: list){
//              System.out.println(jo);
//          }
        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
       // }
        catch (IOException  e){

        }


        //Map car = objectMapper.readValue("\"omjit\"", HashMap.class);
        //JsonElement car = jsonParser.parse("\"omjit\"");


        //  System.out.println("car doors = " + car.getDoors());

//another way
        // myMap = objectMapper.readValue(mapData, new TypeReference<HashMap<String,String>>() {});
        //System.out.println("Map using TypeReference: "+myMap);
    }
}