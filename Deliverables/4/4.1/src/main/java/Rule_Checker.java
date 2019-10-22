import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Array;
import java.sql.ClientInfoStatus;
import java.util.*;

public class Rule_Checker {


    Gson gs = new Gson();


    public JsonElement startPlay(String input) throws IOException {

        List<String[][]> listBoard;
        ArrayList<JsonElement> yourList2 = new ArrayList<JsonElement>();
        JsonElement result = null;
        JsonParser jp = new JsonParser();
        BoardandScore bc = new BoardandScore();
        Type type = new TypeToken<ArrayList<JsonElement>>() {
        }.getType();
        ArrayList<JsonElement> yourList = gs.fromJson(input, type);
        if (yourList.size() == 2) {
            String stone = (yourList.get(0)).toString();
            stone=stone.replace("\"","");
            String move = (yourList.get(1)).toString();

            if (move.equals("\"pass\"")) {
                result= jp.parse("true");
            }

            else
                {
                ArrayList<JsonElement> yourList1 = gs.fromJson(move, type);
                String coord = (yourList1.get(0)).toString();
//                System.out.println("c"+coord);
                String stu = (yourList1.get(1)).toString();
                yourList2 = gs.fromJson(stu, type);
                listBoard = bc.BoardCreate(yourList2);
                int a[]=points(coord);
                test obj=new test();
                boolean ans =obj.play(stone,a[0],a[1],listBoard);
                result=jp.parse(String.valueOf(ans));
                //print_boards(listBoard);

//                for (int i = 0; i < 19; ++i) {
//                    for (int j = 0; j < 19; ++j) System.out.print((listBoard.get(0))[i][j] + "-");
//                    System.out.println();
//                }
            }

        }
        else
            {
            JsonElement ele = jp.parse(input);
          //  System.out.println(ele);
            yourList2.add(ele);
            listBoard = bc.BoardCreate(yourList2);
            //print_boards(listBoard);
           // backupBC abc=new backupBC();
            String res=bc.scoring(listBoard.get(0));
            result=jp.parse(res);
            //print_boards(listBoard);
            //System.out.println(yourList2.get(0));
        }

        return result;
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
}