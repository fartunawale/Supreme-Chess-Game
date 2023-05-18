public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;

    public Bishop(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    //    Can move any distance but moves only diagonally
    //    Can only move if it’s path is not blocked
    public boolean isMoveLegal(Board board, int endRows, int endCols){
        // check if path is clear or blocked
        if (board.verifySourceAndDestination(this.row, this.col, endRows, endCols, isBlack)){
            return board.verifyDiagonal(this.row,this.col, endRows, endCols);
        }
        return false;
    }

}
