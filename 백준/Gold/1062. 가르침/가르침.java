import java.io.*;
import java.util.*;

public class Main {
  static int N, K, answer;
  static long[] bitmaskWord;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;

    stk = new StringTokenizer(br.readLine());
    N = Integer.parseInt(stk.nextToken());
    K = Integer.parseInt(stk.nextToken());

    String[] arr = new String[N];
    for (int i=0; i<N; i++) {
      arr[i] = br.readLine();
    }

    if (K < 5) {
      System.out.println(0);
      return;
    }

    bitmaskWord = strToBitmask(arr);

    boolean[] visited = new boolean[26];

    // a b c d e f g h i j k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
    // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
    // a, c, i, t, n 포함
    visited[0] = true;
    visited[2] = true;
    visited[8] = true;
    visited[13] = true;
    visited[19] = true;
    // 5개를 포함한 조합 생성
    comb(5, 0, visited);

    System.out.println(answer);
  }

  private static void comb(int cnt, int start, boolean[] visited) {
    if (cnt == K) {
      long bitmaskV = visitedToBitmask(visited);
      int result = compareBitmaskWord(bitmaskV);
      answer = Math.max(answer, result);

      return;
    }

    for (int i=start; i<26; i++) {
      if (visited[i])
        continue;

      visited[i] = true;
      comb(cnt+1, i+1, visited);
      visited[i] = false;
    }
  }

  private static int compareBitmaskWord(long bitmaskV) {
    int result = 0;

    for (int i=0; i<bitmaskWord.length; i++)
      if ((bitmaskWord[i] & bitmaskV) == bitmaskWord[i])
        result++;

    return result;
  }

  private static long visitedToBitmask(boolean[] visited) {
    long result = 0L;

    for (int i=0; i<visited.length; i++)
      if (visited[i])
        result = (result | (1 << i));

    return result;
  }

  private static long[] strToBitmask(String[] arr) {
    long[] result = new long[arr.length];

    for (int i=0; i<arr.length; i++) {
      char[] ca = arr[i].toCharArray();
      long l = 0L;
      for (char c : ca)
        l = (l | (1 << c-'a'));

      result[i] = l;
    }

    return result;
  }
}