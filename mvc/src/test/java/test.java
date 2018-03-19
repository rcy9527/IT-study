import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-03-16
 * @Time: 下午 7:36
 * Description:
 **/
@RunWith(JUnit4.class)
public class test {


    @Test
    public void test(){
        int n = 0;
        int[] a = {1,24,7,3,2,555,2};
        List b = twoStacksSort(a);
        System.out.println(b.toString());
    }



    public Integer number(Integer n){
        if( n < 6 || n< 8 ){
            return -1;
        }
        Integer min = n/6 + 1;//最小的袋子数量
        Integer x = 1;//6个苹果的袋子数量
        Integer minX = 0;// n 对 6取余
        Integer minY = 0;// n 对 8取余
        Integer j = 0;

        minX = n % 6;
        minY = n % 8;
        if( minX.equals(0) ){
            if ( minY.equals(0) ){

                return n/8;
            }
            else
            {
                return n /6;
            }
        }
        else if ( minY.equals(0) ){
            return n/8;
        }
        for(;x <= (n/6) ;x++){
            j = (n - 6*x ) % 8;
           if( j.equals(0) && min > (x + j) ){
                   min = (x + (n - 6*x ) / 8);
                   System.out.println( min);
           }
        }
        if(min ==  (n/6 + 1)){
            return -1;
        }
        return min;
    }



    public ArrayList<Integer> twoStacksSort(int[] numbers) {
        Stack<Integer> A =new Stack<Integer>();
        Stack<Integer> B =new Stack<Integer>();
        ArrayList<Integer> list =new ArrayList<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            A.push(numbers[i]);
        }
        while(!A.isEmpty()){
            int temp =A.pop();
            while(!B.isEmpty() && B.peek()>temp){
                A.push(B.pop());
            }
            B.push(temp);
        }
        while (!B.isEmpty()) {
            list.add(B.pop());
        }
        return list;
    }
//
//    public ArrayList<Integer> ds(int[] number){
//        Stack<Integer> A = new Stack<Integer>();
//        Stack<Integer> b = new Stack<>();
//        ArrayList<Integer>
//    }
}