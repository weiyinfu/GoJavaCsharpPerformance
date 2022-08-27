public class Util
{
    public static double GetCurrentMillis()
    {
        return DateTime.UtcNow.Subtract(DateTime.UnixEpoch).TotalMilliseconds;
    }
}