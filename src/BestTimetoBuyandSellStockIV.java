/**
 * Created by Feiyu on 2015/7/16.
 **/
public class BestTimetoBuyandSellStockIV {
    /**
     * Wrong Answer :
     * M(1,i,k)=Max{ M(1,j,k-1) + price(j)-price(k) } 1<=j<i 交易（买入卖出）时
     * M(1,i-1,k) 不交易
     * 会多次交易（一次买入，多次卖出），因为j总是大于1，应该大于上次交易的j
     */
    public class Solution1 {
        public int maxProfit(int k, int[] prices) {
            int[][] table = new int[k][prices.length];
            for (int i = 0; i < prices.length; i++) {
                for (int j = 0; j < k; j++) {
                    if (i == 0 || j < 1) {
                        table[j][i] = 0;
                    } else {
                        int noTranction = table[j][i - 1];
                        int doTranction = 0;
                        for (int l = 0; l < i; l++)
                            doTranction = Math.max(doTranction,
                                    ((j - 2 >= 0) ? table[j - 2][i] : 0) + prices[i] - prices[l]);
                        table[j][i] = Math.max(noTranction, doTranction);
                    }
                }
            }
            int result = table[prices.length - 1][k - 1];
            return result;
        }
    }

    /**
     * Out of Memory , 看来答案是O(N)的，怎么改进？
     * M(i,k)=max{ M(j,k-2)+m(i)-m(j) } j买/i卖
     * M(j-1,k)                 不操作
     */
    public class Solution2 {
        public int maxProfit(int k, int[] prices) {
            int[][] table = new int[k + 1][prices.length + 1];
            for (int i = 1; i <= prices.length; i++) {
                for (int j = 1; j <= k; j++) {
                    if (i != 1 && j >= 2) {
                        int noTranction = table[j][i - 1];
                        int doTranction = 0;
                        for (int l = 1; l < i; l++) {
                            int tmp = table[j - 2][l] + prices[i - 1] - prices[l - 1];
                            doTranction = Math.max(doTranction, tmp);
                        }
                        table[j][i] = Math.max(noTranction, doTranction);
                    }
                }
            }
            int result = table[k][prices.length];
            return result;
        }
    }

    /**
     * By HangCun 算法什么意思？
     */
    public class Solution3 {
        public int maxProfit(int k, int[] prices) {
            int[] hold = new int[k];
            int[] release = new int[k];
            for (int i = 0; i != k; i++) {
                hold[i] = Integer.MIN_VALUE;
                release[i] = 0;
            }
            for (int i = 0; i != prices.length; i++) {
                for (int j = 0; j != k; j++) {
                    release[j] = Math.max(release[i], hold[j] + prices[i]);
                    hold[j] = Math.max(hold[j], release[i] - prices[i]);
                }
            }
            return release[k - 1];
        }
    }

    /**
     * Time Out ,控制了k的最大值，但是时间上 O(n^3)还是不行的，应该要控制到 nk
     */
    public class Solution4 {
        public int maxProfit(int k, int[] prices) {
            int maxK = k > 2 * prices.length ? 2 * prices.length : k;

            int[][] table = new int[maxK + 1][prices.length + 1];
            for (int i = 1; i <= prices.length; i++) {
                for (int j = 1; j <= maxK; j++) {
                    if (i != 1 && j >= 2) {
                        int noTranction = table[j][i - 1];
                        int doTranction = 0;
                        for (int l = 1; l < i; l++) {
                            int tmp = table[j - 2][l] + prices[i - 1] - prices[l - 1];
                            doTranction = Math.max(doTranction, tmp);
                        }
                        table[j][i] = Math.max(noTranction, doTranction);
                    }
                }
            }
            int result = table[maxK][prices.length];
            return result;
        }
    }

    public class Solution {
        public int maxProfit(int k, int[] prices) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new BestTimetoBuyandSellStockIV().new Solution();
        solution.maxProfit(100, new int[]{0, 3, 2, 5});
    }
}
