/**
 * Created by Feiyu on 2015/7/10 0010.
 **/
public class MaximumDepthofBinaryTree {
    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Accept，和求最小depth之前的代码一致，因为是求最大，所以也能通过
     */
    public class Solution {
        public int maxDepth(TreeNode root) {
            return getMaxDepth(root);
        }

        private int getMaxDepth(TreeNode node) {
            if (node != null) {
                int left = node.left != null ? getMaxDepth(node.left) : 0;
                int right = node.right != null ? getMaxDepth(node.right) : 0;
                return left >= right ? 1 + left : 1 + right;
            } else {
                return 0;
            }
        }
    }
}
