/**
 * Created by Feiyu on 2015/7/10 0010.
 * Given a binary tree, find its minimum depth.
 * <p/>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 **/
public class MinimumDepthofBinaryTree {
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
     * Wrong Answer,
     * Input: [1,2,3,4,null,null,5]
     * Output: 2
     * Expected: 3
     */
    public class Solution1 {
        public int minDepth(TreeNode root) {
            if (root != null) {
                if (root.left == null && root.right != null) {
                    return 1 + getMinDepth(root.right);
                } else if (root.left != null && root.right == null) {
                    return 1 + getMinDepth(root.left);
                } else
                    return getMinDepth(root);
            } else
                return 0;
        }

        private int getMinDepth(TreeNode node) {
            if (node != null) {
                int left = node.left != null ? getMinDepth(node.left) : 0;
                int right = node.right != null ? getMinDepth(node.right) : 0;
                return left <= right ? 1 + left : 1 + right;
            } else {
                return 0;
            }
        }
    }

    /**
     * Accept,注意是找根节点到最近的叶子节点的距离，不是中间随便一个节点就行的
     */
    public class Solution {
        public int minDepth(TreeNode root) {
            if (root != null) {
                return getMinDepth(root);
            } else
                return 0;
        }

        private int getMinDepth(TreeNode node) {
            if (node != null) {
                if (node.left == null && node.right != null) {
                    return 1 + getMinDepth(node.right);
                } else if (node.left != null && node.right == null) {
                    return 1 + getMinDepth(node.left);
                } else if (node.left == null && node.right == null) {
                    return 1;
                } else {
                    int left = getMinDepth(node.left);
                    int right = getMinDepth(node.right);
                    return left <= right ? 1 + left : 1 + right;
                }
            } else
                return 0;
        }
    }
}
