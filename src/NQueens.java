import java.util.LinkedList;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/4 0004.
 *
 * The n-queens puzzle is the problem of placing n queens on an n¡Án chessboard such that no two queens attack each other.



 Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

 For example,
 There exist two distinct solutions to the 4-queens puzzle:
 [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]


 **/
public class NQueens {
    /**
     * Accept
     */
    public class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> list = new LinkedList<>();
            int[] solution = new int[n];
            for(int i=0;i<n;i++) solution[i]=-1;
            backtrace(0,n,solution,list);
            return list;
        }

        private void backtrace(final int t,
                               final int n,
                               final int[] solution,
                               final List<List<String>> list){
            if(t>=n){
                list.add(getOutputList(solution));
            }else{
                for(int i=0;i<n;i++){
                    solution[t]=i;
                    if(isPosValid(t, solution))
                        backtrace(t+1,n,solution,list);
                }
            }
        }

        private boolean isPosValid(int t, int[] solution) {
            for(int i=0;i<t;i++){
                if(solution[i]==solution[t] ||
                        Math.abs(i-t)==Math.abs(solution[i]-solution[t]))
                    return false;
            }
            return true;
        }

        private List<String> getOutputList(int[] solution) {
            List<String> result = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            int pos = -1;
            for(int i=0;i<solution.length;i++){
                pos = solution[i];
                for(int j=0;j<solution.length;j++){
                    sb.append(pos==j?'Q':'.');
                }
                result.add(sb.toString());
                sb.delete(0,solution.length);
            }
            return result;
        }

    }
    public static void main(String[] args){
        Solution solution = new NQueens().new Solution();
        solution.solveNQueens(5);
    }
}
