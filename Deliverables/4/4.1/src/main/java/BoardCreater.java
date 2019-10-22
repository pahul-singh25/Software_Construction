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

public class BoardCreater {

   Gson gs =new Gson();

    public List<String[][]> BoardCreate(List<JsonElement> elements)throws IOException {
        ArrayList<String[][]> boards = new ArrayList<String[][]>();

        Type ty = new TypeToken<String[][]>() {}.getType();
        for (int i = 0; i < elements.size(); ++i) {
            boards.add(gs.fromJson(elements.get(i).toString(), ty));
        }
        return boards;
    }

    public void scoring(String[][] b)
    {
        int black=0,white=0;
        int vis[][]=new int[19][19];

        for(int i=0;i<19;++i) {
            for (int j = 0; j < 19; ++j) {
                if (b[i][j].equals(" ")) b[i][j] = fill(b, vis, i, j);
                System.out.println("assigned"+i+j+b[i][j]);
            }
        }
        for(int i=0;i<19;++i) {
            for (int j = 0; j < 19; ++j) {
                if (b[i][j].equals("B")|| b[i][j].equals("black"))++black;
                if ((b[i][j].equals("W")) || (b[i][j].equals("white")))++white;
            }
        }
        System.out.println("White"+white+"black"+black);
    }


    public String fill(String[][] grid, int[][] visited, int r, int c) {
        //quit if off the grid:
        String right,left,up,down;
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return "";

        //quit if visited:
        if(grid[r][c].equals("B")) {System.out.println("eureka");return "black";}
        if(grid[r][c].equals("W")) {System.out.println("eureka white");return "white";}

        if(visited[r][c]==1)return"";
        visited[r][c]=1;

        right=fill(grid,visited,r+1,c);
        left=fill(grid,visited,r-1,c);
        up=fill(grid,visited,r,c+1);
        down=fill(grid,visited,r,c-1);
        System.out.println(right+"r"+left+"l"+up+"u"+down+"d"+r+" "+c);
        if((right.equals("")||(right.equals("black"))) && (left.equals("")||(left.equals("black"))) && (up.equals("")||(up.equals("black"))) && (down.equals("")||(down.equals("black")))){System.out.println("found black"); return "black";}
        else if((right.equals("")||(right.equals("white"))) && (left.equals("")||(left.equals("white"))) && (up.equals("")||(up.equals("white"))) && (down.equals("")||(down.equals("white")))){System.out.println("found white");return "white";}
        else return "N";
    }
}
