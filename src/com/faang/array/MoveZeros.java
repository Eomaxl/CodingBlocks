package com.faang.array;

import java.util.Scanner;

public class MoveZeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int arr[] = new int[num];
        for(int i = 0 ; i < num ; i++) {
            arr[i] = sc.nextInt();
        }
        int[] result = moveZeroArr(arr);
        for( int i=0; i< result.length; i++) {
            System.out.print(result[i]+ " ");
        }
    }

    public static void moveZero(int [] arr) {
        int count = 0;
        for (int i= 0 ; i < arr.length; i++) {
            if(arr[i] == 0) {
                count++;
            } else {
                System.out.print(arr[i]+" ");
            }
        }
        for(int i=0; i < count; i++) {
            System.out.print(0+" ");
        }
    }

    public static int[] moveZeroArr (int [] arr) {
        int i =0 , j =0;
        while( i < arr.length) {
            if(arr[j] != 0 ) {
                j++;
            }
            if( arr[j] == 0 && arr[i] != 0) {
                arr[j] = arr[i];
                arr[i] = 0;
                j++;
            }
            i++;
        }
        return arr;
    }
}
