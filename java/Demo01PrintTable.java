import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Demo01PrintTable {
    Set<String> vis = new HashSet<>();
    static Map<Integer, List<Integer>> edges = new HashMap<>();

    static int pack(int x, int y) {
        if (x > y) {
            return y * 10 + x;
        }
        return x * 10 + y;
    }

    static {
        for (int i = 0; i < 3; i++) {
            int f = i * 3;
            int t = i * 3 + 2;
            int m = i * 3 + 1;
            edges.put(pack(f, t), Arrays.asList(pack(f, m), pack(m, t)));
            f = i;
            t = 2 * 3 + i;
            m = 1 * 3 + i;
            edges.put(pack(f, t), Arrays.asList(pack(f, m), pack(m, t)));
        }
        edges.put(pack(0, 8), Arrays.asList(pack(0, 4), pack(4, 8)));
        edges.put(pack(2, 6), Arrays.asList(pack(2, 4), pack(4, 6)));
    }

    void swap(List<Integer> a, int x, int y) {
        int temp = a.get(x);
        a.set(x, a.get(y));
        a.set(y, temp);
    }

    void submit(List<Integer> a) {
        //提交一个路径
        /*
         * 02=012
         * 35=345
         * 06=036
         *
         * */
        Set<Integer> se = new TreeSet<>();
        for (int i = 0; i < a.size() - 1; i++) {
            int f = a.get(i);
            int t = a.get(i + 1);
            int it = pack(f, t);
            if (edges.containsKey(it)) {
                se.addAll(edges.get(it));
            } else {
                se.add(it);
            }
        }
        String k = se.stream().sorted().map(x -> x.toString()).collect(Collectors.joining(","));
        vis.add(k);
    }

    void perm(List<Integer> a, int x) {
        if (x == a.size()) {
            submit(a);
            return;
        }
        for (int i = x; i < a.size(); i++) {
            swap(a, x, i);
            perm(a, x + 1);
            swap(a, x, i);
        }
    }

    Demo01PrintTable(List<Integer> a) {
        for (int i = 0; i < (1 << a.size()); i++) {
            //flag表示是否可以选择这个点
            List<Integer> b = new ArrayList<>();
            for (int j = 0; j < a.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    b.add(a.get(j));
                }
            }
            if (b.size() < 2) continue;
            perm(b, 0);
        }
    }

    public static void one() throws FileNotFoundException {
        Scanner cin = new Scanner(new FileInputStream("in3.txt"));
//        Scanner cin = new Scanner(System.in);
        int T = cin.nextInt();
        int x = 0;
        while (T-- > 0) {
            List<Integer> a = new ArrayList<>();
            x = 0;
            for (int i = 0; i < 3; i++) {
                String s = cin.next().trim();
                for (int j = 0; j < 3; j++) {
                    if (s.charAt(j) == '.') {
                        int p = i * 3 + j;
                        a.add(p);
                        x |= (1 << p);
                    }
                }
            }
//            System.out.println(new Main(a).vis.size());
            System.out.println(fast(x));
        }
    }

    public static void table() {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < (1 << 9); i++) {
            List<Integer> a = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if ((i & (1 << j)) > 0) {
                    a.add(j);
                }
            }
            ans.add(new Demo01PrintTable(a).vis.size());
        }
        System.out.println(ans);
    }

    public static int fast(int x) {
        int[] a = {0, 0, 0, 1, 0, 1, 1, 3, 0, 1, 1, 6, 1, 6, 6, 22, 0, 1, 1, 6, 1, 6, 6, 22, 1, 6, 6, 30, 6, 30, 30, 135, 0, 1, 1, 6, 1, 6, 6, 22, 1, 6, 6, 30, 6, 30, 30, 135, 1, 6, 6, 30, 6, 30, 30, 135, 3, 22, 22, 135, 22, 135, 135, 800, 0, 1, 1, 6, 1, 6, 6, 22, 1, 3, 6, 22, 6, 22, 30, 113, 1, 6, 6, 30, 3, 22, 22, 113, 6, 22, 30, 135, 22, 113, 135, 705, 1, 6, 6, 30, 6, 30, 30, 135, 6, 22, 30, 135, 30, 135, 160, 789, 6, 30, 30, 160, 22, 135, 135, 789, 22, 110, 135, 780, 111, 688, 780, 5090, 0, 1, 1, 6, 1, 6, 6, 22, 1, 6, 6, 30, 6, 30, 30, 135, 1, 6, 3, 22, 6, 30, 22, 110, 6, 30, 22, 135, 30, 160, 135, 780, 1, 6, 6, 30, 6, 30, 30, 135, 6, 30, 30, 160, 30, 160, 160, 879, 6, 30, 22, 135, 30, 160, 135, 780, 22, 135, 111, 780, 135, 879, 780, 5496, 1, 6, 6, 30, 6, 30, 30, 135, 6, 22, 30, 135, 30, 135, 160, 789, 6, 30, 22, 135, 22, 135, 111, 688, 30, 135, 135, 800, 135, 789, 780, 5090, 6, 30, 30, 160, 30, 160, 160, 879, 30, 135, 160, 879, 160, 879, 975, 5953, 30, 160, 135, 879, 135, 879, 780, 5460, 135, 780, 780, 5496, 780, 5460, 5424, 41820, 0, 1, 1, 6, 1, 6, 6, 22, 1, 6, 6, 30, 6, 30, 30, 135, 1, 3, 6, 22, 6, 22, 30, 113, 6, 22, 30, 135, 30, 135, 160, 789, 1, 6, 6, 30, 3, 22, 22, 113, 6, 30, 30, 160, 22, 135, 135, 789, 6, 22, 30, 135, 22, 113, 135, 705, 22, 111, 135, 780, 110, 688, 780, 5090, 1, 6, 6, 30, 6, 30, 30, 135, 6, 22, 30, 135, 30, 135, 160, 789, 6, 22, 30, 135, 22, 111, 135, 696, 30, 113, 160, 789, 135, 696, 879, 5090, 6, 30, 30, 160, 22, 135, 135, 789, 30, 135, 160, 879, 135, 800, 879, 5558, 30, 135, 160, 879, 113, 696, 789, 5090, 135, 688, 879, 5460, 688, 4603, 5460, 39508, 1, 6, 6, 30, 6, 30, 30, 135, 6, 30, 30, 160, 30, 160, 160, 879, 6, 22, 22, 111, 30, 135, 135, 688, 30, 135, 135, 780, 160, 879, 879, 5460, 6, 30, 30, 160, 22, 135, 135, 789, 30, 160, 160, 975, 135, 879, 879, 5953, 30, 135, 135, 780, 135, 789, 800, 5090, 135, 780, 780, 5424, 780, 5460, 5496, 41820, 3, 22, 22, 135, 22, 135, 135, 800, 22, 113, 135, 789, 135, 789, 879, 5558, 22, 113, 110, 688, 113, 696, 688, 4603, 135, 705, 780, 5090, 789, 5090, 5460, 39508, 22, 135, 135, 879, 113, 789, 789, 5558, 135, 789, 879, 5953, 789, 5558, 5953, 44934, 135, 789, 780, 5460, 705, 5090, 5090, 39508, 800, 5090, 5496, 41820, 5090, 39508, 41820, 355148};
        return a[x];
    }

    public static void main(String[] args) throws FileNotFoundException {
        long beginTime = System.currentTimeMillis();
        table();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
    }
}