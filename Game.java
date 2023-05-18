import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board newBoard = new Board();


        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", newBoard);
        System.out.print(newBoard.art());
        System.out.println("\nIf you would like to read the game manual before playing,\ntype 'help' or press any key to begin.");
        System.out.println("\nEnjoy the game!");

        Scanner scan = new Scanner(System.in);
        String manual = scan.nextLine();
        boolean turn = false;   // something to keep track of turns: false is white, true is black
        if (manual.equals("help")){ // Extra feature - Game manual to help players understand the game
            newBoard.getManual();
        }
            while (!newBoard.isGameOver()){ // while loop that checks if game is over
                String[] num;
                int row1 = 0;
                int col1 = 0;
                int row2 = 0;
                int col2 = 0;

                while (turn) { // black's turn
                    System.out.print(newBoard);
                    System.out.println("\nIt's Black's turn to play.\nWhat's your move? format: [start row] [start col] [end row] [end col]");
                    num = scan.nextLine().split(" ");
                    row1 = Integer.parseInt(num[0]);
                    col1 = Integer.parseInt(num[1]);
                    row2 = Integer.parseInt(num[2]);
                    col2 = Integer.parseInt(num[3]);
                    // while move is illegal, keep asking user to make it legal + change turns
                    System.out.println((newBoard.getPiece(row1, col1).getIsBlack()));
                    // System.out.println((newBoard.movePiece(row1, col1, row2, col2)));
                    while ((!newBoard.getPiece(row1, col1).getIsBlack())||(!newBoard.movePiece(row1, col1, row2, col2))){ // if this piece is white instead of black, and it's also false
                        // then keep asking the user to try again until legal
                        System.out.println("Black: That was an illegal move, try again. What's your move? ");
                        num = scan.nextLine().split(" ");
                        row1 = Integer.parseInt(num[0]);
                        col1 = Integer.parseInt(num[1]);
                        row2 = Integer.parseInt(num[2]);
                        col2 = Integer.parseInt(num[3]);
                    }
                    turn = false;
                }
                if (newBoard.isGameOver()) { // checks after black's turn if black is winner, white is checked immediately after first while loop is complete.
                    break;
                }
                while (!turn) { // white's turn
                    System.out.print(newBoard);
                    System.out.println("\nIt's White's turn to play.\nWhat's your move? format: [start row] [start col] [end row] [end col]");
                    num = scan.nextLine().split(" ");
                    row1 = Integer.parseInt(num[0]);
                    col1 = Integer.parseInt(num[1]);
                    row2 = Integer.parseInt(num[2]);
                    col2 = Integer.parseInt(num[3]);
                    //System.out.println("" + row1 + col1 + row2 + col2);
                    // while move is illegal, keep asking user to make it legal + change turns
                    System.out.println((!newBoard.getPiece(row1, col1).getIsBlack()));
                    while ((newBoard.getPiece(row1, col1).getIsBlack())||(!newBoard.movePiece(row1, col1, row2, col2))) { // if this piece is black instead of white, and it's also false
                    // then keep asking the user to try again until legal
                            System.out.println("\nWhite: That was an illegal move, try again. What's your move? ");
                            num = scan.nextLine().split(" ");
                            row1 = Integer.parseInt(num[0]);
                            col1 = Integer.parseInt(num[1]);
                            row2 = Integer.parseInt(num[2]);
                            col2 = Integer.parseInt(num[3]);
                        }
                        turn = true;
                }
            }
        if (turn) { // Game is over, the winner is...
            System.out.println("Congratulations! Black has won the game!");
        } else {
            System.out.println("Congratulations! White has won the game!");
        }
    }
}





