import java.util.ArrayList;
import java.util.List;

public class test {
    ArrayList<ArrayList<Integer>> list ;

    public boolean play(String stone, int xpos, int ypos, List<String[][]> arr)
    {

     //   System.out.print(xpos +" "+ypos);
//        System.out.println(stone);
       // System.out.println("whose chance is"+WhoseChance(arr));
        if(!stone.trim().equals(WhoseChance(arr)))//check if TURN is valid
        {
           // System.out.println("Hello");
            return false;
        }
      // System.out.println("checking validity of the board "+IsBoardValid(arr));

        if(!IsBoardValid(arr))
        {
//            System.out.println();
//            System.out.println("inside isboardvalid");
//            System.out.println();
            return false;
        }
//
       //  printBoard(arr.get(0));
//
        if(!PlaceStoneValidity(stone,xpos,ypos,arr))
        {
            return false;
        }




        return true;
    }

    public boolean PlaceStoneValidity(String stone, int xpos, int ypos, List<String[][]> arr)
    {
      //  System.out.println("hello");
        String[][] str=arr.get(0);
        if(!str[xpos][ypos].equals(" "))
        {

            return false;
        }
        if(CheckSelfCaptureRule7( stone,  xpos,  ypos, arr.get(0)))
        {
         //   System.out.println("CheckSelfCaptureRule7");
            return false;
        }
        if(CheckKoRule8(stone,  xpos,  ypos, arr))
        {
            return false;

        }

        return true;
    }

    public boolean CheckSelfCaptureRule7(String stone, int xpos, int ypos, String[][] arr)
    {
//        System.out.println("hello1");
//        System.out.println(stone);
//        System.out.println("stone.trim().equals(w)"+stone.trim().equals("W"));
        arr[xpos][ypos]=stone;

        if(stone.trim().equals("B"))
        {
            RemoveZeroLiberty("W",arr);
            if(CheckLiberty(xpos,ypos,arr)==0)
            {
                arr[xpos][ypos]=" ";

                return true;
            }
        }
        if(stone.trim().equals("W"))
        {

        //    System.out.println("hello12");
            RemoveZeroLiberty("B",arr);
          //  printBoard(arr);
            if(CheckLiberty(xpos,ypos,arr)==0)
            {
                arr[xpos][ypos]=" ";
              //  printBoard(arr);
                ResInsert(arr);
                return true;
            }
        }

        return false;
    }


    public boolean IsBoardValid(List<String[][]> arr)
    {
  //      System.out.println("size is"+arr.size());
        if(arr.size()==1)
        {
//            System.out.println();
//            System.out.println(" 1st check in board valid");
//            System.out.println();
//            System.out.println("check if board is empty"+IsBoardEmpty(arr.get(0)));
            return IsBoardEmpty(arr.get(0));
        }
        else if(arr.size()==2)
        {
//            System.out.println();
//            System.out.println(" 2nd check in board ");
//            System.out.println();
//            System.out.println("IsBoardEmpty(arr.get(1)) "+IsBoardEmpty(arr.get(1)));
//            System.out.println("AreValidConsecutives(arr.get(0),arr.get(1) "+AreValidConsecutives(arr.get(0),arr.get(1),"W"));
            return IsBoardEmpty(arr.get(1)) &&  AreValidConsecutives(arr.get(0),arr.get(1),"B");
        }
        else
        {
            String stone=WhoseChance(arr);
           // System.out.println("stone"+stone);
            if(stone.trim().equals("B"))
            {
//                System.out.println("stonr"+stone);
//               System.out.println("AreValidConsecutives(arr.get(0),arr.get(1),\"W\")"+AreValidConsecutives(arr.get(0),arr.get(1),"W"));
//               System.out.println("AreValidConsecutives(arr.get(1),arr.get(2),\"B\")"+AreValidConsecutives(arr.get(1),arr.get(2),"B"));
                return  AreValidConsecutives(arr.get(1),arr.get(2),"B") &&AreValidConsecutives(arr.get(0),arr.get(1),"W") && !CheckEqual(arr.get(0),arr.get(2));
            }
            else if(stone.trim().equals("W"))
            {
//                System.out.println("AreValidConsecutives(arr.get(0),arr.get(1),\"W\")"+AreValidConsecutives(arr.get(0),arr.get(1),"B"));
//                System.out.println("AreValidConsecutives(arr.get(1),arr.get(2),\"B\")"+AreValidConsecutives(arr.get(1),arr.get(2),"W"));
                return AreValidConsecutives(arr.get(1),arr.get(2),"W")&&  AreValidConsecutives(arr.get(0),arr.get(1),"B") && !CheckEqual(arr.get(0),arr.get(2));

            }
        }

        return true;
    }

    public boolean AreValidConsecutives(String[][] arr1,String[][] arr2,String StoneChance)
    {
        if(CheckEqual(arr1,arr2))
        {
            return true;
        }
//        String StoneChance;
//        if(Stone.equals("B"))
//        {
//            StoneChance="W";
//        }
//        else
//        {
//            StoneChance="B";
//        }
        //int x=0;
        //printBoard(arr2);
        for(int i=0;i<arr2.length;i++)
        {

            for(int j=0;j<arr2[0].length;j++)
            {
           //     x++;
                if(arr2[i][j].equals(" "))
                {
                    arr2[i][j]=StoneChance;
//                    if(i==17 && j==3 && StoneChance.trim().equals("W"))
//                    {
//                        printBoard(arr2);
//                        System.out.println();
//                    }

                    if(StoneChance.trim().equals("B"))
                    {
                        RemoveZeroLiberty("W",arr2);
////                        if(x<5)
//                        if(i==17 && j==3)
//                        {
//                            printBoard(arr2);
//                            System.out.println();
//                        }

//                        printBoard(arr2);
                        int liberty=CheckLiberty(i,j,arr2);
                        if(liberty!=0)
                        {
                            if(CheckEqual(arr1,arr2))
                            {
                                arr2[i][j]=" ";
                                ResInsert(arr2);
                                return true;
                            }
                            arr2[i][j]=" ";
                            ResInsert(arr2);
                        }
                        else
                        {
                            arr2[i][j]=" ";
                            ResInsert(arr2);
                        }
                    }
                    else
                    {
                        RemoveZeroLiberty("B",arr2);
//                        if(i==17 && j==3 && StoneChance.trim().equals("W"))
//                        {
//                            printBoard(arr2);
//                            System.out.println();
//                        }
                        int liberty=CheckLiberty(i,j,arr2);
//                        if(i==17 && j==3 && StoneChance.trim().equals("W"))
//                        {
//                            System.out.println("loberty is"+ liberty);
//                        }
//                        if(i==18 && j==18)
//                        {
//                            printBoard(arr2);
//                        }
                        if(liberty!=0)
                        {
                            if(CheckEqual(arr1,arr2))
                            {
                                arr2[i][j]=" ";
                                ResInsert(arr2);
                                return true;
                            }
                            arr2[i][j]=" ";
                            ResInsert(arr2);

                        }
                        else
                        {
                            arr2[i][j]=" ";
                            ResInsert(arr2);
                        }
                    }
                }
            }
        }
        return false;
    }

    public void printBoard(String[][] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }



    public boolean CheckKoRule8(String stone, int xpos, int ypos, List<String[][]> arr)
    {
        if(arr.size()!=3)
        {
            return false;
        }
        String[][] str=arr.get(0);
        str[xpos][ypos]=stone;
        if(stone.trim().equals("B"))
        {
            RemoveZeroLiberty("W",str);
        }
        else if(stone.trim().equals("W"))
        {
            RemoveZeroLiberty("B",str);
        }
         if(CheckEqual(str,arr.get(1)))
         {
             return true;
         }

        return false;
    }

    public boolean CheckEqual(String[][] arr1, String[][] arr2)
    {
        for(int i=0;i<arr1.length;i++)
        {
            for(int j=0;j<arr1[0].length;j++)
            {
                if(!arr1[i][j].trim().equals(arr2[i][j].trim()))
                {
                    return false;
                }
            }
        }
        return true;
    }
    // return true if self capture is possible

    public void ResInsert(String[][] arr)
    {
        for (int i = 0; i < list.size(); i++)
        {
            int xcordinate = list.get(i).get(0);
            int ycordinate = list.get(i).get(1);
            String stone;
            if (list.get(i).get(2) == 1) {
                stone = "B";
            } else {
                stone = "W";
            }
            arr[xcordinate][ycordinate] = stone;
        }
        list.clear();
    }
    public void RemoveZeroLiberty(String stone,String[][] arr)
    {
        list =new ArrayList<>();
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                if(arr[i][j].trim().equals(stone))
                {
                    int liberty=CheckLiberty(i,j,arr);
                    if(liberty==0)
                    {
                        ArrayList<Integer> arrayList=new ArrayList<>();
                        arrayList.add(i);
                        arrayList.add(j);
                        int number;
                        if(stone.equals("B"))
                        {
                            number=1;
                        }
                        else
                        {
                            number=2;
                        }
                        arrayList.add(number);
                        list.add(arrayList);

                    }
                }
            }
        }
        for(int i=0;i<list.size();i++)
        {
            int xcordinate=list.get(i).get(0);
            int ycordinate=list.get(i).get(1);
            arr[xcordinate][ycordinate]=" ";

        }
    }

    public int CheckLiberty(int xpos,int ypos,String[][] arr)
    {
        int[][] intarr=new int[19][19];
        return CheckLibertyHelper(xpos,ypos,arr[xpos][ypos],arr,intarr);


//        int liberty=0;
//        if(xpos-1>=0 && arr[xpos-1][ypos].equals(" "))
//            liberty++;
//
//        if(ypos-1>=0 && arr[xpos][ypos-1].equals(" "))
//            liberty++;
//
//        if(xpos+1<18 && arr[xpos+1][ypos].equals(" "))
//            liberty++;
//        if(ypos+1<18 && arr[xpos][ypos+1].equals(" "))
//            liberty++;
//
//        return liberty;
    }
    public int CheckLibertyHelper(int xpos,int ypos,String Stone,String[][] arr,int[][] intarr)
    {
        if(xpos<0 || ypos <0 || xpos >18 || ypos >18)
        {
            return 0;
        }
        intarr[xpos][ypos]=1;
        int ans=0;
        if(xpos-1>=0  &&  intarr[xpos-1][ypos]==0 &&  arr[xpos-1][ypos].equals(" "))
            ans++;

        if(ypos-1>=0 &&  intarr[xpos][ypos-1]==0 && arr[xpos][ypos-1].equals(" "))
            ans++;

        if(xpos+1<=18 &&  intarr[xpos+1][ypos]==0 && arr[xpos+1][ypos].equals(" "))
            ans++;
        if(ypos+1<=18 &&  intarr[xpos][ypos+1]== 0&& arr[xpos][ypos+1].equals(" "))
            ans++;

    // Recursive Calls

        if(xpos-1>=0  &&  intarr[xpos-1][ypos]==0 &&  arr[xpos-1][ypos].equals(Stone))
        {
            int smallans=CheckLibertyHelper(xpos-1,ypos,Stone,arr,intarr);
            ans=ans+smallans;
        }
        if(ypos-1>=0 &&  intarr[xpos][ypos-1]==0 && arr[xpos][ypos-1].equals(Stone))
        {
            int smallans=CheckLibertyHelper(xpos,ypos-1,Stone,arr,intarr);
            ans=ans+smallans;
        }
        if(xpos+1<=18 &&  intarr[xpos+1][ypos]==0 && arr[xpos+1][ypos].equals(Stone))
        {
            int smallans=CheckLibertyHelper(xpos+1,ypos,Stone,arr,intarr);
            ans=ans+smallans;
        }
        if(ypos+1<=18 &&  intarr[xpos][ypos+1]== 0&& arr[xpos][ypos+1].equals(Stone))
        {
            int smallans=CheckLibertyHelper(xpos,ypos+1,Stone,arr,intarr);
            ans=ans+smallans;
        }
        return ans;

    }


    public boolean IsBoardEmpty(String[][] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                if(!arr[i][j].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public String WhoseChance(List<String[][]> arr)
    {
        if(arr.size()==1)
        {
            return "B";
        }
        else if(arr.size()==2)
        {
            return "W";
        }
        else
        {
            int numberBlack1=0;
            int numberWhite1=0;
            int numberBlack2=0;
            int numberWhite2=0;
            int numberBlack3=0;
            int numberWhite3=0;

            String[][] str1=arr.get(2);
            String[][] str2=arr.get(1);
            String[][] str3=arr.get(0);

            for(int i=0;i<str1.length;i++)
            {
                for(int j=0;j<str1[0].length;j++)
                {
                    if(str1[i][j].trim().equals("B"))
                    {
                        numberBlack1++;
                    }
                    if(str1[i][j].trim().equals("W"))
                    {
                        numberWhite1++;
                    }
                }
            }

            for(int i=0;i<str2.length;i++)
            {
                for(int j=0;j<str2[0].length;j++)
                {
                    if(str2[i][j].trim().equals("B"))
                    {

                        numberBlack2++;
                    }
                    if(str2[i][j].trim().equals("W"))
                    {
                        numberWhite2++;
                    }
                }
            }

            for(int i=0;i<str3.length;i++)
            {
                for(int j=0;j<str3[0].length;j++)
                {
                    if(str3[i][j].trim().equals("B"))
                    {
                        numberBlack3++;
                    }
                    if(str3[i][j].trim().equals("W"))
                    {
                        numberWhite3++;
                    }
                }
            }

            if(numberBlack2>numberBlack1 )
            {
                return "B";
            }

            else if(numberWhite2>numberWhite1 )
            {
                return "W";
            }
            else  //incase of PASS
            {
                if(numberBlack3>numberBlack2)
                {
                    return "W";
                }
                else if(numberWhite3>numberWhite2)
                {
                    return "B";
                }
                else
                {
                    return "GAMEHASENDED";
                }
            }


        }



    }

}
