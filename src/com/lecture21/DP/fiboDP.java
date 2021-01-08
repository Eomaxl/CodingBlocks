package com.lecture21.DP;

import java.util.Scanner;

public class fiboDP {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number ");
        int num = sc.nextInt();
       // System.out.println("Top Down approach :"+fiboTD(num,new int[num+1]));
        System.out.println("Bottom Up approach :"+fiboBU(num));
    }

    public static int fiboTD(int num,int[] strg){
        if(num == 0 || num == 1) {
            return num;
        }
        if(strg[num] != 0){
            return strg[num];
        }

        int num1 = fiboTD(num-1,strg);
        int num2 = fiboTD(num-2,strg);

        int fn = num1 + num2;

        strg[num]= fn;

        return fn;
    }

    public static int fiboBU(int n){
        // array of size n+1
        int[] strg = new int[n+1];

        //base case value fill
        strg[0] = 0;
        strg[1] = 1;

        // filling up direction
        for(int i=2; i<= n; i++){
            strg[i] = strg[i - 1] + strg[i - 2];
        }

        return strg[n];
    }
}
