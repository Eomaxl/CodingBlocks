package com.faang.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class OneHappyNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number :");
        int num = sc.nextInt();
        System.out.println(isOneHappyNumber(num));
    }

    public static int update(int num) {
        int updatedNum = 0;
        while ( num > 0 ) {
            int digit = num % 10;
            updatedNum = updatedNum + digit * digit;
            num = num /10;
        }
        return updatedNum;
    }

    public static boolean check(Set<Integer> set, int num) {
        if (num == 1) {
            return true;
        }

        if (set.contains(num)) {
            return false;
        }

        set.add(num);
        num = update(num);
        return check(set, num);
    }

    public static boolean isOneHappyNumber(int num){
        Set<Integer> set = new HashSet<>();
        return check(set, num);
    }
}
