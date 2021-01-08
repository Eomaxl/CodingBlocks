package com.lecture21.DP;

import java.util.Scanner;

public class fibonacci {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number :");
        int num = sc.nextInt();
        System.out.println("the nth number in the fibonacci series is :"+fibo(num));
    }

    public static int fibo(int num){
        if(num == 0 || num == 1)
            return num;

        int num1 = fibo(num-1);
        int num2 = fibo(num-2);

        return (num1 + num2);
    }
}
