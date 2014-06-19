package leetcode;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: xyu
 * Date: 6/10/14
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 *
 *  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 *  algo: use a stack, if a number, push, if a operator, pop the two number, do the operation.
 *  If meet anything not expected, throw error
 */
public class ReversePolishNotation {

    public int  solution(String[] inputs)
    {
        Stack<Integer> stack = new Stack<Integer>();
        for(String s : inputs)
        {
            try{
                Integer number = toNumber(s);
                if(number !=null)
                    stack.push(number);
                else{
                    int second = stack.pop();
                    int first = stack.pop();
                    Integer result = evaluate(s, first, second);
                    if(result != null)  stack.push(result);
                    else return -1;

                }
            }catch(Exception e){
               System.out.println("Error " + e);
                return -1;
            }
        }

        if(stack.size() == 1)  return Long.valueOf(Math.round(stack.pop())).intValue();
        else return -1;

    }

    private Integer evaluate(String operator, int first, int second)
    {
        if(operator == null || operator.length() != 1) return null;
       switch(operator.toCharArray()[0]){
           case '+' : return first + second;
           case '-': return first - second;
           case '*': return first * second;
           case '/': return first /second;
           default: return null;

       }
    }

    /**
     * return double value of the string if is the number, else, return null
     * @param number
     * @return
     */
    private Integer toNumber(String number)
    {
        try{
            return Double.valueOf(number).intValue();
        }
        catch(NumberFormatException ex)
        {
            return null;
        }
    }

    public static void main(String[] arg){
       String[] inputs = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        String[] inputs2 = {"4", "13", "5", "/", "+"};
        ReversePolishNotation rpn = new ReversePolishNotation();
        System.out.println(rpn.solution(inputs2)) ;
    }
}
