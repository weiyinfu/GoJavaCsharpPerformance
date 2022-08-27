using NUnit.Framework;

namespace Demo01PrintTable
{
    public class MainClass
    {
        ISet<string> vis = new HashSet<string>();
        static Dictionary<int, List<int>> edges = new();

        static int pack(int x, int y)
        {
            if (x > y)
            {
                return y * 10 + x;
            }

            return x * 10 + y;
        }

        static MainClass()
        {
            for (int i = 0; i < 3; i++)
            {
                int f = i * 3;
                int t = i * 3 + 2;
                int m = i * 3 + 1;
                edges.Add(pack(f, t), new List<int>
                {
                    pack(f, m), pack(m, t)
                });
                f = i;
                t = 2 * 3 + i;
                m = 1 * 3 + i;
                edges.Add(pack(f, t), new List<int>
                {
                    pack(f, m), pack(m, t)
                });
            }

            edges.Add(pack(0, 8), new List<int> {pack(0, 4), pack(4, 8)});
            edges.Add(pack(2, 6), new List<int> {pack(2, 4), pack(4, 6)});
        }

        void swap(List<int> a, int x, int y)
        {
            (a[x], a[y]) = (a[y], a[x]);
        }

        void submit(List<int> a)
        {
            //提交一个路径
            /*
             * 02=012
             * 35=345
             * 06=036
             *
             * */
            ISet<int> se = new HashSet<int>();
            for (int i = 0; i < a.Count() - 1; i++)
            {
                int it = pack(a[i], a[i + 1]);
                if (edges.ContainsKey(it))
                {
                    se.UnionWith(edges[it]);
                }
                else
                {
                    se.Add(it);
                }
            }

            string k = string.Join(",", se.OrderBy(x => x).Select(x => x.ToString()));
            vis.Add(k);
        }

        void perm(List<int> a, int x)
        {
            if (x == a.Count)
            {
                submit(a);
                return;
            }

            for (int i = x; i < a.Count; i++)
            {
                swap(a, x, i);
                perm(a, x + 1);
                swap(a, x, i);
            }
        }

        MainClass(List<int> a)
        {
            for (int i = 0; i < (1 << a.Count); i++)
            {
                //flag表示是否可以选择这个点
                List<int> b = new();
                for (int j = 0; j < a.Count; j++)
                {
                    if ((i & (1 << j)) != 0)
                    {
                        b.Add(a[j]);
                    }
                }

                if (b.Count < 2) continue;
                perm(b, 0);
            }
        }

        public static void table()
        {
            List<int> ans = new();
            for (int i = 0; i < (1 << 9); i++)
            {
                List<int> a = new();
                for (int j = 0; j < 9; j++)
                {
                    if ((i & (1 << j)) > 0)
                    {
                        a.Add(j);
                    }
                }

                ans.Add(new MainClass(a).vis.Count);
            }

            Console.WriteLine(ans);
        }
    }

    public class Haha
    {
        [Test]
        public static void Run()
        {
            var beginTime = DateTime.UtcNow.Subtract(DateTime.UnixEpoch).TotalMilliseconds;
            MainClass.table();
            var endTime = DateTime.UtcNow.Subtract(DateTime.UnixEpoch).TotalMilliseconds;
            Console.WriteLine(endTime - beginTime);
        }
    }
}