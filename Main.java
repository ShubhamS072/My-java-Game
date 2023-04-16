import java.util.*;

class ticTacToe{
    static char [][] board;

    public ticTacToe()
    {
        board = new char [3][3];
        boardInitialisation();
    }
    void boardInitialisation(){
        for (int i=0; i< board.length; i++){
            for (int j =0; j< board[i].length; j++){
                board[i][j] = ' ';
            }
        }
    }
    static void dispBoard(){
        System.out.println(" ------------- ");
        for (int i=0; i< board.length; i++){
            System.out.print(" | ");
            for (int j =0; j< board[i].length; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println(" ------------- ");
        }
    }

    static boolean boardEmpty(){
        for (int i = 0; i<=2; i++){
            for (int j = 0; j<= 2; j++){
                if (board[i][j] == ' '){
                    return true;
                }
            }
        }
        return false;
    }

    static void enterMark(int row, int col, char mark){
        if(row >=0 && row<= 2 && col >=0  && col<= 2){
            board[row][col] = mark;
        }else{
            System.out.println("invalid placement coordinates");
        }
    }

    static boolean checkColWin(){
        for (int j=0; j<= 2; j++){
            if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]){
                return true;
            }
        }return false;
    }
    static boolean checkRowWin(){
        for (int i=0; i<= 2; i++){
            if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return true;
            }
        }return false;
    }
    static boolean checkDiagonalWin(){
        if(board[1][1] != ' '&& board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            return true;
        }
        if(board[1][1] != ' '&& board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            return true;
        }
        return false;
    }
}

abstract class Player{
    String name;
    char mark;
    abstract void makeAMove();
    boolean isValidMove(int row, int col){
        if(row >= 0 && row <=2 & col >= 0 && col <= 2){
            if (ticTacToe.board[row][col] == ' '){
                return true;
            }
        }
        return false;
    }
}

class humanPlayer extends Player{

    humanPlayer(String name, char mark){
        this.name = name;
        this.mark = mark;
    }
    void makeAMove(){
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("Enter row & column number to place your mark");
            row = sc.nextInt();
            col = sc.nextInt();
        }while (!isValidMove(row, col));

        ticTacToe.enterMark(row, col, mark);
    }
}
class AIPlayer extends Player{

    AIPlayer(String name, char mark){
        this.name = name;
        this.mark = mark;
    }


    void makeAMove(){
        Scanner sc = new Scanner(System.in);
        int row;
        int col;

        do {


                Random r = new Random();
                row = r.nextInt(3);
                col = r.nextInt(3);



        }while (!isValidMove(row, col));

        ticTacToe.enterMark(row, col, mark);
    }
}



public class Main {
    public static void main(String[] args) {
        ticTacToe game = new ticTacToe();
        Scanner sc = new Scanner(System.in);

        System.out.println("Press 1 for single player & Press 2 for multiplayer");
        int playerMode = sc.nextInt();

        if(playerMode == 1){


            System.out.println("PLease enter Player 1 name who'll use 'X' ");
            String playerOne = sc.next();

            humanPlayer p1 = new humanPlayer(playerOne, 'X');
            AIPlayer p2 = new AIPlayer("AI", '0');

            Player cp;
            cp = p1;

            while (true){
                System.out.println(cp.name + "'s turn");
                cp.makeAMove();
                ticTacToe.dispBoard();

                if (ticTacToe.checkColWin() || ticTacToe.checkRowWin() || ticTacToe.checkDiagonalWin()) {
                    System.out.println(cp.name + " has won this game");
                    break;
                } else if (!ticTacToe.boardEmpty()) {
                    System.out.println("Game Draw");
                    break;
                } else {
                    if (cp == p1) {
                        cp = p2;
                    } else {
                        cp = p1;
                    }
                }
            }



        } else if (playerMode == 2) {


            System.out.println("PLease enter Player 1 name who'll use 'X' ");
            String playerOne = sc.next();
            System.out.println("PLease enter Player 2 name who'll use '0' ");
            String playerTwo = sc.next();

            humanPlayer p1 = new humanPlayer(playerOne, 'X');
            humanPlayer p2 = new humanPlayer(playerTwo, '0');

            Player cp;
            cp = p1;

            while (true){
                System.out.println(cp.name + "'s turn");
                cp.makeAMove();
                ticTacToe.dispBoard();

                if (ticTacToe.checkColWin() || ticTacToe.checkRowWin() || ticTacToe.checkDiagonalWin()) {
                    System.out.println(cp.name + " has won this game");
                    break;
                } else if (!ticTacToe.boardEmpty()) {
                    System.out.println("Game Draw");
                    break;
                } else {
                    if (cp == p1) {
                        cp = p2;
                    } else {
                        cp = p1;
                    }
                }
            }

        }else {
            System.out.println("wrong input, Run the code again");
        }
    }
}