/**
 * Created by Feiyu on 2015/7/10 0010.
 **/
public class ValidateBinarySearchTree {
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
     * Accept,注意：子树的取值范围必须有上下限，注意int的最大最小值溢出的问题（用long解决，有其他方法吗？）
     * testcase：[10,5,15,null,null,-2147483648,20]，[2147483644,-2147483648,2147483646,null,null,2147483645,2147483647]
     */
    public class Solution {
        public boolean isValidBST(TreeNode root) {
            if (root != null) {
                return ((root.left == null) || ((root.left.val < root.val) && isValid(Long.MIN_VALUE, root.val, root.left)))
                        && ((root.right == null) || ((root.right.val > root.val) && isValid(root.val, Long.MAX_VALUE, root.right)));
            } else
                return true;
        }

        private boolean isValid(long minBound, long maxBound, TreeNode node) {
            if (node != null) {

                return ((node.left == null) || (node.val > node.left.val && (node.left.val > minBound) && isValid(minBound, node.val, node.left))) &&
                        ((node.right == null) || (node.val < node.right.val && (node.right.val < maxBound) && isValid(node.val, maxBound, node.right)));

            } else
                return true;
        }
    }

    public static void main(String... a) {
        Solution solution = new ValidateBinarySearchTree().new Solution();
        TreeNode root = new ValidateBinarySearchTree().new TreeNode(2147483644);
        root.left = new ValidateBinarySearchTree().new TreeNode(-2147483648);
        root.right = new ValidateBinarySearchTree().new TreeNode(2147483646);
        root.right.left = new ValidateBinarySearchTree().new TreeNode(2147483645);
        root.right.right = new ValidateBinarySearchTree().new TreeNode(2147483647);
        solution.isValidBST(root);
    }
}
