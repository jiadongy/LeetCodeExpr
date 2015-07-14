/**
 * Created by Feiyu on 2015/7/4 0004.
 * Follow up for N-Queens problem.

 Now, instead outputting board configurations, return the total number of distinct solutions.

 **/


public class NQueensII {
    /**
     * Accept
     */
    public class Solution {
        public int totalNQueens(int n) {
            int[] solution =
                    new int[n];
            for(int i=0;i<n;i++)    solution[i]=-1;
            int row = 0,
                sum = 0;
            while (row>=0) {
                solution[row]++;
                for(int pos=solution[row];pos<=n;pos++){
                    solution[row]=pos;
                    if(isPosValid(row,pos,solution)){
                        break;
                    }
                }
                if(solution[row]<n){
                    if(row==n-1)
                        sum++;
                    else {
                        row++;
                        solution[row]=-1;
                    }
                }else {
                    row--;
                }
            }
            return sum;
        }
        final private boolean isPosValid(int row,int rowpos,int[] solution){
            for (int j = 0; j < row; j++) {
                if (solution[j] == rowpos ||
                        Math.abs(j - row) == Math.abs(solution[j] - rowpos)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args){
        Solution solution = new NQueensII().new Solution();
        int x= solution.totalNQueens(5);
        int y=0;
    }
}
