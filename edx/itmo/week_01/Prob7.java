package edx.itmo.week_01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Prob7 {

    public static int w, h;
    public static HashMap<Character, Point> keyboard = new HashMap<Character, Point>();

    static Scanner newInput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new Scanner(new File("input.txt"));
        } else {
            return new Scanner(System.in);
        }
    }
    static PrintWriter newOutput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new PrintWriter("output.txt");
        } else {
            return new PrintWriter(System.out);
        }
    }

    public static int dist(Point p1, Point p2) {
        return Math.max(Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
    }

    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            w = in.nextInt();
            h = in.nextInt();
            in.nextLine();
            for(int i = 0; i < h; i++){
                String line = in.nextLine();
                for(int j = 0; j < w; j++){
                    keyboard.put(line.charAt(j), new Point(i, j));
                }
            }
            String langName = "";
            int langScore = 999999999;
            for(int l = 0; l < 3; l++){
                String currLang = in.nextLine();
                in.nextLine(); // % template start %
                String line;
                StringBuilder sbCode = new StringBuilder();
                while(!(line = in.nextLine()).equals("%TEMPLATE-END%")){
                    sbCode.append(line.replaceAll(" ", ""));
                }
                String code = sbCode.toString();
                int currScore = 0;
                Point p1 = keyboard.get(code.charAt(0));
                for(int i = 1; i < code.length(); i++) {
                    Point p2 = keyboard.get(code.charAt(i));
                    currScore += dist(p1, p2);
                    p1 = p2;
                }
                if(currScore < langScore){
                    langScore = currScore;
                    langName = currLang;
                }
            }
            out.println(langName);
            out.println(langScore);
        }
    }

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

