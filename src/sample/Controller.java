package sample;

public class Controller
{
    private int fieldSize;
    private char[][] field;

    private final char EMPTY = '*';
    private final char ATTACK = 'X';
    private final char SHIP = 'K';
    private final char MISS = 'O';

    private int singleShips=4;
    private int doubleShips=3;
    private int threeShips=2;
    private int fourShips=1;

    private int firstPlayer = 20;
    private int secondPlayer = 20;

    private final String WIN_FIRST_PLAYER = "First player wins";
    private final String WIN_SECOND_PLAYER = "First second wins";

    public Controller()
    {
        fieldSize = 10;
        field = new char[fieldSize][fieldSize];
    }

    public void ClearField()
    {
        for (int i = 0; i < fieldSize; i++)
        {
            for (int j = 0; j < fieldSize; j++)
            {
                field[i][j]=EMPTY;
            }
        }
    }

    public void AddShip(int i, int j)
    {
        for (i = 0; i < fieldSize; i++)
        {
            for (j = 0; j < fieldSize; j++)
            {
                if(field[i][j]!=SHIP || field[i+1][j]!=SHIP || field[i][j+1]!=SHIP
                        ||
                        field[i-1][j]!=SHIP || field[i][j-1]!=SHIP
                        ||
                        field[i+1][j+1]!=SHIP || field[i-1][j-1]!=SHIP
                        ||
                        field[i+1][j-1]!=SHIP || field[i-1][j+1]!=SHIP)
                {
                    field[i][j]=SHIP;
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
                if(field[i][j]==SHIP)
                {
                    field[i][j]=ATTACK;
                }
                else
                {
                    field[i][j]=MISS;
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
                output+=field[fieldSize][fieldSize];
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
        return ;
    }


}
