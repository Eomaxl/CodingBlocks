package com.lecture20.Trie;

import java.util.BitSet;

public class HEClient {
    public static void main (String[] args){
        String str = "abbccda";
        HuffmanEncoder hf = new HuffmanEncoder(str);
        String codedString = hf.encode(str);
        System.out.println(codedString);
        String decodedString = hf.decode(codedString);
        System.out.println(decodedString);

        BitSet bitset = new BitSet(codedString.length());
        int bitCount = 0;
        for(Character c : codedString.toCharArray()){
            if(c.equals('1')){
                bitset.set(bitCount);
            }
            bitCount++;
        }
        byte[] arr = bitset.toByteArray();
        System.out.println("******************************");
        System.out.println(arr.length);
    }
}
