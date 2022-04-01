package com.company;

public class Main {

    public static class SkeletonProgram {

        //initiates board and players
        char board[][];
        Player playerOne;
        Player playerTwo;

        //sets console as controller
        Console console = new Console();

        public SkeletonProgram() {
            //prints leaderboard
            console.printLeaderBoard();

            //creates 4x4 board??
            board = new char[4][4];

            //gets player info
            playerOne = new Player(console.readLine("What is the name of player one? "));
            playerTwo = new Player(console.readLine("What is the name of player two? "));
            playerOne.setScore(0);
            playerTwo.setScore(0);

            //gets the symbol choice of the first player
            do {
                playerOne.setSymbol(console.readChar((playerOne.getName()
                        + " what symbol do you wish to use X or O? ")));
                if (playerOne.getSymbol() != 'X' && playerOne.getSymbol() != 'O') {
                    //makes sure symbol is correct
                    console.println("Symbol to play must be uppercase X or O");
                }
            } while (playerOne.getSymbol() != 'X' && playerOne.getSymbol() != 'O');

            //assigns the other player the other symbol
            if (playerTwo.getSymbol() == 'X') {
                playerTwo.setSymbol('O');
            } else {
                playerTwo.setSymbol('X');
            }

            //always starts with x
            char startSymbol = 'X';

            //not sure what this is
            char replay;

            do {
                //initialises game
                int noOfMoves = 0;
                boolean gameHasBeenDrawn = false;
                boolean gameHasBeenWon = false;

                //clears the board
                clearBoard();

                //not sure what this is
                console.println();

                //prints the initial board
                displayBoard();

                //gets the starting player and begins the game - always starts on x
                if (startSymbol == playerOne.getSymbol()) {
                    console.println(playerOne.getName() + " starts playing " + startSymbol);
                } else {
                    console.println(playerTwo.getName() + " starts playing " + startSymbol);
                }

                console.println();

                //sets the current symbol to the start as it is first loop
                char currentSymbol = startSymbol;
                //initialises more variables
                boolean validMove;
                Coordinate coordinate;
                do {

                    do {

                        //gets player move
                        coordinate = getMoveCoordinates();

                        //checks if it is a valid move - but does not do overwrite checks
                        validMove = checkValidMove(coordinate, board);
                        if (!validMove) {
                            console.println("Coordinates invalid, please try again");
                        }
                    } while (!validMove);

                    //gets current symbol from most recent thing placed on board - doesnt just switch it
                    board[coordinate.getX()][coordinate.getY()] = currentSymbol;

                    //updates board
                    displayBoard();

                    //checks if game has been won - does not check diagonally
                    gameHasBeenWon = checkXOrOHasWon();

                    //increments number of moves
                    noOfMoves++;

                    if (!gameHasBeenWon) {

                        //will always draw the game after 9 moves
                        if (noOfMoves == 9) {
                            gameHasBeenDrawn = true;
                        } else {

                            //switches current symbol
                            if (currentSymbol == 'X') {
                                currentSymbol = 'O';
                            } else {
                                currentSymbol = 'X';
                            }

                        }
                    }

                    //executes while game still running
                } while (!gameHasBeenWon && !gameHasBeenDrawn);


                //increases the score of the winning player, or prints that it is a draw
                if (gameHasBeenWon) {
                    if (playerOne.getSymbol() == currentSymbol) {
                        console.println(playerOne.getName() + " congratulations you win!");
                        playerOne.addScore();
                    } else {
                        console.println(playerTwo.getName() + " congratulations you win!");
                        playerTwo.addScore();
                    }
                } else {
                    console.println("A draw this time!");
                }

                //prints score
                console.println("\n" + playerOne.getName() + " your score is: " + String.valueOf(playerOne.getScore()));
                console.println(playerTwo.getName() + " your score is: " + String.valueOf(playerTwo.getScore()));
                console.println();

                //swaps the start symbol for the next game
                if (startSymbol == playerOne.getSymbol()) {
                    startSymbol = playerTwo.getSymbol();
                } else {
                    startSymbol = playerOne.getSymbol();
                }
                replay = console.readChar("\n Another game Y/N? ");

                //could just use replay.upper
            } while (replay != 'N' && replay != 'n');

            //prints all player data into the file - does not all need to be there
            console.writeFile(playerOne.toString());
            console.writeFile(playerTwo.toString());
        }

        void displayBoard() {
            int row;
            int column;

            //was incorrectly formatted- fixed by adding a space

            console.println("  | 1 2 3 ");
            console.println("--+-------");
            for (row = 1; row <= 3; row++) {
                console.write(row + " | ");
                for (column = 1; column <= 3; column++) {
                    console.write(board[column][row] + " ");
                }
                console.println();
            }
        }

        //makes board blank chars
        void clearBoard() {
            int row;
            int column;
            for (row = 1; row <= 3; row++) {
                for (column = 1; column <= 3; column++) {
                    board[column][row] = ' ';
                }
            }
        }

        //gets move coordinates - would be easy to check them in this method instead of having another one
        Coordinate getMoveCoordinates() {
            Coordinate coordinate = new Coordinate(console.readInteger("Enter x Coordinate: "), console.readInteger("Enter y Coordinate: "));
            return coordinate;
        }

        //issue with overwriting fixed by adding a check to see if the square is empty
        boolean checkValidMove(Coordinate coordinate, char[][] board) {
            boolean validMove;
            validMove = true;
            if (coordinate.getX() < 1 || coordinate.getX() > 3) {
                validMove = false;
            } else if (board[coordinate.getX()][coordinate.getY()] != ' ')
                validMove = false;
            return validMove;
        }

        //fixed to check if player has won diagonally - all checks are pretty inefficient though
        boolean checkXOrOHasWon() {
            boolean xOrOHasWon;
            int row;
            int column;
            xOrOHasWon = false;

            for (column = 1; column <= 3; column++) {
                if (board[column][1] == board[column][2]
                        && board[column][2] == board[column][3]
                        && board[column][2] != ' ') {
                    xOrOHasWon = true;
                }
            }
            for (row = 1; row <= 3; row++) {
                if (board[1][row] == board[2][row]
                        && board[2][row] == board[3][row]
                        && board[2][row] != ' ') {
                    xOrOHasWon = true;
                }
            }

            if (board[1][1] == board[2][2]
                    && board[2][2] == board[3][3]
                    && board[2][2] != ' ') {
                xOrOHasWon = true;
            }

            if (board[1][3] == board[2][2]
                    && board[2][2] == board[3][1]
                    && board[2][2] != ' ') {
                xOrOHasWon = true;
            }

            return xOrOHasWon;
        }
    }

    public static void main(String[] args) {
        new SkeletonProgram();
    }
}