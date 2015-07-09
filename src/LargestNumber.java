import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/9 0009.
 **/
public class LargestNumber {
    /**
     * Accept
     */
    public class Solution {
        public String largestNumber(int[] nums) {
            List<String> items = new ArrayList<>();
            for (int num : nums) items.add(Integer.toString(num));
            Collections.sort(items, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return -1 * (o1 + o2).compareTo(o2 + o1);//大小不是单纯的字典序；应为逆序
                }
            });
            StringBuilder sb = new StringBuilder();
            boolean biggerThanZero = false;
            for (int i = 0; i < items.size(); i++) {
                if (biggerThanZero || !items.get(i).equals("0")) {//对多个0的边界处理
                    biggerThanZero = true;
                    sb.append(items.get(i));
                } else if (i == items.size() - 1)
                    sb.append(items.get(i));
            }
            return sb.toString();

        }
    }

    public static void main(String... a) {
        Solution solution = new LargestNumber().new Solution();
        solution.largestNumber(new int[]{0, 0});
    }
}
