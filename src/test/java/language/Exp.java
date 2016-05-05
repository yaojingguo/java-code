package language;

import org.junit.Test;

public class Exp {

    @Test
    public void test() {
        for (int i = 0; i <= 11; i++)
            System.out.printf("%d(%d),", comb(i), i);
        System.out.println();
    }

    int comb(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return comb(n - 1) + comb(n - 2);
    }
}
