package Codechef.AUG19B;

import java.io.*;
import java.util.*;

/**
 * Type: XOR DP
 * Difficulty: medium
 */
public class KSI {

    public static void main(String[] args) throws IOException {
        Reader s = new Reader();
        int T = s.nextInt();

        int[] arr = new int[100100];
        int[] cumulativeXor = new int[100100];
        Map<Integer, Pair> mp = new HashMap<>(100100);

        for (int t = 0; t < T; t++) {
            int n = s.nextInt();

            mp.clear();

            arr[0] = s.nextInt();
            cumulativeXor[0] = arr[0];
            mp.put(cumulativeXor[0], new Pair(0, 0L));
            for (int i = 1; i < n; i++) {
                arr[i] = s.nextInt();
                cumulativeXor[i] = cumulativeXor[i - 1] ^ arr[i];
                mp.put(cumulativeXor[i], new Pair(0, 0L));
            }

            long count = 0;

            for (int i = 0; i < n; i++) {
                /*ArrayList<Integer> indexes = mp.get(cumulativeXor[i]);
                for (Integer index : indexes) {
                    count += (long) i  - index;
                }*/

                Pair indexesInfo = mp.get(cumulativeXor[i]);
                if (indexesInfo.length > 0) {
                    count += (long) indexesInfo.length * i  - indexesInfo.sum;
                }


                if (cumulativeXor[i] == 0) {
                    count += (long) i;
                }

                mp.put(cumulativeXor[i],  new Pair(indexesInfo.length + 1, indexesInfo.sum + i + 1));
            }

            System.out.println(count);
        }
    }

    static class Pair {
        int length;
        long sum;

        Pair(int length, long sum) {
            this.length = length;
            this.sum = sum;
        }
    }

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}