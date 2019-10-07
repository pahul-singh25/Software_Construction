//
//
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//        import com.google.gson.JsonParser;
//
//        import java.io.*;
//
//public class JsonProjectMoreAdditions {
//
//    public static  void main(String [] args){
//        //      "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
//        BufferedReader br;
//        try {
//            FrontEnd jv=new FrontEnd();
//            jv.takeInp();
//            // System.out.println(new File("input.txt").getAbsolutePath());
//            //File f = new File("input.txt");
//
////            br = new BufferedReader(new FileReader("/Users/pahul/Desktop/Software Construction Project/src/Input.txt"));
////            String line = br.readLine();
////            while(line!=null){
////                JsonParser jsonParser = new JsonParser();
////                JsonElement json = jsonParser.parse(line);
////                //System.out.println(json);
////
////                if(json.isJsonPrimitive()) {
////                    System.out.println("Number : " + json.getAsJsonPrimitive());
////                    // System.out.println("Number : " + json.getAsJsonPrimitive().isNumber());
////                    // System.out.println("String : " + json.getAsJsonPrimitive().isString());
////                }else{
////
////                    //JsonObject jo =  json.getAsJsonObject().;
////
////                    while(json.isJsonObject()){
////                        System.out.println("String : " + json.getAsJsonObject().get("name"));
////
////                        json = json.getAsJsonObject().remove("name");
////                    }
////
////
////
////
////                }
////
////                line = br.readLine();
////            }
//        }
////        catch (FileNotFoundException e) {
////            e.printStackTrace();
////        }
//        catch (IOException  e){
//
//        }
//
//
//        //Map car = objectMapper.readValue("\"omjit\"", HashMap.class);
//        //JsonElement car = jsonParser.parse("\"omjit\"");
//
//
//        //  System.out.println("car doors = " + car.getDoors());
//
////another way
//        // myMap = objectMapper.readValue(mapData, new TypeReference<HashMap<String,String>>() {});
//        //System.out.println("Map using TypeReference: "+myMap);
//    }
//}
