import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

public class sort {
    public static List<JsonElement> ListSort(List<JsonElement> list) {

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (compare(list.get(j + 1), list.get(j))) {
                    JsonElement temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);


                }

            }
        }
        return list;

    }

    private static boolean compare(JsonElement obj1, JsonElement obj2) {
        // Return true if obj2>obj1

         if (obj1.isJsonObject() && (obj2.isJsonPrimitive() && obj2.getAsJsonPrimitive().isString())) {
            return false;
        } else if (obj2.isJsonObject() && (obj1.isJsonPrimitive() && obj1.getAsJsonPrimitive().isString())) {
            return true;
        } else if (((obj1.isJsonPrimitive() && obj1.getAsJsonPrimitive().isString()) || obj1.isJsonObject()) && ( obj2.isJsonPrimitive() && obj2.getAsJsonPrimitive().isNumber())) {
            return false;
        } else if (((obj2.isJsonPrimitive() && obj2.getAsJsonPrimitive().isString()) || obj2.isJsonObject()) && (obj1.isJsonPrimitive() && obj1.getAsJsonPrimitive().isNumber())) {
            return true;
        } else if (obj1.isJsonPrimitive() && obj2.isJsonPrimitive()) {
             if (obj1.getAsJsonPrimitive().isNumber() && obj2.getAsJsonPrimitive().isNumber()) {
                 return obj1.getAsJsonPrimitive().getAsDouble() < obj2.getAsJsonPrimitive().getAsDouble();

             } else if (obj1.getAsJsonPrimitive().isString() && obj2.getAsJsonPrimitive().isString()) {
                 int difference = (obj1.getAsJsonPrimitive().getAsString()).compareTo((obj2.getAsJsonPrimitive().getAsString()));

                 if (difference <= 0) {
                     return true;
                 } else {
                     return false;
                 }
             }
         }
           else if (obj1.isJsonObject() && obj2.isJsonObject()) {
                return compare(obj1.getAsJsonObject().get("name"), obj2.getAsJsonObject().get("name"));
//                JsonElement json1= obj1.getAsJsonObject().remove("name");
//                JsonElement json2=obj2.getAsJsonObject().remove("name");
//                return compare(json1,json2);


            }

         else {
            return false;
        }

        return false;
    }


}
//while(obj.