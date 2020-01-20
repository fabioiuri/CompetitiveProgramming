package CP_Classes._05_Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _118 {

    public static void main(String[] args)  throws IOException {
        char [][] turn=new char [128][128];
        turn['N']['L']='W';
        turn['W']['L']='S';
        turn['S']['L']='E';
        turn['E']['L']='N';
        turn['N']['R']='E';
        turn['E']['R']='S';
        turn['S']['R']='W';
        turn['W']['R']='N';

        int [] moveX=new int [128];
        int [] moveY=new int [128];

        moveY['N']=1;
        moveY['S']=-1;
        moveX['W']=-1;
        moveX['E']=1;

        boolean [][] protect=new boolean [51][51];
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int maxX=Integer.parseInt(st.nextToken());
        int maxY=Integer.parseInt(st.nextToken());

        String s;
        while (true) {
            s=br.readLine();
            if (s==null || s.isEmpty()) {
                break;
            }
            st=new StringTokenizer(s);
            int currX=Integer.parseInt(st.nextToken());
            int currY=Integer.parseInt(st.nextToken());
            char face=st.nextToken().charAt(0);

            boolean lost=false;

            char [] instr=br.readLine().toCharArray();
            for (int i=0;i<instr.length;i++) {
                if (instr[i]=='L' || instr[i]=='R') {
                    face=turn[face][instr[i]];
                } else if (instr[i]=='F') {
                    int newX=currX+moveX[face];
                    int newY=currY+moveY[face];
                    if ((newX<0 || newX>maxX || newY<0 || newY>maxY)) {
                        if (!protect[currX][currY]) {
                            protect[currX][currY]=true;
                            lost=true;
                            break;
                        }
                    } else {
                        currX=newX;
                        currY=newY;
                    }
                }
            }

            if (lost) {
                System.out.println(currX+" "+currY+" "+face+" LOST");
            } else {
                System.out.println(currX+" "+currY+" "+face);
            }
        }
    }
}
