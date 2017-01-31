import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alex on 8/5/16.
 */
public class Main {
    private static Scanner in = new Scanner(System.in);

    private static String[][] grid;

    private static int startX;
    private static int startY;

    private static int currentX;
    private static int currentY;

    private static String direction = "east";

    private static ArrayList<Integer> xCoord = new ArrayList<Integer>();
    private static ArrayList<Integer> yCoord = new ArrayList<Integer>();

    private static ArrayList<ArrayList<Integer>[][]> possibilities = new ArrayList<ArrayList<Integer>[][]>();

    private static ArrayList<int[][]> basic = new ArrayList<int[][]>;

    public static void main(String[] args){
        makeGrid();
        createMaze();
        basic[0][0].add(0, startX);
        basic[0][1].add(0, startY);
        solveMaze();
        finalMaze();
    }//end main()

    private static void makeGrid(){
        System.out.print("Length of the Grid:\n>>> ");
        int length = in.nextInt();
        in.nextLine();

        System.out.print("Height of the Grid:\n>>> ");
        int height = in.nextInt();
        in.nextLine();

        grid = new String[height][length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = " ";
            }//end for i
        }//end for j

        printGrid();
    }//end makeGrid()

    private static void printGrid() {

        String length = "" + grid.length;
        int numLength = length.length() - 1;

        for (int i = 0; i < numLength - 1; i++){
            System.out.print(" ");
        }//end for

        System.out.print("+");

        for (int i = 0; i < grid.length; i++) {

            if (i < 10)
                System.out.print(" " + i + " ");
            else if (i < 100)
                System.out.print(" " + i);
            else
                System.out.print(i);
        }//end for
        System.out.println();

        for (int i = 0; i < grid.length; i++) {
            String number = "" + i;
            int numberLength = number.length();

            for (int j = numberLength; j < numLength; j++) {
                System.out.print(" ");
            }//end for

            System.out.print(i);

            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("[" + grid[i][j] + "]");
            }//end for i
            System.out.println();
        }//end for j
    }//end printGrid()

    private static void createMaze(){
        System.out.println("Input where the walls will be (all the cells that are not part of the maze should be a 'wall').");
        System.out.println("Type \"end\" to finish inputting walls.");

        String input = "";
        while (!input.equals("end")) {
            System.out.print("X-Coordinate:\n>>> ");
            input = in.nextLine();
            if (!input.equals("end")){
                int xCoord = Integer.parseInt(input);

                System.out.print("Y-Coordinate:\n>>> ");
                input = in.nextLine();
                if (!input.equals("end")){
                    int yCoord = Integer.parseInt(input);

                    grid[yCoord][xCoord] = "X";
                }//end if
            }//end if
        }//end while

        printGrid();

        System.out.print("\nPlease place the start and finish on the maze.\n\n");

        System.out.print("Start, X-Coordinate:\n>>> ");
        int xCoord = in.nextInt();
        startX = xCoord;
        in.nextLine();

        System.out.print("Start, Y-Coordinate:\n>>> ");
        int yCoord = in.nextInt();
        startY = yCoord;
        in.nextLine();

        grid[yCoord][xCoord] = "S";

        System.out.print("Finish, X-Coordinate:\n>>> ");
        xCoord = in.nextInt();
        in.nextLine();

        System.out.print("Finish, Y-Coordinate\n>>> ");
        yCoord = in.nextInt();
        in.nextLine();

        grid[yCoord][xCoord] = "F";

        printGrid();
    }//end createMaze()

    private static void solveMaze() {
        currentX = startX;
        currentY = startY;

        xCoord.add(currentX);
        yCoord.add(currentY);

        while (!grid[currentY][currentX].equals("F")){
            if (direction.equals("east"))
                direction = "south";
            else if (direction.equals("south"))
                direction = "west";
            else if (direction.equals("west"))
                direction = "north";
            else
                direction = "east";

            if (cellChecker()){
                moveForward();
            }//end if
            else{
                if (direction.equals("east"))
                    direction = "north";
                else if (direction.equals("south"))
                    direction = "east";
                else if (direction.equals("west"))
                    direction = "south";
                else
                    direction = "west";

                if (cellChecker()){
                    moveForward();
                }//end if
                else{
                    if (direction.equals("east"))
                        direction = "north";
                    else if (direction.equals("south"))
                        direction = "east";
                    else if (direction.equals("west"))
                        direction = "south";
                    else
                        direction = "west";
                }//end else
            }//end else
        }//end while()
    }//end solveMaze()

    private static boolean cellChecker() {
        if (direction.equals("east")) {
            if (currentX+1 < grid[0].length && !grid[currentY][currentX+1].equals("X"))
                return true;
        }//end if
        else if (direction.equals("west")){
            if (currentX-1 > grid[0].length && !grid[currentY][currentX-1].equals("X"))
                return true;
        }//end else if
        else if (direction.equals("north")){
            if (currentY-1 < grid.length && !grid[currentY-1][currentX].equals("X"))
                return true;
        }//end else if
        else{
            if (currentY+1 < grid.length && !grid[currentY+1][currentX].equals("X"))
                return true;
        }//end else
        return false;
    }//end cellChecker()

    private static void moveForward(){
        if (direction.equals("east")) {
            if (currentX+1 < grid[0].length && !grid[currentY][currentX+1].equals("X"))
                currentX++;
        }//end if
        else if (direction.equals("west")){
            if (currentX-1 > grid[0].length && !grid[currentY][currentX-1].equals("X"))
                currentX--;
        }//end else if
        else if (direction.equals("north")){
            if (currentY-1 < grid.length && !grid[currentY-1][currentX].equals("X"))
                currentY--;
        }//end else if
        else{
            if (currentY+1 < grid.length && !grid[currentY+1][currentX].equals("X"))
                currentY++;
        }//end else
        xCoord.add(currentX);
        yCoord.add(currentY);
    }//end moveForward()

    private static void finalMaze(){
        for (int i = 0; i < xCoord.size(); i++){
            grid[yCoord.get(i)][xCoord.get(i)] = "O";
        }//end for
        System.out.println();
        printGrid();
    }//end finalMaze()

    private static void findShortest(ArrayList<Integer> xCoord, ArrayList<Integer> yCoord){



        if (){

        }//end if

    }//end findShortest()

    private static double giveLength(int xCoord1, int yCoord1, int xCoord2, int yCoord2){
        return Math.sqrt(Math.pow((xCoord1 - xCoord2), 2) + Math.pow((yCoord1 - yCoord2), 2));
    }//end giveLength()

    private ArrayList<int[]> sortByLength(ArrayList<int[]> lengthTableIn){

        ArrayList<int[]> lengthTable = lengthTableIn;

        for (int i = 0; i < lengthTable.size(); i ++){
            int[] current = lengthTable.get(i);

            for (int j = i; j < lengthTable.size(); j++) {

                if (lengthTable.get(j)[2] < current[2]){
                    int[] temp = current;
                    current = lengthTable.get(j);
                    lengthTable.set(j, temp);
                }//end if

            }//end for

            lengthTable.set(i, current);
        }//end for

        return lengthTable;
    }//end sortByLength()

    private ArrayList<int[]> addAndSort(ArrayList<int[]> lengthTable, int[] newPoint){

        lengthTable.add(newPoint);

        return sortByLength(lengthTable);

    }//end addAndSort()
}//end Main