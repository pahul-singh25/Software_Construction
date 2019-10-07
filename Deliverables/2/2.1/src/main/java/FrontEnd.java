import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FrontEnd {

    public void takeInp()throws FileNotFoundException
    {
//        System.out.println("Enter inputs");
        List<JsonElement> arr = new ArrayList<>();
        List<JsonElement> arr1 = new ArrayList<>();
        List<List<JsonElement>> arr2 =new ArrayList<>();
        //File text = new File("C:\\Users\\khaak\\Downloads\\softw_ass2\\sample.txt");

        //Creating Scanner instnace to read File in Java
        //Scanner scnr = new Scanner(text);
        Scanner sc = new Scanner(System.in);
        //Reading each line of file using Scanner class
        int lineNumber = 0;
        while(sc.hasNextLine())
        {
            if(lineNumber==10)
            {
                sort ob =new sort();
                arr1=ob.ListSort(arr);
                StringBuilder csvBuilder = new StringBuilder();

                for(JsonElement elem : arr1){
                    csvBuilder.append(elem);
                    csvBuilder.append(",");
                }

                String csv = csvBuilder.toString();
                System.out.println(csv);
                //OUTPUT: Milan,London,New York,San Francisco,

                //Remove last comma
                csv = csv.substring(0, csv.length() - ",".length());

                System.out.print(csv);
                arr2.add(arr1);
                lineNumber=0;
                arr.clear();
                //arr1.clear();
            }
            String line = sc.nextLine();
            JsonParser jsonParser = new JsonParser();
            arr.add((jsonParser.parse(line)));


            ++lineNumber;
        }
        if(lineNumber<10)
        {
            sort ob =new sort();
            arr1=ob.ListSort(arr);
            StringBuilder csvBuilder = new StringBuilder();

            for(JsonElement elem : arr1){
                csvBuilder.append(elem);
                csvBuilder.append(",");
            }

            String csv = csvBuilder.toString();
            System.out.println(csv);
            //OUTPUT: Milan,London,New York,San Francisco,

            //Remove last comma
            csv = csv.substring(0, csv.length() - ",".length());

            System.out.print(csv);
            //OUTPUT: Milan,London,New
//            for(JsonElement jo: arr1){
//                System.out.print(",");
//                System.out.print(jo);
//            }
//            System.out.print("]");
            arr2.add(arr1);
            lineNumber=0;
            arr.clear();
            arr1.clear();
        }
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
    }
}
