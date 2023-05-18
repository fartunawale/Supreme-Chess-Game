public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
//    Can only move in an L direction
//    Can jump over pieces
//    Knight can’t move to a position which has a friendly piece ? (like against its own court?)
    public boolean isMoveLegal(Board board, int endRows, int endCols) {
        // check if knight is moving to a friendly position or not
        if ((board.getPiece(endRows, endCols) != null) && (board.getPiece(endRows, endCols).getIsBlack() == isBlack)) {
            return false;
        }
        //if (isBlack != board.getPiece(endRows, endCols).getIsBlack()) { // if the pieces are different colors
            if ((Math.abs(this.row - endRows) == 2) && (Math.abs(this.col - endCols) == 1)) {
                return true;
            } else if ((Math.abs(this.col - endCols) == 2) && (Math.abs(this.row - endRows) == 1)) {
                return true;
            }
        //}
        return false; // if the move is not even an L shape
    }
}
