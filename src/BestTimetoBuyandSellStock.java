/**
 * Created by Feiyu on 2015/7/17.
 **/
public class BestTimetoBuyandSellStock {
    /**
     * Accept , 从4抄过来
     */
    public class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length == 0) return 0;
            int maxK = 1;
            int[][] local = new int[maxK + 1][prices.length],
                    global = new int[maxK + 1][prices.length];
            for (int j = 1; j <= maxK; j++) {
                for (int i = 1; i < prices.length; i++) {
                    int diff = prices[i] - prices[i - 1];
                    local[j][i] = Math.max(global[j - 1][i - 1], local[j][i - 1] + diff);
                    global[j][i] = Math.max(global[j][i - 1], local[j][i]);
                }
            }
            int res = global[maxK][prices.length - 1];
            return res;
        }
    }
}
