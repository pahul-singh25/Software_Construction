import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//import org.graalvm.compiler.replacements.nodes.CStringConstant;

public class BoardandScore {

    Gson gs =new Gson();

    public List<String[][]> BoardCreate(List<JsonElement> elements)throws IOException {
        ArrayList<String[][]> boards = new ArrayList<String[][]>();

        Type ty = new TypeToken<String[][]>() {}.getType();
        for (int i = 0; i < elements.size(); ++i) {
            boards.add(gs.fromJson(elements.get(i).toString(), ty));
        }
        return boards;
    }

    public String scoring(String[][] b) throws IOException {
        int black=0,white=0;
//        int vis[][]=new int[19][19];
        int vis[][]=new int[19][19];
        for(int i=0;i<19;++i) {
            for (int j = 0; j < 19; ++j) {

                if (b[i][j].equals(" ")) b[i][j] = fill(b, vis, i, j);
                //System.out.println("assigned"+i+j+b[i][j]);
                neighbourfill(vis,b[i][j],b);
                //for(int h=0;h<19;++h){for(int k=0;k<19;++k)System.out.print(b[h][k]);System.out.println();}
//                BufferedReader m =new BufferedReader(new InputStreamReader(System.in));
//                String fr=m.readLine();
            }
        }
        for(int i=0;i<19;++i) {
            for (int j = 0; j < 19; ++j) {
                if (b[i][j].equals("B")|| b[i][j].equals("black"))++black;
                if ((b[i][j].equals("W")) || (b[i][j].equals("white")))++white;
            }
        }
        //System.out.println("\"W\":"+white+"\"B\":"+black);
        String result="{\"B\":"+black+",\"W\":"+white+"}";
        return result;
    }


    public void neighbourfill(int [][]visited,String colour,String [][]b )
    {
        for(int q=0;q<19;++q)
        {
            for(int qt=0;qt<19;++qt)
            {
                if(visited[q][qt]==1)
                {
                    b[q][qt]=colour;
                    visited[q][qt]++;
                }
            }
        }
    }

    public String fill(String[][] grid, int[][] visited, int r, int c) {
        //quit if off the grid:
        String right,left,up,down;
        //System.out.println("points"+r+" "+c);
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return "";

        //quit if visited:
        if(grid[r][c].equals("B")) {return "black";}
        if(grid[r][c].equals("W")) {return "white";}

        if(visited[r][c]==1)return"";
        visited[r][c]=1;

        right=fill(grid,visited,r,c+1);
        //System.out.println(right+"r");

        left=fill(grid,visited,r,c-1);
        //System.out.println(left+"l");

        up=fill(grid,visited,r-1,c);
        //System.out.println(up+"u");
        down=fill(grid,visited,r+1,c);
        //System.out.println(down+"d");

//        System.out.println(right+"r"+left+"l"+up+"u"+down+"d"+r+" "+c);
        if(right.equals("") && (left.equals("")) && (up.equals(""))&& (down.equals(""))){return "";}
        else if((right.equals("")||(right.equals("black"))) && (left.equals("")||(left.equals("black"))) && (up.equals("")||(up.equals("black"))) && (down.equals("")||(down.equals("black")))){return "black";}
        else if((right.equals("")||(right.equals("white"))) && (left.equals("")||(left.equals("white"))) && (up.equals("")||(up.equals("white"))) && (down.equals("")||(down.equals("white")))){return "white";}
        else return "N";
    }
}