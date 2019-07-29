import java.util.Random;

public class Sudoku {
    private int[][] board;
    private Random rand;
    private int size;

    public Sudoku() {
        size = 9;
        board = new int[size][size];
        rand = new Random();


    }


    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.fillBoard();
        sudoku.printBoard();

    }

    public boolean horizontalCheck(int row, int num) { //same row, different column
        for (int i = 0; i < size; i++) { //iterates through each column of 9x9 grid
            if (board[row][i] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean verticalCheck(int col, int num) { //same column, different row
        for (int i = 0; i < size; i++) { //iterates through each row of 9x9 grid
            if (board[i][col] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean gridCheck(int row, int col, int num) {//checks 3x3 grid
        int r = row - row % 3; //sets the first row for the subgrid we need to check
        int c = col - col % 3; //sets the first column for the subgrid we need to check

        for (int i = r; i < r + 3; i++){ //loop from current location of row to last row of subgrid being checked
            for (int j = c; j < c + 3; j ++){ //loop from current location of column to last column of subgrid being checked
                if (board[i][j] == num){
                    return false;
                }
            }
        }
        return true;

    }

    public boolean fillBoard(int row, int col) {
        int num = rand.nextInt(9) + 1; //random number between [1-9]
        if (row >= 9) { //base case to prevent ArrayIndexOutOfBounds exception
            return true;
        }
        if (col > 8) { //no more columns remaining
            //System.out.println("checking row "+row+" col "+col);
            col = 0; //reset to first column
            return fillBoard(row + 1, col); //function calls itself as it moves to next row

        }

        //System.out.println("checking row "+row+" col "+col);

        while (row < 9) {
            if (num > 9) { 
                num = 1; // reset number to 1 if random number is anything greater than 9
            }

            //System.out.println("checking row " + row + " col " + col);

            if ((horizontalCheck(row, num) && verticalCheck(col, num) && gridCheck(row, col, num) && fillBoard(row, col + 1))){
                board[row][col] = num; //place that number in the cell if all conditions are met
                return true;

            }
            num++;
        }
        return false;
    }


    public boolean fillBoard(){ //ensures we start filling the board at the very first, top-left cell
        return fillBoard(0,0);
    }



    public  void printBoard(){ //displays the 9x9 grid
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col] + "\t");
            }
            System.out.println("\n");
        }


    }

}