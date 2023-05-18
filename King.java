public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
//    Not powerful but most important, it can move in any direction but only one spot at a time
//    If player’s king is captured, they lose
    public boolean isMoveLegal(Board board, int endRows, int endCols) {
        // check if path is clear or blocked
        if (board.verifySourceAndDestination(this.row, this.col, endRows, endCols, isBlack)) {
            // check if king is only moving one spot in any direction
          //  if ((this.row - endRows == 1) || (this.col - endCols == 1)) {
                return board.verifyAdjacent(this.row, this.col, endRows, endCols);
          //  }
        }
        return false;
    }
}
