import java.util.LinkedList;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/5 0005.
 **/
public class RestoreIPAddresses {

    /**
     * Accept,最关键的就是约束条件函数
     */
    public class Solution {
        public List<String> restoreIpAddresses(String s) {
            int[] solution = new int[3];
            List<String> list = new LinkedList<>();
            int index =0;
            while(index>=0){
                solution[index]++;
                while(solution[index]<=3&&!isSplitValid(index,solution,s))
                    solution[index]++;
                if(solution[index]<=3){
                    if(index==2){
                        list.add(toIPString(s, solution));
                    }else{
                        index++;
                        solution[index]=0;
                    }
                }else{
                    index--;
                }
            }
            return list;
        }
        protected final boolean isSplitValid(int split,int[] solution,String s){
            int prevOffset=0;
            for(int i=0;i<split;i++){
                prevOffset+=solution[i];
            }

            boolean lengthValid = prevOffset +solution[split]+3-split<=s.length()
                        && prevOffset+solution[split]+3*(3-split)>=s.length();
            boolean numberValid = lengthValid;
            if(lengthValid){
                String s1 = s.substring(prevOffset,prevOffset+solution[split]);
                 numberValid= !(s1.length()>1 && s1.charAt(0)=='0')
                        && Integer.parseInt(s1)<=255;
            }

            if(split<2){
                return lengthValid && numberValid;
            }else{
                boolean numberValid2 = lengthValid;
                if(lengthValid){
                    String s2 = s.substring(prevOffset+solution[split]);
                    numberValid2 = !(s2.length()>1 && s2.charAt(0)=='0')
                            && Integer.parseInt(s2)<=255 ;
                }
                return lengthValid  && numberValid && numberValid2;
            }
        }
        protected final String toIPString(String s,int[] solution){
            return s.substring(0,solution[0])+"."+
                    s.substring(solution[0],solution[0]+solution[1])+"."+
                    s.substring(solution[0]+solution[1],solution[0]+solution[1]+solution[2])+"."+
                    s.substring(solution[0]+solution[1]+solution[2]);
        }
    }
    public static void main(String[] a){
        Solution solution=new RestoreIPAddresses().new Solution();
        List<String> list = solution.restoreIpAddresses("010010");
        solution.isSplitValid(2,new int[]{2,2,1},"1234567");
        String x = solution.toIPString("123456789",new int[]{0,2,7,9,2,4,5,5,8,7,3,0,3});
    }
}
