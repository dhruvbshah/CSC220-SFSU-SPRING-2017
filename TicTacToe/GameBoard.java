/**
 * Created by Dhruv Shah on 2/13/2017.
 */

/**
 * This interface must be implemented in order to use the GameBoard
 */
interface GamePiece {
    /**
     * Game pieces must have a create method in addition to a constructor.
     * @return Returns a brand new default game piece
     */
    GamePiece create();
    int getValue();
    boolean makeMove(boolean isPlayer1, int direction);
}

/**
 * This is the TicTacToe GamePiece
 */
class TTT_Piece implements GamePiece {
    private int value;
    TTT_Piece() {
        this(0);
    }
    TTT_Piece(int val) {
        this.value = val;
    }

    @Override
    public int getValue() {
        return value;
    }

    /**
     * Place X or O in the slot occupied by this game piece
     * @param isPlayer1 True for Player 1
     * @return Success or Failure
     */
    public boolean makeMove(boolean isPlayer1, int direction) {
        if (value != 0) {
            System.out.println("Spot already taken!");
            return false;
        } else {
            if (isPlayer1) value = 1;
            else value = 2;
            System.out.println("Good move!");
            return true;
        }
    }
    @Override
    public GamePiece create() {
        return new TTT_Piece();
    }
    @Override
    public String toString() {
        String st = null;
        switch(value)
        {
            case 0:
                st = "   ";
                break;
            case 1:
                st =  " X ";
                break;
            case 2:
                st = " O ";
                break;
            default:
                assert(false); //TODO: add proper error handling
        }
        return st;
    }
}

/**
 * The GameBoard holds all the pieces needed for the game.
 * @param <K>
 */
class GameBoard <K> {
    K[][] gameLayout; //Hold the current state of the board
    /**
     * Used to build an n by n 2D game board
     * @param n the size of the board
     * @param samplePiece needs a single game piece instance as a template
     */
    GameBoard(int n, GamePiece samplePiece) {
        assert (n > 0); //TODO: add proper exception handling
        gameLayout = (K[][]) new Object[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gameLayout[i][j] = (K) samplePiece.create();
            }
        }
    }
    public boolean outOfBounds(int row, int col){
        return row < 0 || col < 0 || row >= gameLayout.length || col >= gameLayout.length;
    }
    public int getValue(int row, int col) {
        if(outOfBounds(row,col)) {return -1;}
        else {
            GamePiece currentPiece = (GamePiece) gameLayout[row][col];
            return currentPiece.getValue();
        }
    }
    public int getSize() {
        return gameLayout.length;
    }
    /**
     * Use this function to move a piece on the game board
     * @param isPlayer1 switch for player one or two
     * @param row col on which to put your piece
     * @param col row on which to put your piece
     * @return whether the move was successful
     */
    boolean makeMove(boolean isPlayer1, int row, int col) {
        if (outOfBounds(row, col)) {
            System.out.println("Out of bounds!"); //TODO: Auto extend for truly infinite field
            return false;
        } else {
            GamePiece currentPiece = (GamePiece) gameLayout[row][col];
            return currentPiece.makeMove(isPlayer1, 0);
        }
    }
    /**
     * Converts a certain row to a string
     * @param n which row to convert
     * @return a string made up of that row
     */
    private String rowToString(int n) {
        String row = gameLayout[n][0].toString();
        for (int i = 1; i < gameLayout.length; i++) {
            row = row + "|" + gameLayout[n][i].toString();
        }
        return row + "\n";
    }
    /**
     * Create a dashed line of the right length
     * @return dashed line string
     */
    private String rowSeparator() {
        String separator = "---";
        for (int i = 1; i < gameLayout.length; i++) {
            separator = separator + "----";
        }
        return separator + "\n";
    }
    @Override
    public String toString() {
        String board = rowToString(0);
        for (int i = 1; i < gameLayout.length; i++) {
            board = board + rowSeparator() + rowToString(i);
        }
        return board;
    }
    public static void main(String[] args) {
        GameBoard<TTT_Piece> board = new GameBoard<>(5, new TTT_Piece(0));
        System.out.println(board);
        board.makeMove(true, 3, 3);
        System.out.print(board);

    }
}
