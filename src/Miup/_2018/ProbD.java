package Miup._2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProbD {

    public static void main(String... args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int speed = Integer.parseInt(bf.readLine());
        int tap = Integer.parseInt(bf.readLine());
        int tMax = Integer.parseInt(bf.readLine());
        int n = Integer.parseInt(bf.readLine());
        int period = Integer.parseInt(bf.readLine());
        int[] tapBehaviour = new int[period];
        boolean[] slices = new boolean[361];
        for (int i = 0; i < slices.length; i++) {
            slices[i] = false;
        }
        String[] tokens = bf.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int start = Integer.parseInt(tokens[i*2]);
            int end = Integer.parseInt(tokens[i*2+1]);
            for(int j = start; j <= end; j++){
                slices[j] = true;
            }
            if(end == 360){
                slices[0] = true;
            }
        }
        tokens = bf.readLine().split(" ");
        for (int i = 0; i < period; i++) {
            tapBehaviour[i] = Integer.parseInt(tokens[i]);
        }

        int nDrops = 0;
        for(int t = 0; t < tMax; t++){
            int oldTapPos = tap;
            int action = tapBehaviour[t % period];
            int nextStep = (tap - speed) % 360;
            if(nextStep < 0) nextStep = 360 + nextStep;
            tap = nextStep;
            if(action == 1) nDrops++;
            if(action == 1 && !slices[oldTapPos]) {
                nDrops--;
                break;
            }
        }
        System.out.println(nDrops);
    }

    public static class Slice{
        public int startAng;
        public int endAng;

        public Slice(int startAng, int endAng) {
            this.startAng = startAng;
            this.endAng = endAng;
        }
    }
}
