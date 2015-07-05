import java.util.Stack;

/**
 * Created by Feiyu on 2015/7/2 0002.
 **/
public class WordSearch {

    /**
         * Given a 2D board and a word, find if the word exists in the grid.

         The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

         For example,
         Given board =

         [
         ["ABCE"],
         ["SFCS"],
         ["ADEE"]
         ]
         word = "ABCCED", -> returns true,
         word = "SEE", -> returns true,
         word = "ABCB", -> returns false.
     */
    /**
     * 理解错了，每一个字符的上下左右，不是数组的上下左右
     */
    public class Solution1 {
        public boolean exist(char[][] board, String word) {
            int mid=0;
            Stack<Character> stack = new Stack<>();
            int index=0;
            stack.push(word.charAt(index));
            int[] used = new int[word.length()];
            for(int i=0;i<used.length;i++)
                used[i]=-1;
            boolean isFind=false;
            while(!stack.empty() && mid<board.length){
                char c = stack.peek();
                outer:
                for(int i=mid-1>=0?mid-1:0;i<=(mid+1<board.length?mid+1:board.length-1);i++){
                    isFind=false;
                    for(int j=0;j<board[0].length;j++){
                        if(board[i][j]==c&& !existInArray(i * board[0].length + j, used,index)){
                            used[index]=i * board[0].length + j;
                            index++;
                            if(index==word.length())
                                return true;
                            else
                                stack.push(word.charAt(index));
                            isFind=true;
                            break outer;
                        }
                    }
                }
                if(!isFind){
                    mid++;
                }
            }
            return false;

        }

        private boolean existInArray(int a,int[] as,int endIndex){
            for(int i=0;i<=endIndex;i++){
                if(as[i]==a)
                    return true;
            }
            return false;
        }
    }

    /**
     * 没有记录哪些解尝试过了，回溯的时候不能跳过已经尝试过的状态
     */
    public class Solution2 {
        public boolean exist(char[][] board, String word) {
            int x,y,index = 0;
            boolean isFind=false;
            final int maxX=board.length-1,maxY=board[0].length-1;
            int[] path=new int[word.length()];
            for(int i=0;i<path.length;i++)
                path[i]=-1;
            for(int i=0;i<=maxX;i++){
                outer:
                for(int j=0;j<=maxY;j++){
                    index=0;
                    if(board[i][j]==word.charAt(0)){
                        x=i;y=j;
                        path[index]=x+y*board[0].length;
                        index=1;
                        while(index < word.length()){
                            isFind=false;
                            if(x-1>=0 && board[x-1][y]==word.charAt(index) && !existInArray(x-1+y*board[0].length,path,index)){
                                x=x-1;
                                path[index]=x+y*board[0].length;
                                index++;
                                isFind=true;
                                continue ;

                            }
                            if(x+1<=maxX && board[x+1][y]==word.charAt(index)  && !existInArray(x+1+y*board[0].length,path,index)){
                                x=x+1;
                                path[index]=x+y*board[0].length;
                                index++;
                                isFind=true;
                                continue ;
                            }
                            if(y-1>=0 && board[x][y-1]==word.charAt(index)  && !existInArray(x+(y-1)*board[0].length,path,index)){
                                y=y-1;
                                path[index]=x+y*board[0].length;
                                index++;
                                isFind=true;

                                continue ;
                            }
                            if(y+1<=maxY && board[x][y+1]==word.charAt(index)  && !existInArray(x+(y+1)*board[0].length,path,index)){
                                y=y+1;
                                path[index]=x+y*board[0].length;
                                index++;
                                isFind=true;

                                continue ;
                            }
                            if(!isFind){
                                if(index>0){
                                    y=path[index-1]/board[0].length;
                                    x=path[index-1]%board[0].length;
                                    index--;
                                }else{
                                    continue outer;
                                }
                            }


                        }
                        return true;

                    }
                }
            }
            return false;

        }

        private boolean existInArray(int a,int[] as,int endIndex){
            for(int i=0;i<=endIndex;i++){
                if(as[i]==a)
                    return true;
            }
            return false;
        }
    }

    public class Solution {
        public boolean exist(char[][] board, String word) {
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[0].length;j++){
                    if(board[i][j]==word.charAt(0)){
                        if(word.length()==1)
                            return true;
                        if(word.length()>board[0].length*board.length)
                            return false;
                        int index=0,x=i,y=j;
                        int[][] solution=new int[board.length][board[0].length];
                        int[] path = new int[word.length()*2];
                        for(int k=0;k<path.length;k++)
                            path[k]=-1;
                        solution[x][y]++;
                        path[0]=x;
                        path[1]=y;
                        index++;
                        while(index>0){
                            switch (solution[x][y]){
                                case 1:
                                    solution[x][y]++;
                                    if(x-1>=0 && board[x-1][y]==word.charAt(index) && !exsitInArray(path,x-1,y,2*(index-1))){
                                        x--;
                                        path[2*index]=x;
                                        path[2*index+1]=y;
                                        index++;
                                        solution[x][y]=1;
                                    }
                                    break;
                                case 2:
                                    solution[x][y]++;
                                    if(y+1<board[0].length && board[x][y+1]==word.charAt(index) && !exsitInArray(path,x,y+1,2*(index-1))){
                                        y++;
                                        path[2*index]=x;
                                        path[2*index+1]=y;
                                        index++;
                                        solution[x][y]=1;
                                    }

                                    break;
                                case 3:
                                    solution[x][y]++;
                                    if(x+1<board.length && board[x+1][y]==word.charAt(index) && !exsitInArray(path,x+1,y,2*(index-1))){
                                        x++;
                                        path[2*index]=x;
                                        path[2*index+1]=y;
                                        index++;
                                        solution[x][y]=1;
                                    }

                                    break;
                                case 4:
                                    solution[x][y]++;
                                    if(y-1>=0 && board[x][y-1]==word.charAt(index) && !exsitInArray(path,x,y-1,2*(index-1))){
                                        y--;
                                        path[2*index]=x;
                                        path[2*index+1]=y;
                                        index++;
                                        solution[x][y]=1;
                                    }

                                    break;
                            }
                            if(solution[x][y]>4){
                                if(index>=2){
                                    x=path[2*(index-2)];
                                    y=path[2*(index-2)+1];
                                    path[2*(index-1)]=-1;
                                    path[2*(index-1)+1]=-1;
                                    index--;
                                }else
                                    break;

                            }
                            if(index==word.length())
                                return true;
                        }

                    }
                }
            }
            return false;
        }

        private boolean exsitInArray(int[] path,int x,int y,int endIndex){
            for(int i=0;i<endIndex;i=i+2)
                if(path[i]==x && path[i+1]==y)
                    return true;
            return false;
        }
    }

    static public void main(String... args) {
        Solution solution = new WordSearch().new Solution();
        char[][] board1={{'a'},{'a'}};
        char[][] board = {
        {
            'A','B','C','E'
        },
        {
            'S','F','C','S'
        },
        {
            'A','D','E','E'
        }
        };
        char[][] board2 = {
                {
                        'C','C','A'
                },
                {
                        'A','A','A'
                },
                {
                        'B','C','D'
                }
        };
        boolean result = solution.exist(board,"ABCB");
        boolean haha = true;
    }


}
