public class Rook {
    private int row;
    private int col;
    private boolean isBlack;

    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
//    Can move horizontally & vertically if no piece is blocking its path
//    Can capture pawns
    public boolean isMoveLegal(Board board, int endRows, int endCols) {
        // check if piece is blocking the path
        if (board.verifySourceAndDestination(this.row, this.col, endRows, endCols, isBlack)) {
            return board.verifyHorizontal(this.row, this.col, endRows, endCols)
                    || board.verifyVertical(this.row, this.col, endRows, endCols);
        }
        return false;
    }
}
