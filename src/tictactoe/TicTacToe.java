/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;
import java.util.*;
/**
 *
 * @author Maggie
 */
public class TicTacToe {

    static ArrayList <Integer> intPlayerPos = new ArrayList <Integer>();
    static ArrayList <Integer> intCpuPos = new ArrayList <Integer>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[][] chrBoardGame = {{' ', ' ', '|', ' ', '|', ' ', ' '},
                                 {'-', '-', '+', '-', '+', '-', '-'},
                                 {' ', ' ', '|', ' ', '|', ' ', ' '},
                                 {'-', '-', '+', '-', '+', '-', '-'},
                                 {' ', ' ', '|', ' ', '|', ' ', ' '}};
        
        mPrintBoardGame(chrBoardGame);
        
        while(true)
        {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter placement (1 - 9) below: ");
        int intPlayerPosition = sc.nextInt();
        while(intPlayerPos.contains(intPlayerPosition) || intCpuPos.contains(intPlayerPos))
        {
            System.out.println("Position taken! Enter a correct Position");
            intPlayerPosition = sc.nextInt();
        }
        
        mPlacePiece(chrBoardGame, intPlayerPosition, "player");
        
        String strResult = mCheckWinner();
        if(strResult.length() > 0)
        {
            System.out.println(strResult);
            break;
        }
        
        Random rndPick = new Random();
        int intCpuPosition = rndPick.nextInt(9)+1;
        while(intPlayerPos.contains(intCpuPosition) || intCpuPos.contains(intCpuPosition))
        {
            intCpuPosition = rndPick.nextInt(9)+1;
        }
        mPlacePiece(chrBoardGame, intCpuPosition, "cpu");
        
        mPrintBoardGame(chrBoardGame);
        
        strResult = mCheckWinner();
        if(strResult.length() > 0)
        {
            System.out.println(strResult);
            break;
        }
        }
    }
    
        public static void mPrintBoardGame(char[][] chrBoardGame)
        {
        for(char[] row : chrBoardGame)
        {
            for(char c : row)
            {
             System.out.print(c + " ");  
            }
            System.out.println();
        }
        }
        
        public static void mPlacePiece(char[][] chrBoardGame, int intPlayerPosition, String strUser)
        {
            char chrSymbol = ' ';
            
            if(strUser.equals("player"))
            {
                chrSymbol = 'X';
                intPlayerPos.add(intPlayerPosition);
            }
            else if(strUser.equals("cpu"))
            {
                chrSymbol = 'O';
                intCpuPos.add(intPlayerPosition);
            }
            
        switch(intPlayerPosition)
        {
            case 1: chrBoardGame[0][1] = chrSymbol;
            break;
            case 2: chrBoardGame[0][3] = chrSymbol;
            break;
            case 3: chrBoardGame[0][5] = chrSymbol;
            break;
            case 4: chrBoardGame[2][1] = chrSymbol;
            break;
            case 5: chrBoardGame[2][3] = chrSymbol;
            break;
            case 6: chrBoardGame[2][5] = chrSymbol;
            break;
            case 7: chrBoardGame[4][1] = chrSymbol;
            break;
            case 8: chrBoardGame[4][3] = chrSymbol;
            break;
            case 9: chrBoardGame[4][5] = chrSymbol;
            break;
            default:
            break;
        }
        }
        
        public static String mCheckWinner()
        {
            List lstTopRow = Arrays.asList(1, 2, 3);
            List lstMiddleRow = Arrays.asList(4, 5, 6);
            List lstBottomRow = Arrays.asList(7, 8, 9);
            List lstLeftColumn = Arrays.asList(1, 4, 7);
            List lstMiddleColumn = Arrays.asList(2, 5, 8);
            List lstRightColumn = Arrays.asList(3, 6, 9);
            List lstCross1 = Arrays.asList(1, 5, 9);
            List lstCross2 = Arrays.asList(7, 5, 3);
            
            List <List> lstWinning = new ArrayList <List>();
            lstWinning.add(lstTopRow);
            lstWinning.add(lstMiddleRow);
            lstWinning.add(lstBottomRow);
            lstWinning.add(lstLeftColumn);
            lstWinning.add(lstMiddleColumn);
            lstWinning.add(lstRightColumn);
            lstWinning.add(lstCross1);
            lstWinning.add(lstCross2);
            
            for(List l : lstWinning)
            {
                if(intPlayerPos.containsAll(l))
                {
                    return "Congratulations you won!";
                }
                else if(intCpuPos.containsAll(l))
                {
                    return "CPU wins! Sorry :(";
                }
            }
            if(intPlayerPos.size() + intCpuPos.size() == 9)
                {
                    return "It's a DRAW!";
                }
            
            return "";
        }
}
