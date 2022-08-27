public class Program
{
    public static void Main(string[] args)
    {
        var begin = GetCurrentMillis();
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

        var endTime = GetCurrentMillis();
        Console.WriteLine(endTime - begin);
    }

    public static double GetCurrentMillis()
    {
        return DateTime.UtcNow.Subtract(DateTime.UnixEpoch).TotalMilliseconds;
    }
}