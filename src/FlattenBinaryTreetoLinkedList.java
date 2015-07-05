import java.util.Stack;

/**
 * Created by Feiyu on 2015/7/2 0002.
 **/

public class FlattenBinaryTreetoLinkedList {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     *
    */
    public class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
            TreeNode(int x) { val = x; }
    }

    /**
     * Time Out
     */
    public class Solution1 {
        public void flatten(TreeNode root) {
            if(root==null)
                return;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            TreeNode node = root;
            while(!stack.isEmpty()){
                if(node!=root)
                    node.right=stack.peek();
                node=stack.pop();
                if(node.right!=null)
                    stack.push(node.right);
                if(node.left!=null)
                    stack.push(node.left);
            }


        }
    }
}
