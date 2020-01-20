package GoogleHashcode._2019;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class main {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (String s : new String[]{"c"}) {
            doit("input_" + s+".txt");
            System.out.println("done " + s);
        }

    }

    public static void doit (String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int n = Integer.parseInt(br.readLine());
        Photo[] photos = new Photo[n];
        ArrayList<Photo> horizontalPhotos = new ArrayList<>();
        ArrayList<Photo> verticalPhotos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            photos[i] = new Photo(line[0], i);
            int m = Integer.parseInt(line[1]);
            for (int j = 0; j < m; j++) {
                photos[i].addTag(line[2 + j]);
            }
            if (photos[i].horizontal) horizontalPhotos.add(photos[i]);
            else verticalPhotos.add(photos[i]);
            // System.out.println(photos[i]);
        }

        // 2ND PART

        for (int i = 0; i < verticalPhotos.size(); i+=2) {
            if (i + 1 < verticalPhotos.size()){
                Photo masterPhoto = verticalPhotos.get(i);
                masterPhoto.merge(verticalPhotos.get(i+1));
                horizontalPhotos.add(masterPhoto);
            }
        }

        int s = horizontalPhotos.size();
        StringBuilder sb = new StringBuilder();
        Photo hand = null;
        if (s > 0) {
            sb.append(horizontalPhotos.get(0).id);
            sb.append("\n");
            hand = horizontalPhotos.get(0);
            horizontalPhotos.remove(0);
        }
        
        while (hand != null) {
            System.out.println("missing " + horizontalPhotos.size());
            int bestMatch = -1;
            int maxI = 0;
            int maxPossibleScore = (int) Math.floor(hand.tags.size() / 2);
            for (int i = 0; i < horizontalPhotos.size(); i++) {
                Photo currPhoto = horizontalPhotos.get(i);
                int nSimilar = (int) hand.tags.stream().filter(currPhoto.tags::contains).count();
                int currI = Math.min(nSimilar,
                        Math.min(hand.tags.size() - nSimilar,
                                currPhoto.tags.size() - nSimilar));
                if (maxI <= currI) {
                    maxI = currI;
                    bestMatch = i;

                    if (maxI >= maxPossibleScore) break;
                }
            }
            if (bestMatch == -1) break;

            hand = horizontalPhotos.get(bestMatch);
            sb.append(hand.id);
            horizontalPhotos.remove(bestMatch);
            sb.append('\n');
        }

        PrintWriter printWriter = new PrintWriter(new FileWriter("out_"+fileName, false));

        printWriter.println(s);
        printWriter.print(sb.toString());
        printWriter.close();
    }

    static class Photo {
        public String id;
        public boolean horizontal;
        public HashSet<String> tags;

        public Photo(String o, int id) {
            this.horizontal = o.equals("H");
            this.id = id + "";
            this.tags = new HashSet<>();
        }

        public void merge(Photo photo) {
            this.id += " " + photo.id;
            this.tags.addAll(photo.tags);
        }

        public void addTag(String tag) {
            tags.add(tag);
        }

        public String toString() {
            return "[" + horizontal + ": " + tags.toString() + "]";
        }
    }
}
