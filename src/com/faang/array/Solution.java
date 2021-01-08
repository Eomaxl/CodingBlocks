package com.faang.array;
import java.util.*;

public class Solution {
    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        List<String> input = new ArrayList<>();
        for(int i=0; i<size; i++){
            String s = sc.nextLine();
            input.add(s);
        }
        int ans = maxLength(input);
        System.out.println(ans);
    }

    public static int maxLength(List<String> arr){
        return maxLength(arr,0,"");
    }

    public static int maxLength(List<String> arr, int i, String ans){
        //base case
        if(i == arr.size()){
            int[] freq = new int[26];
            for(int k=0; k<ans.length(); k++){
                if(freq[ans.charAt(k) - 'a'] == 1){
                    return 0;
                }
                freq[ans.charAt(k) - 'a']++;
            }
            return ans.length();
        }

        //recursive case
        int op1 = Integer.MIN_VALUE, op2 = op1;
        if(ans.length() + arr.get(i).length() <= 26){
            op1 = maxLength(arr, i+1, ans + arr.get(i));
        }
        op2 = maxLength(arr, i+1, ans);
        return Math.max(op1, op2);
    }
}