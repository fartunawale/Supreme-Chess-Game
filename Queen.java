public class Queen {
    private int row;
    private int col;
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRows, int endCols){
        // check if path is clear or blocked
        if (board.verifySourceAndDestination(this.row, this.col, endRows, endCols, isBlack)){
            return board.verifyHorizontal(this.row,this.col, endRows, endCols) || board.verifyVertical(this.row,this.col, endRows, endCols)
                    || board.verifyDiagonal(this.row,this.col, endRows, endCols);
        }
        return false;
    }


}
