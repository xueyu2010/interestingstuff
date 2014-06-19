package leetcode;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: xyu
 * Date: 6/7/14
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 *
 *  Given an input string, reverse the string word by word.

    For example,
    Given s = "the sky is blue",
    return "blue is sky the".
 */
public class ReverseWords {
    /**
     * Solution 1 : recursively do it
     * @param s
     * @return
     */
    public String reverseWords(String s){
       String[] words = s.split(" ");
        List<String> wordsCollection = new LinkedList<String>();
        for(String w: words)
        {
           if(w.length() != 0 ) wordsCollection.add(w);
        }
       LinkedList<String> stringList = new LinkedList<String>(wordsCollection);
       List<String> results =  reverseWordsRecu(new ArrayList<String>(), stringList);

        System.out.println("results: " + makeString(results));


       return makeString(results);

    }

    private List<String> reverseWordsRecu(List<String> results, List<String> subList){
      if(subList.size() == 0 ) return results;
      if (subList.size() == 1 )
      {
          results.add(subList.get(0));
          return results;
      }
      // remove the last word

      int currentSize = subList.size();
      results.add(subList.get(currentSize  - 1));
      subList.remove(currentSize - 1);
      return reverseWordsRecu( results, subList);

    }

    /**
     *
     * Solution 2: Final good solution
     *
     * Explain: using exsiting stack data structure to realize recursion
     * @param s input string
     *
     * edge case: "" , "  a   b "
     *
     * @return
     */
    public String reverseWordsByStack(String s){

        // edge case
        if(s == null || s.trim().length() == 0 ) return "";



        String[] words = s.split(" ");
        Stack<String> stack = new Stack<String>();
        for( String w : words)
        {
            if(w.length() != 0)
            stack.push(w.trim());
        }

        String reversedString = mkString(stack);
        System.out.println(" results: "+reversedString);
        return reversedString;

    }

    private String makeString(Collection<String> results)
    {
        StringBuilder sb = new StringBuilder();
        for(String s : results)
        {
            sb.append(s + " ");
        }
        return sb.toString().trim();
    }

    /**
     * Use stack pop to construct the string
     * @param stack
     * @return
     */
    private String mkString(Stack<String> stack)
    {
        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty())
        {

          sb.append(stack.pop() + " ");
        }
       return  sb.toString().trim();
    }


    public static void main(String[] arg)
    {
        String a = "  a   b ";
//        String[] b = a.split(" ");
//        for( String i : b)
//        {
//            String j = i.replace(' ', '#');
//            System.out.println(i);
//        }

        ReverseWords rw = new ReverseWords();
        rw.reverseWordsByStack("the sky is blue");
    }


}
