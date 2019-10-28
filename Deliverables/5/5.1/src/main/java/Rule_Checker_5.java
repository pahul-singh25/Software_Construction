import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Rule_Checker_5 {


    Gson gs = new Gson();
    boolean register =false ;
    String curr_stone ="";

    public JsonArray readInput(String input) throws IOException {

        List<String[][]> listBoard;
        ArrayList<JsonElement> yourList2 = new ArrayList<JsonElement>();
        JsonElement result = null;
        JsonArray jarr = new JsonArray();
        JsonParser jp = new JsonParser();
        BoardandScore bc = new BoardandScore();
        int in = 0;

        for (int h = 1; h <= input.length(); ++h) {
            String sub = input.substring(in, h);
            if (isJson(sub) && !(sub.equals(" "))) {
                in = h;
                //Rule_Checker ob= new Rule_Checker();
                //System.out.println("sub" + sub);
                String res = startPlay(sub);
                //result= jp.parse("true");
                // if(res.equals())

                if(!(res.isEmpty()))jarr.add(res);
            }
        }
        return jarr;
    }




    public String startPlay(String input) throws IOException {
        List<String[][]> listBoard;
        ArrayList<JsonElement> yourList2 = new ArrayList<JsonElement>();
        JsonElement result = null;
        //JsonArray jarr = null;
        JsonParser jp = new JsonParser();
        BoardandScore bc = new BoardandScore();
        Type type = new TypeToken<ArrayList<JsonElement>>() {
        }.getType();
        ArrayList<JsonElement> yourList = gs.fromJson(input, type);

        if (yourList.size() == 1) {
            //System.out.println("registered");
            register = true;
            return "no name";
        }
        if (yourList.size() == 2) {

            String first = (yourList.get(0)).toString();
           // System.out.println("first"+first);
            if (first.equals("\"receive-stones\"")) {
               // System.out.println("in_recieve"+first);
                curr_stone = (yourList.get(1)).toString();
                //stone=stone.replace("\"","");
                //System.out.println("Move of " + curr_stone);
                curr_stone=curr_stone.replace("\"","");
            } else {
                String stu = (yourList.get(1)).toString();
                yourList2 = gs.fromJson(stu, type);
                listBoard = bc.BoardCreate(yourList2);
                test obj = new test();
                String ans = obj.play(curr_stone, listBoard);
                return ans;
                //if(ans.equals("this history makes no sense "))
                //  result = jp.parse(String.valueOf(ans));
            }
        }
        return "";
    }



    public void print_boards(List<String[][]> boards) {
        for(int t=0;t<boards.size();++t) {

            for (int i = 0; i < 19; ++i) {
                for (int j = 0; j < 19; ++j) System.out.print((boards.get(t))[i][j] + "-");
                System.out.println();
            }
        }
    }

    public int[] points(String point)
    {
        int[]  pointArr=new int[2];
        point=point.substring(1,point.length()-1);
        String t[]=point.split("-");

        //System.out.println("point"+point);
        //System.out.println(t[0]);
        pointArr[0]=Integer.parseInt(t[1])-1;
        pointArr[1]=Integer.parseInt(t[0])-1;
        return pointArr;
    }


    public static boolean isJson(String Json) {
        Gson gson = new Gson();
        try {
            gson.fromJson(Json, Object.class);
            return true;
        }
        catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

}