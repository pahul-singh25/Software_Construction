import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class input {

    int place=0;
    JsonElement result;
    JsonParser jsonParser = new JsonParser();
    JsonArray jarr=new JsonArray();
    //jsonParser.parse("yesy");

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
            line=line.trim();
            if ((line.isEmpty())) t = t + "breakpoint";
            else t = t + line;
        }
        String fg[] = t.split("breakpoint");
      //  System.out.println("len" + fg.length);
        for (int va = 0; va < fg.length; ++va) {
           // System.out.println("el" + fg[va]);
            if (!(fg[va].isEmpty())) {
             //   System.out.println("run"+lineNumber);
                ++lineNumber;
                int in = 0, p = 0;
                t = fg[va];
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

                for (int i = 0; i < elements.length; i++) {
                  //  System.out.println(elements[i]);
                }


              //  System.out.println(elements.length + " len");

                in = 0;
                for (int j = 0; j < 19; ++j) {
                    String bo[] = elements[j + 1].split(",");
                    // System.out.println(bo.length+"len");
                    for (int l = 0; l < 19; ++l) {
                        board[19 - j - 1][l] = bo[l];
                        //   System.out.println("val"+j+" "+l+"bo"+board[j][l]);
                    }
                }
//                System.out.println();
//                System.out.println();
//                System.out.println("printing the board");
//                System.out.println();
//                for (int i = 0; i < 19; i++) {
//                    for (int j = 0; j < 19; j++) {
//                        System.out.print(board[i][j] + " ");
//                    }
//                    System.out.println();
//                }
                int pos = 0;
                //boolean ans;
                String com[] = comm(elements[0]);

                if (com.length > 2) {
                    if (com[0].equals("\"reachable?\"")) {
                        int position2[] = points(com[1]);
                        int[][] arr = new int[19][19];
                        arr[position2[0]][position2[1]] = 1;
//                        for (int i = 0; i < 19; i++) {
//                            for (int j = 0; j < 19; j++) {
//                                System.out.print(arr[i][j]);
//                            }
//                            System.out.println();
//                        }

//                        System.out.println();
//                        System.out.println();
//                        System.out.println("printing the points");
//                        System.out.println();
//                        System.out.println(position2[0] + " " + position2[1]);

                        String pointType = com[2];
//                        System.out.println();
//                        System.out.println();
//                        System.out.println("printing the point Type");
//                        // System.out.println();
//                        System.out.println(pointType.trim());
//                        String a = "a";
//                        System.out.println(a);

                        arr[position2[0]][position2[1]] = 1;
                        boolean Isreachable = reachable(board, position2[0], position2[1], com[2], arr);
                        result = jsonParser.parse(String.valueOf(Isreachable));
//                        System.out.println(" bbbbbb is " + board[position2[0]][position2[1]]);
//                        System.out.println(" ccccccc is " + com[2]);
//                        if ((board[position2[0]][position2[1]].trim()).equals(com[2].trim())) {
//                            System.out.println("true true true true");
//                        }
                      //  System.out.println(" answer is " + Isreachable);
                        jarr.add(result);

                    } else {
                       // System.out.println(":poi" + com[2]);
                        int position[] = points(com[2]);
                        com[0] = com[0].trim();
                     //   System.out.println("com is" + com[0]);
                        if (com[0].equals("\"occupies?\"")) {
                            result = jsonParser.parse(String.valueOf(hasStone(board, com[1], position[0], position[1])));
                            String s = String.valueOf(isOccupied(board, position[0], position[1]));
                       //     System.out.println("str is " + s);
                        }

                        if (com[0].equals("\"place\"")) {
                         //   System.out.println("entered here");
                            board = placeStone(board, com[1], position[0], position[1]);
                            String s = "\"This seat is taken!\"";
                            if (place == 0) result = jsonParser.parse(s);
                            if (place == 1) {
                                place = 0;
                                //String bor = "";
                                result = jsonParser.parse(conv(board));
                            }
                        }
                        if (com[0].equals("\"remove\"")) {
                            board = removeStone(board, com[1], position[0], position[1]);
                            String s = "\"I am just a board! I cannot remove what is not there!\"";
                            if (place == 0) result = jsonParser.parse(s);
                            if (place == 1) {
                                place = 0;
                                result = jsonParser.parse(conv(board));
                            }
                        }

       //                 System.out.println("Result " + result);
                        jarr.add(result);
                    }
                }

                else {

                    if (com[0].equals("\"occupied?\"")) {
                        int position[] = points(com[1]);
                        com[0] = com[0].trim();
                      //  System.out.println("com is" + com[0]);
                      //  System.out.println(isOccupied(board, position[0], position[1]));
                        result = jsonParser.parse(String.valueOf(isOccupied(board, position[0], position[1])));
                        String s = String.valueOf(isOccupied(board, position[0], position[1]));
                      //  System.out.println("str is " + s);

                    }
                    if(com[0].equals("\"get-points\""))
                    {
//                        System.out.println("Hey i am in get points hurray");
//                        System.out.println("Hey i am in get points hurray");
//                        System.out.println("Hey i am in get points hurray");
                        ArrayList<String> lis=GetPoints(board,com[1]);
                        String r="";
                        if(lis.size()!=0)
                        {
                            for (int y = 0; y < lis.size(); ++y) r = r + lis.get(y) + ",";
                            r = "[" + r.substring(0, r.length() - 1) + "]";
                         //   System.out.println("get points" + r);
                        }
                        else r="[]";
                        result=jsonParser.parse(r);

                    }
                    jarr.add(result);
                }
            }
        }
        System.out.println(jarr);
    }

    public String conv(String board[][])
    {
        String bor="";
        for (int i = 0; i < 19; ++i)
        {
            for (int j = 0; j < 19; ++j) bor = bor + board[i][j] + ",";
            bor=bor.substring(0,bor.length()-1)+"],[";
            //System.out.println(bor);
        }
        bor = "[[" + bor.substring(0,(bor.length()-2)) + "]";
        //System.out.println("result"+bor) ;
        return bor;
    }


    public boolean isOccupied(String b[][],int p1,int p2)
    {
      //  System.out.println("elemet is "+b[p1][p2]);
        String st=b[p1][p2].trim();
        if(st.equals("\" \""))return false;
        else return true;
    }

    public boolean hasStone(String b[][],String stone, int p1,int p2)
    {
        stone=stone.trim();
        String p=b[p1][p2].trim();
       // System.out.println("stone"+stone+b[p1][p2]);
        if(p.equals(stone))return true;
        else return false;
    }

    public String[][] placeStone(String b[][],String stone,int p1,int p2)
    {
        stone=stone.trim();
        if(!(isOccupied(b,p1,p2))){ b[p1][p2]=stone; place=1;}
        return b;
    }

    public String[][] removeStone(String b[][],String stone,int p1,int p2)
    {
        stone=stone.trim();
        if(hasStone(b,stone,p1,p2)){b[p1][p2]="\" \""; place=1;}

        return b;
    }



    public String[] comm(String b)
    {
        String el[]=b.split(",");
        return el;
    }

    public int[] points(String b)
    {
        b=b.trim();
        String poi[]=((b.substring(1,b.length()-1)).split("-"));

//        System.out.println("po"+poi[1]);
//        System.out.println("po"+poi[0]);
        int ab[]=new int[2];
        ab[0]=(Integer.parseInt(poi[1])-1);
        ab[1]=Integer.parseInt(poi[0])-1;
        return ab;
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
    public ArrayList<String> GetPoints(String b[][], String PointType)

    {
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<b.length;i++)
        {
            for(int j=0;j<b[0].length;j++)
            {
                if(b[i][j].trim().equals(PointType.trim()))
                {

                    String value="\""+(j+1)+"-"+(i+1)+"\"";
                    list.add(value);
                }

            }
        }
       // System.out.println();
//        System.out.println();
//        System.out.println(" list is ");
//        System.out.println();
//        for(int i=0;i<list.size();i++)
//        {
//            System.out.print(list.get(i)+" ");
//        }
        Collections.sort(list);
//        System.out.println();
//        System.out.println();
//        System.out.println(" sorted list is ");
//        System.out.println();
//        for(int i=0;i<list.size();i++)
//        {
//            System.out.print(list.get(i)+" ");
//        }
        return list;

    }

    // public boolean isReachable(String b[][],)
    public boolean reachable(String b[][],int p1,int p2, String PointType,int[][] arr)
    {
        arr[p1][p2]=1;
        if(p1<0 || p1 >18 || p2<0 || p2 >18)
        {
            return false;
        }
        if(b[p1][p2].trim().equals(PointType.trim())|| (p1>0 && b[p1-1][p2].trim().equals(PointType.trim())) || (p2>0 && b[p1][p2-1].trim().equals(PointType.trim())) || (p1<18 &&b[p1+1][p2].trim().equals(PointType.trim())) || (p2<18 && b[p1][p2+1].trim().equals(PointType.trim())))
        {
            return true;
        }
        boolean smallans=false;
        smallans=(p1>0 && arr[p1-1][p2]==0 && b[p1-1][p2].trim().equals(b[p1][p2].trim()) && reachable(b,p1-1,p2,PointType,arr)) || (p2>0 &&arr[p1][p2-1]==0 && b[p1][p2-1].trim().equals(b[p1][p2].trim()) &&reachable(b,p1,p2-1,PointType,arr))||(p1<18 && arr[p1+1][p2]==0 &&b[p1+1][p2].trim().equals(b[p1][p2].trim()) &&reachable(b,p1+1,p2,PointType,arr))||(p2<18 && arr[p1][p2+1]==0 && b[p1][p2+1].trim().equals(b[p1][p2].trim()) &&reachable(b,p1,p2+1,PointType,arr));
        return  smallans;
    }
}