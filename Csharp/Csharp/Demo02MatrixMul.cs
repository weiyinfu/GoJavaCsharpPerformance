using NUnit.Framework;

namespace Demo01PrintTable;

[TestFixture]
public class Demo02
{
    [Test]
    public static void MatrixMul()
    {
        var begin = Util.GetCurrentMillis();
        var a = new int[1000, 1000];
        var b = new int[1000, 1000];
        var c = new int[1000, 1000];
        for (int i = 0; i < 1000; i++)
        {
            for (int j = 0; j < 1000; j++)
            {
                for (int k = 0; k < 1000; k++)
                {
                    c[i, k] += a[i, j] * b[j, k];
                }
            }
        }

        var endTime = Util.GetCurrentMillis();
        Console.WriteLine(endTime - begin);
    }

    static long fib(long x)
    {
        if (x <= 2) return 1;
        return fib(x - 1) + fib(x - 2);
    }

    [Test]
    public static void Fibnaci()
    {
        var begin = Util.GetCurrentMillis();
        Console.WriteLine(fib(45));
        var end = Util.GetCurrentMillis();
        Console.WriteLine(end - begin);
    }
}