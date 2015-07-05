/**
 * Created by Feiyu on 2015/7/2 0002.
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

 Do not allocate extra space for another array, you must do this in place with constant memory.

 For example,
 Given input array nums = [1,1,2],

 Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

 **/
public class RemoveDuplicatesfromSortedArray {

    /**
     * Accept but Long Time
     */
    public class Solution {
        public int removeDuplicates(int[] nums) {
            int i1=0,i2=0;
            int newlength=nums.length;

            for(;i1<newlength;i1++){
                for(i2=i1+1;i2<newlength;i2++){
                    if(nums[i2]==nums[i1]){
                        int temp = nums[i2];
                        for(int i=i2;i<nums.length-1;i++){
                            nums[i]=nums[i+1];
                        }
                        nums[nums.length-1]=temp;
                        newlength--;
                        i2--;
                    }
                }
            }
            return newlength;
        }
    }

    public static void main(String... args){
        int[] a={1,1,1};
        RemoveDuplicatesfromSortedArray re = new RemoveDuplicatesfromSortedArray();
        re.new Solution().removeDuplicates(a);


    }
}
