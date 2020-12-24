package com.example.lib;

import java.io.File;
import java.util.Arrays;

public class MyClass {
    public static void main(String[] args) {
//        System.out.println("===get==>"+getRandom(13,10));
//        System.out.println("===get11==>"+Math.ceil(Math.sqrt(8)));
//        System.out.println("===get112==>"+(158>(158-0.5f)));
//        System.out.println("===get113==>"+(new File("http://wwww.baidu.com").isFile()));
        int [] a={3,2,4};
        System.out.println(Arrays.toString(to(a,6)));

    }
    public static int  getRandom(int max,int min){
        return  (int) (Math.random()*(max-min)+min);
    }
    public static int[] to(int[] nums,int target){
        for (int i=0;i<nums.length;i++ ){
            int a1=nums[i];
            for (int j=0;j<nums.length;j++ ){
                int a2=nums[j];
                if(i!=j){
                    if(a1+a2==target){
                        return new int[]{i,j};
                    }
                }

            }
        }
        return null;
    }

}