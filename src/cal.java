import java.io.BufferedReader;
import java.io.InputStreamReader;


public class cal {
	public static void main(String[] args) throws Exception {
        // 自分の得意な言語で
        // Let's チャレンジ！！
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String num[] = line.split("[\\s]+");
        int m = Integer.parseInt(num[0]);
        int n = Integer.parseInt(num[1]);
        int ans1 = m / n; 
        int ans2 = m % n;
        System.out.println(ans1 + " " + ans2);
    }
}
