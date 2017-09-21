import org.ietf.jgss.GSSManager;

import java.util.Scanner;

/**
 * Created by Dhruv Shah on 2/13/2017.
 */
public class PlayTicTacToe {
    public static boolean isPlayer1 = true;
    public static Scanner in = new Scanner(System.in);
    public static void out(String st) {System.out.print(st);}
    public static void outl(String st) {System.out.println(st);}

    static boolean trickyCheckWin(GameBoard board) {
        //TODO: deal with cases above 5 for the BONUS
        return false;
    }
    static boolean checkDiag(GameBoard board) {
        int diag1 = board.getValue(0,0);
        for (int i = 1; (diag1 != 0) && i < board.getSize(); i++) {
            if (board.getValue(i,i) != diag1)
                diag1 = 0;
        }
        int i = 0;
        int j = board.getSize();
        int diag2 = board.getValue(i,j);
        while((diag2 != 0) && i < board.getSize()) {
            if (board.getValue(i,j) != diag1)
                diag2 = 0;
            i++; j--;
        }
        return diag1 != 0 || diag2 != 0;
    }
    static boolean checkRowsCols(GameBoard board) {
        for (int i = 0; i < board.getSize(); i++) {
            int rows = board.getValue(i,0);
            int cols = board.getValue(0,i);
            for (int j = 1; (rows != 0 || cols != 0) && j < board.getSize(); j++) {
                if (board.getValue(i,j) != rows)
                    rows = 0;
                if (board.getValue(j,i) != cols)
                    cols = 0;
            }
            if (rows != 0 || cols != 0) {
                outl(playerName() + " wins!");
                return true;
            }
        }
        return false;
    }
    static boolean checkWin(GameBoard board) {
        if(board.getSize() > 5) return trickyCheckWin(board);
        if(checkRowsCols(board)) return true;
        if(checkDiag(board)) return true;
        return false;
    }
    static String playerName() {
        if (isPlayer1) {return "Player 1";}
        else {return "Player 2";}
    }
    public static void main(String[] args) {
        outl("Welcome to Tic Tac Toe");
        outl("How big do you want the play field to be?");
        int size;
        while (true) {
            size = in.nextInt();
            if (size < 2) outl("Sorry, that is too small!");
            else break;
        }
        GameBoard<TTT_Piece> board = new GameBoard<>(size,new TTT_Piece());
        outl(board.toString());
        boolean gameOver = false;
        while (!gameOver) {
            outl(playerName() + " your move!");
            out("Row:");
            int row = in.nextInt() - 1;
            out("Column:");
            int col = in.nextInt() - 1;
            if(board.makeMove(isPlayer1,row,col)) {
                gameOver = checkWin(board);
                isPlayer1 = !isPlayer1;
            }
            outl(board.toString());
        }
        isPlayer1 = !isPlayer1;
        outl("GG, " + playerName() + " is the winner!");
    }
}
