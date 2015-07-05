/**
 * Created by Feiyu on 2015/7/2 0002.
 * Given a complete binary tree, count the number of nodes.

 Definition of a complete binary tree from Wikipedia:
 In a complete binary tree every level,
 except possibly the last, is completely filled,
 and all nodes in the last level are as far left as possible.
 It can have between 1 and 2h nodes inclusive at the last level h.

 **/
public class CountCompleteTreeNodes {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
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
    public class Solution_Recuisive {
        public int countNodes(TreeNode root) {
            return root!=null?((root.left!=null?countNodes(root.left):0)
                    + (root.right!=null?countNodes(root.right):0) + 1):0;
        }
    }

    /**
     * Time Out
     */
    public class Solution_BinarySearch1 {
        public int countNodes(TreeNode root) {
            if(root==null)
                return 0;
            if(root.left==null && root.right==null)
                return 1;
            int level = 1;

            TreeNode node = root;

            while(node.left != null){
                level++;
                node = node.left;
            }

            int numofnodes = (int) (Math.pow(2,level-1)-1);

            int startId=1,endId=2*level,midId=level;

            boolean start,end,mid;



            if(!isNodeNull(level, startId, root))
                return numofnodes;
            else if(isNodeNull(level, endId, root))
                return numofnodes+2*level;
            else{
                while(startId<endId){
                    start=isNodeNull(level,startId,root);
                    end=isNodeNull(level,endId,root);
                    mid=isNodeNull(level,midId,root);

                    if(start && !mid){
                        if(endId - startId ==1)
                            break;
                        else{
                            endId = midId-1;
                            midId = (startId+endId)/2;
                        }
                    }else if(mid && !end){
                        startId=midId;
                        midId = (startId + endId)/2;
                    }
                }
                return numofnodes + midId;
            }
        }

        private boolean isNodeNull(int level , int i,TreeNode root){
            if(i > level*2 || i <=0)
                return false;
            TreeNode node = root;
            int currentLevel = level -1;
            while(currentLevel >=1 ){
                if(i<=2*currentLevel/2){
                    node=node.left;
                }else{
                    node=node.right;
                }
                currentLevel--;
            }
            return node!=null;
        }
    }
}
