import java.lang.Math;

public class Board {
    // Instance variables
    private Piece[][] board;
    private int nrows;
    private int ncols;
    private Piece boardPiece;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        this.nrows = 8;
        this.ncols = 8;
        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
                board = new Piece[this.nrows][this.ncols];
            }
        }
    }
    // Accessor Methods
    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        this.boardPiece = this.board[row][col];
        return boardPiece;
    }
    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        this.boardPiece = piece;
        this.board[row][col] = this.boardPiece;
    }
    // Game functionality methods
    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if ((board[startRow][startCol] != null)) {
            if (board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
                //setPiece(endRow, endCol, getPiece(startRow, startCol));
                board[endRow][endCol] = board[startRow][startCol];
                board[endRow][endCol].setPosition(endRow, endCol);
                board[startRow][startCol] = null;
                return true;
            }
            board[endRow][endCol].pawnPromotion(endRow, endCol, board[endRow][endCol].getIsBlack());
        }
        return false;
    }
    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int count = 0;
        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < this.ncols; j++) {
                if (board[i][j] != null){
                    if (board[i][j].getCharacter() == '\u265a') {
                        count += 1;
                    }
                    if (board[i][j].getCharacter() == '\u2654') {
                        count += 1;
                    }
                }
            }
        }
        if (count == 2) {
            return false;
        }
        return true;
    }
    public String getManual(){ // Extra feature - Game manual method to help players understand the game
        System.out.println("\n----------------------» Chess Manual »----------------------\n");
        System.out.println("Pawn --»");
        System.out.println("    Moves forward two times the first time, and only once afterwards");
        System.out.println("    Direction: vertically or diagonally if no obstacle is present");
        System.out.println("    If the pawn reaches the other side of the board without being captured,\n    it can be promoted to anything besides to king.");
        System.out.println("    Captures: any opponent pawns");

        System.out.println("\nRook --»");
        System.out.println("    Direction: moves vertically or horizontally in any distance if no obstacle is present");
        System.out.println("    Captures: pawns");

        System.out.println("\nBishop --»");
        System.out.println("    Direction: moves diagonally in any distance if no obstacle is present");
        System.out.println("    Captures: any piece of the opponent");

        System.out.println("\nKnight --»");
        System.out.println("    Can't move to a friendly position");
        System.out.println("    Direction: moves in L shape and can jump over pieces");
        System.out.println("    Captures: any piece of the opponent");

        System.out.println("\nQueen --»");
        System.out.println("    Direction: any distance in any direction");
        System.out.println("    Captures: any piece of the opponent");

        System.out.println("\nKing --»");
        System.out.println("    Direction: can move adjacently one spot at a time");
        System.out.println("    Captures: any piece of the opponent\n");
        System.out.println("----------------------» Chess Manual »----------------------");
        return "";
    }
    public String art() { // this art was inspired and came from a stack overflow post linked in my readme file
        System.out.println("   __  ");
        System.out.println(" _(--)_    WELCOME");
        System.out.println("(_ . _)    TO THIS");
        System.out.println(" / : \\     GAME OF CHESS");
        System.out.println("(_/ \\_)");
        return "";
    }
    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        String s = "";
        System.out.println("\nBoard: ");
        System.out.println("   0 1 2 3 4 5 6 7");
        for (int i = 0; i < this.nrows; i++) {
            s += i + " ";
            for (int j = 0; j < this.ncols; j++) {
                if (this.board[i][j] == null) {
                    s += "|" + '\u2001' + "";
                } else {
                    s += "|" + this.board[i][j] + "";
                }
            }
            s += "|" + "\n";
        }
        return s;
    }
    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        board = new Piece[this.nrows][this.ncols];
    }
    // Movement helper functions
    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - Both contain a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - Destination contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
            // check if starting and ending points are within bounds, if starting point has a piece or not
            if ((startRow <= 8) && (endRow <= 8) && (startCol <= 8) && (endCol <= 8) && (board[startRow][startCol] != null)){
                if (board[endRow][endCol] != null){ // check if end point has a piece
                    if (board[startRow][startCol].getIsBlack() == isBlack){ // check if those pieces are the same color
                        return false; // if all of that is true then source and destination is false
                    }
                }
                return true; // else all other scenarios are true
            }
            return false; // starting point is null or out of bounds
    }
    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        return Math.abs(startRow-endRow) <= 1 && Math.abs(startCol-endCol) <= 1;
    }
    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        // first check if move is horizontal
        if (startRow == endRow) {
            // next check if there are any obstacles between the two positions
                for (int j = Math.min(startCol, endCol) + 1; j < Math.max(startCol, endCol); j++) {
                    if (board[startRow][j] != null) {
                        return false; // no obstacles & within bounds
                    }
                }
            return true;
        }
        return false;
    }
    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        // first check if move is vertical
        if (startCol == endCol) {
            // next check if there are any obstacles between the two positions
            for (int i = Math.min(startRow, endRow) + 1; i < Math.max(startRow, endRow); i++) {
                    if (board[i][startCol] != null) {
                        return false; // no obstacles & within bounds
                    }
            }
            return true;
        }
        return false;
    }
    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        // first check if move is diagonal
        int sum = startRow + startCol;
        int diff = startRow - startCol;

        if (sum == endRow + endCol) {
            // then check if there are any obstacles between the two positions
            // don't check starting point because that's not null // it's max to min like from row 3 to row 0
            for (int i = Math.max(startRow, endRow) - 1 ; i < Math.min(startRow, endRow); i--) {
                    if (board[i][++startCol] != null) {
                        return false;
                    }
            }
        }
        else if (diff == endRow - endCol) {
            // then check if there are any obstacles between the two positions // it's min to max like from row 0 to row 3
            for (int i = Math.min(startRow, endRow) + 1; i < Math.max(startRow, endRow); i++) {
                    if (board[i][++startCol] != null) { // ++startCol increases startCol bf startCol is used
                        return false;
                    }
            }
        }
        return true;
    }
}
