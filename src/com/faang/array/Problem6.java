package com.faang.array;

import java.util.Scanner;

public class Problem6 {
    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);


            int buildings = sc.nextInt();
            int total[]  = new int[buildings];
            for(int b=0; b< buildings; b++){
                total[b] = sc.nextInt();
            }
            System.out.println(solution(total));

    }

    public static int solution(int arr[]){
        int left =arr[0],count =1;
        for(int i=1; i<arr.length; i++){
            if(left < arr[i]){
                left = arr[i];
                count++;
                System.out.print(left+" , ");
            }
        }
        System.out.println();
        return count;
    }
}
