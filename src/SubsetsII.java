import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/5 0005.
 **/
public class SubsetsII {

    public class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> list = new LinkedList<>();
            if(nums.length==0)
                return list;
            Arrays.sort(nums);
            int[] path = new int[nums.length];
            for(int i=0;i<=Math.pow(2,nums.length)-1;i++){
                parsePath(i, path);
                List<Integer> temp = getList(nums,path);
                boolean isExist = isExistInList(list,temp);
                if(!isExist)
                    list.add(temp);
            }
            return list;
        }
        private boolean isExistInList(List<List<Integer>> lists,List<Integer> list){
            boolean same=false;
            for(List<Integer> l : lists){
                if(l.size()==list.size()){
                    for(int i=0;i<l.size();i++){
                        if(l.get(i)==list.get(i))
                            same=true;
                        else{
                            same=false;
                            break;
                        }

                    }
                }
                if(same)
                    return true;
            }
            return false;
        }
        private void parsePath(int num,int[] path){
            int number=1;
            for(int i=0;i<path.length;i++){
                path[i]=(num & number)==0?0:1;
                number*=2;
            }
        }
        private List<Integer> getList(int[] nums,int[] path){
            List<Integer> list = new LinkedList<>();
            for(int i=0;i<nums.length;i++){
                if(path[i] != 0)
                    list.add(nums[i]);
            }
            return list;
        }
    }

    static public void main(String... atgs){
        Solution solution=new SubsetsII().new Solution();
        solution.subsetsWithDup(new int[]{1, 2, 3});
    }
}
