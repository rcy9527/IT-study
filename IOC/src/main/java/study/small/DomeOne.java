package study.small;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2017-12-17
 * @Time: 上午 1:24
 * Description:
 **/
public class DomeOne {

//        public static void main(String args[]){
//
//                int test = 10;
//                int size = 100;
//                int maxNumber = 100;
//                boolean succsed = true;
//                int[] ary;
//                int[] bas;
//                for(int i = 0;i<test;i++){
//                        ary = suiji(size,maxNumber);
//                        bas = copArrays(ary);
//                        maopao(ary);
//                        Arrays.sort(bas);
//                        System.out.println(i);
//                        System.out.println(Arrays.toString(ary));
//                        System.out.println(Arrays.toString(bas));
//
//                        if(!isEquls(ary,bas)){
//                                succsed = false;
//                                break;
//                        }
//                }
//                System.out.println(succsed);
//
//        }
//
//        public static int[] maopao(int ary[]){
//                for(int i = ary.length - 1;i > 0;i--){
//                        for(int k = 0;k < i ;k++){
//                                if(ary[k] > ary[k+1]){
//                                        maopaoNumber(ary,k);
//                                }
//                        }
//                }
//                return ary;
//        }
//
//        public static void maopaoNumber(int ary[] , int k){
//                ary[k] = ary[k] ^ ary[k+1];
//                ary[k+1] = ary[k] ^ ary[k+1];
//                ary[k] = ary[k] ^ ary[k+1];
//        }
//
//
//        public static int[] suiji(int maxSize,int maxNumber){
//                int[] ary = new int [(int)(Math.random()*(maxSize+1))];
//                for(int i = 0;i<ary.length;i++){
//                        ary[i] = (int)(Math.random()*(maxNumber+1))-(int)(Math.random()*maxNumber);
//                }
//
//                return ary;
//        }
//
//
//        public static int[] copArrays(int ary[]){
//
//                if(ary == null){
//                        return null;
//                }
//
//                int[] bas = new int[ary.length];
//                for(int i=0;i<ary.length ;i++){
//                        bas[i] = ary[i];
//                }
//
//                return bas;
//        }
//
//        private static boolean isEquls(int ary[],int bas[]){
//                if((ary == null && bas !=null) || (ary != null && bas == null)){
//                        return false;
//                }
//
//                if (ary==null&& bas==null){
//                        return true;
//                }
//
//                if(ary.length!=bas.length){
//                        return false;
//                }
//                for(int i = 0;i<bas.length;i++){
//                        if(ary[i] != bas[i]){
//                                return false;
//                        }
//                }
//                return true;
//        }
}