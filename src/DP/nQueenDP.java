package DP;

import java.util.Scanner;

public class nQueenDP {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter No: ");
        int n = sc.nextInt();

        int [][] chess = new int[4][4];
        printNQueens(chess,"",0);
    }

    public static void printNQueens(int [][] chess, String qsf, int row){
        if(row==chess.length){
            System.out.println(qsf);
            return;
        }

        for(int col=0;col<chess.length;col++){
            if(isSafe(chess,row,col)){
                chess[row][col]=1;
                printNQueens(chess,qsf+row+"-"+col+",",row+1);
                chess[row][col]=0;
            }
        }
    }

    public static boolean isSafe(int [][] chess,int row, int col){
        //center
        for(int i=row-1,j=col;i>=0;i--){
            if(chess[i][j]==1) return false;
        }
        //left side
        for(int i=row-1,j=col-1;i>=0 && j>=0; i--,j--){
            if(chess[i][j]==1) return false;
        }
        //right side
        for(int i=row-1,j=col+1;i>=0 && j<chess.length;i--,j++){
            if(chess[i][j]==1) return false;
        }

        return true;
    }
}
