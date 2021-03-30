package sample;

import java.util.Random;

public class Controller
{
    private final int fieldSize = 10;
    private char[][] firstPlayerMyField;
    private char[][] secondPlayerMyField;
    private char[][] firstPlayerShootField;
    private char[][] secondPlayerShootField;

    private final char EMPTY = '*';
    private final char ATTACK = 'X';
    private final char SHIP = 'K';
    private final char MISS = 'O';

    private final int singleShips=10;

    private int aliveFirstPlayer;
    private int killFirstPlayer;
    private int aliveSecondPlayer;
    private int killSecondPlayer;

    private final Random random = new Random();

    private final String WIN_FIRST_PLAYER = "First player wins";
    private final String WIN_SECOND_PLAYER = "First second wins";

    public Controller()
    {
        firstPlayerMyField = new char[fieldSize][fieldSize];
        secondPlayerMyField = new char[fieldSize][fieldSize];


        killFirstPlayer = 0;
        killSecondPlayer = 0;
        aliveFirstPlayer = singleShips;
        aliveSecondPlayer = singleShips;
    }

    public void ClearField()
    {
        for (int i = 0; i < fieldSize; i++)
        {
            for (int j = 0; j < fieldSize; j++)
            {
                firstPlayerMyField[i][j]=EMPTY;
            }
        }
    }

    public void AddShip(int i, int j)
    {
        for (i = 0; i < fieldSize; i++)
        {
            for (j = 0; j < fieldSize; j++)
            {
                if(firstPlayerMyField[i][j]!=SHIP || firstPlayerMyField[i+1][j]!=SHIP || firstPlayerMyField[i][j+1]!=SHIP
                        ||
                        firstPlayerMyField[i-1][j]!=SHIP || firstPlayerMyField[i][j-1]!=SHIP
                        ||
                        firstPlayerMyField[i+1][j+1]!=SHIP || firstPlayerMyField[i-1][j-1]!=SHIP
                        ||
                        firstPlayerMyField[i+1][j-1]!=SHIP || firstPlayerMyField[i-1][j+1]!=SHIP)
                {
                    firstPlayerMyField[i][j]=SHIP;
                }
            }
        }
    }

    public void Shoot(int i, int j)
    {
        for (i = 0; i < fieldSize; i++)
        {
            for (j = 0; j < fieldSize; j++)
            {
                if(firstPlayerMyField[i][j]==SHIP)
                {
                    firstPlayerMyField[i][j]=ATTACK;
                }
                else
                {
                    firstPlayerMyField[i][j]=MISS;
                }
            }
        }
    }

    public String GetFieldInString()
    {
        String output = "";
        for (int i = 0; i < fieldSize; i++)
        {
            for (int j = 0; j < fieldSize; j++)
            {
                output+= firstPlayerMyField[fieldSize][fieldSize];
            }
            output+="\n";
        }
        return output;
    }

    public String GetGameResult()
    {
        if (secondPlayer==0)
        {
            return WIN_FIRST_PLAYER;
        }
        if(firstPlayer==0)
        {
            return WIN_SECOND_PLAYER;
        }
    }


}
