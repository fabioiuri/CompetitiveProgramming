package UVA.DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob105 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        List<BuildingPoint> buildingPoints = new ArrayList<>();
        PriorityQueue<Integer> heightHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });

        while (line != null) {
            String[] tokens = line.split(" ");
            buildingPoints.add(new BuildingPoint(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), true));
            buildingPoints.add(new BuildingPoint(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1]), false));

            line = br.readLine();
        }

        buildingPoints.sort(new Comparator<BuildingPoint>() {
            @Override
            public int compare(BuildingPoint o1, BuildingPoint o2) {
                int cmp = Integer.compare(o1.x, o2.x);
                if (cmp == 0) {
                    cmp = -Integer.compare(o1.height, o2.height);
                }
                return cmp;
            }
        });

        LinkedList<Integer> points = new LinkedList<>();
        Set<Integer> endedXs = new HashSet<>();
        for (BuildingPoint buildingPoint : buildingPoints) {
            int currMax = (heightHeap.isEmpty()) ? 0 : heightHeap.peek();

            if (buildingPoint.startPoint) {
                heightHeap.add(buildingPoint.height);
                if (buildingPoint.height > currMax) {
                    if (endedXs.contains(buildingPoint.x)) {
                        points.removeLast();
                        points.removeLast();
                    }
                    points.add(buildingPoint.x);
                    points.add(buildingPoint.height);
                }
            } else {
                heightHeap.remove(buildingPoint.height);
                int newMax = (heightHeap.isEmpty()) ? 0 : heightHeap.peek();
                if (newMax < currMax) { // if x is in endedXs, a previous higher endpoint was already added
                    if (endedXs.contains(buildingPoint.x)) {
                        points.removeLast();
                        points.removeLast();
                    }
                    endedXs.add(buildingPoint.x);
                    points.add(buildingPoint.x);
                    points.add(newMax);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer point : points) {
            sb.append(point).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static class BuildingPoint {
        int x, height;
        boolean startPoint;

        public BuildingPoint(int x, int height, boolean startPoint) {
            this.x = x;
            this.height = height;
            this.startPoint = startPoint;
        }
    }
}
