package com.alvin.juc.c_000;

import java.util.concurrent.TimeUnit;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {


/*        testSleep();
        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }*/

        /** testYield()
         * A0
         * -----------B0
         * A1
         * A2
         * A3
         * A4
         * A5
         * A6
         * A7
         * A8
         * A9
         * A10
         * -----------B1
         * -----------B2
         * -----------B3
         * -----------B4
         * -----------B5
         * -----------B6
         * -----------B7
         * -----------B8
         * -----------B9
         * -----------B10
         * A11
         * A12
         * A13
         * A14
         * A15
         * A16
         * A17
         * A18
         * A19
         * A20
         * -----------B11
         * -----------B12
         * -----------B13
         * -----------B14
         * -----------B15
         * -----------B16
         * -----------B17
         * -----------B18
         * -----------B19
         * -----------B20
         * A21
         * A22
         * A23
         * A24
         * A25
         * A26
         * A27
         * A28
         * A29
         * A30
         * -----------B21
         * -----------B22
         * -----------B23
         * -----------B24
         * -----------B25
         * -----------B26
         * -----------B27
         * -----------B28
         * -----------B29
         * -----------B30
         * A31
         * A32
         * A33
         * A34
         * A35
         * A36
         * A37
         * A38
         * A39
         * A40
         * -----------B31
         * -----------B32
         * -----------B33
         * -----------B34
         * -----------B35
         * -----------B36
         * -----------B37
         * -----------B38
         * -----------B39
         * -----------B40
         * A41
         * A42
         * A43
         * A44
         * A45
         * A46
         * A47
         * A48
         * A49
         * A50
         * -----------B41
         * -----------B42
         * -----------B43
         * -----------B44
         * -----------B45
         * -----------B46
         * -----------B47
         * -----------B48
         * -----------B49
         * -----------B50
         * A51
         * A52
         * A53
         * A54
         * A55
         * A56
         * A57
         * A58
         * A59
         * A60
         * -----------B51
         * -----------B52
         * -----------B53
         * -----------B54
         * -----------B55
         * -----------B56
         * -----------B57
         * -----------B58
         * -----------B59
         * -----------B60
         * A61
         * A62
         * A63
         * A64
         * A65
         * A66
         * A67
         * A68
         * A69
         * A70
         * -----------B61
         * A71
         * A72
         * A73
         * A74
         * A75
         * -----------B62
         * A76
         * -----------B63
         * A77
         * -----------B64
         * A78
         * -----------B65
         * A79
         * -----------B66
         * A80
         * -----------B67
         * A81
         * -----------B68
         * -----------B69
         * -----------B70
         * A82
         * -----------B71
         * -----------B72
         * -----------B73
         * -----------B74
         * -----------B75
         * -----------B76
         * -----------B77
         * -----------B78
         * -----------B79
         * -----------B80
         * A83
         * A84
         * A85
         * A86
         * A87
         * A88
         * A89
         * A90
         * -----------B81
         * -----------B82
         * -----------B83
         * -----------B84
         * A91
         * -----------B85
         * A92
         * -----------B86
         * A93
         * A94
         * A95
         * A96
         * A97
         * A98
         * A99
         * -----------B87
         * -----------B88
         * -----------B89
         * -----------B90
         * -----------B91
         * -----------B92
         * -----------B93
         * -----------B94
         * -----------B95
         * -----------B96
         * -----------B97
         * -----------B98
         * -----------B99
         */

//        testYield();
        testJoin();
    }

    static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A" + i);
            }
        }).start();
    }

    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("-----------B" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("B" + i);
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t1.start();

    }
}
