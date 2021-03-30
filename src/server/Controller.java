package server;

import java.util.Random;

public class Controller {
    private final int fieldSize = 10;
    private char[][] firstPlayerMyField;
    private char[][] secondPlayerMyField;
    private char[][] firstPlayerShootField;
    private char[][] secondPlayerShootField;

    private final char EMPTY = '*';
    private final char ATTACK = 'X';
    private final char SHIP = 'K';
    private final char MISS = 'O';

    private final int singleShips = 10;

    private int aliveFirstPlayer;
    private int killFirstPlayer;
    private int aliveSecondPlayer;
    private int killSecondPlayer;

    private final Random random = new Random();

    private final String WIN_FIRST_PLAYER = "First player wins";
    private final String WIN_SECOND_PLAYER = "First second wins";

    public Controller() {
        firstPlayerMyField = new char[fieldSize][fieldSize];
        secondPlayerMyField = new char[fieldSize][fieldSize];
        firstPlayerShootField = new char[fieldSize][fieldSize];
        secondPlayerShootField = new char[fieldSize][fieldSize];

        ClearField(firstPlayerMyField);
        ClearField(firstPlayerShootField);
        ClearField(secondPlayerMyField);
        ClearField(secondPlayerShootField);

        PlaceRandomShips(firstPlayerMyField);
        PlaceRandomShips(secondPlayerMyField);

        killFirstPlayer = 0;
        killSecondPlayer = 0;
        aliveFirstPlayer = singleShips;
        aliveSecondPlayer = singleShips;
    }

    private void ClearField(char[][] field) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = EMPTY;
            }
        }
    }

    private void PlaceRandomShips(char[][] field) {
        for (int k = 0; k < singleShips; k++) {
            int i;
            int j;

            do {
                i = random.nextInt(fieldSize);
                j = random.nextInt(fieldSize);
            } while (field[i][j] != EMPTY);

            field[i][j] = SHIP;
        }
    }

    private boolean ShootToField(int i, int j, char[][] myField, char[][] shootField) {
        if (i < 0 || i > fieldSize - 1 || j < 0 || j > fieldSize - 1) {
            return false;
        }
        if (myField[i][j] == SHIP || myField[i][j] == MISS) {
            return false;
        }

        if (myField[i][j] == SHIP) {
            myField[i][j] = ATTACK;
            shootField[i][j] = ATTACK;
            return true;
        }
        if (myField[i][j] == EMPTY) {
            myField[i][j] = MISS;
            shootField[i][j] = MISS;
            return true;
        }

        return false;
    }

    public boolean FirstPlayerShootToSecondPlayer(int i, int j){
        return ShootToField(i,j,secondPlayerMyField,firstPlayerShootField);
    }

    public boolean SecondPlayerShootToFirstPlayer(int i, int j){
        return ShootToField(i,j,firstPlayerMyField,secondPlayerShootField);
    }

    public String GetFieldInString() {
        String output = "";
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                output += firstPlayerMyField[fieldSize][fieldSize];
            }
            output += "\n";
        }
        return output;
    }

    public String GetGameResult() {
        if (secondPlayer == 0) {
            return WIN_FIRST_PLAYER;
        }
        if (firstPlayer == 0) {
            return WIN_SECOND_PLAYER;
        }
    }


}
