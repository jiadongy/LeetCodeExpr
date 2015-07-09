/**
 * Created by Feiyu on 2015/7/8 0008.
 * Determine whether an integer is a palindrome. Do this without extra space.
 * <p/>
 * click to show spoilers.
 * <p/>
 * Some hints:
 * Could negative integers be palindromes? (ie, -1)
 * <p/>
 * If you are thinking of converting the integer to string, note the restriction of using extra space.
 * <p/>
 * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
 * <p/>
 * There is a more generic way of solving this problem.
 **/
public class PalindromeNumber {
    /**
     * Accept
     */
    public class Solution {
        public boolean isPalindrome(int x) {
            //x=Math.abs(x==Integer.MIN_VALUE?Integer.MAX_VALUE:Math.abs(x));
            if (x < 0)
                return false;
            if (x <= 9)
                return true;
            int i = (int) Math.pow(10, (int) Math.log10(x)), j = 10;

            while (i > 0) {
                if (x / i == x % j) {
                    x = x % i / j;//如果有0就不适用了，所以while条件不能用x>=10判断
                } else
                    return false;
                i /= 100;//不是除10！
            }
            return true;
        }
    }

    public static void main(String... a) {
        Solution solution = new PalindromeNumber().new Solution();
        boolean x = solution.isPalindrome(13231);
        boolean y = false;
    }
}
