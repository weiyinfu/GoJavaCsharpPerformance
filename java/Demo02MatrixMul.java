class Demo02MatrixMul {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        var a = new int[1000][1000];
        var b = new int[1000][1000];
        var c = new int[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                for (int k = 0; k < 1000; k++) {
                    c[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}