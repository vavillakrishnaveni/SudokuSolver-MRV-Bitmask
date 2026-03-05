import java.util.*;

public class SudokuSolver {

    static int calls = 0;

    public static void main(String[] args) {
        int[][] board = readInput();

        long start = System.currentTimeMillis();

        if (solveSudoku(board)) {
            long end = System.currentTimeMillis();

            System.out.println("\nSolved Sudoku:");
            printBoard(board);

            System.out.println("\nRecursive Calls: " + calls);
            System.out.println("Time Taken: " + (end - start) + " ms");
        } else {
            System.out.println("No solution exists.");
        }
    }

    // Read input from user
    public static int[][] readInput() {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[9][9];

        System.out.println("Enter Sudoku board (use 0 for empty cells):");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        return board;
    }

    // Print formatted board
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0)
                System.out.println("-------------------------");

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0)
                    System.out.print("| ");

                if (board[i][j] == 0)
                    System.out.print(". ");
                else
                    System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------------------");
    }

    // Backtracking solver
    public static boolean solveSudoku(int[][] board) {
        calls++;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) {

                    for (int num = 1; num <= 9; num++) {

                        if (isValid(board, row, col, num)) {

                            board[row][col] = num;

                            if (solveSudoku(board))
                                return true;

                            board[row][col] = 0;  // backtrack
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Constraint checking
    public static boolean isValid(int[][] board, int row, int col, int num) {

        for (int i = 0; i < 9; i++)
            if (board[row][i] == num)
                return false;

        for (int i = 0; i < 9; i++)
            if (board[i][col] == num)
                return false;

        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[startRow + i][startCol + j] == num)
                    return false;

        return true;
    }
}