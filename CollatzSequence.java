
import java.util.HashSet;

public class CollatzSequence {
    // HashSet to store numbers that have been processed before (memoization)
    private static HashSet<Long> collatzHistory = new HashSet<>();

    public static void main(String[] args) {
        long startTime, endTime;

        // -- 1) Standart Approach for a number --

        // startTime = System.currentTimeMillis();
        // collatz(10000000);
        // endTime = System.currentTimeMillis();
        // System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        // -- 2) Standart Aprroach for an interval --

        startTime = System.currentTimeMillis();
        collatzSequence(1, 1000);
        endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        // -- 3) Standart Aprroach for an number (printing step by step) --

        // startTime = System.currentTimeMillis();
        // printCollatz(5);
        // endTime = System.currentTimeMillis();
        // System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        // -- 4) Standart Aprroach for an interval (printing step by step) --

        // startTime = System.currentTimeMillis();
        // printCollatzSequence(1, 250);
        // endTime = System.currentTimeMillis();
        // System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        // -- 5) Standart approach using memoization for a number --

        // startTime = System.currentTimeMillis();
        // collatzMemoization(1500);
        // endTime = System.currentTimeMillis();
        // System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        // -- 6) Standart approach using memoization for an interval --

        // startTime = System.currentTimeMillis();
        // CollatzSequenceMemoization(1, 25000);
        // endTime = System.currentTimeMillis();
        // System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        // -- 7) Farkas' Variant for a number (even or odd)--

        // startTime = System.currentTimeMillis();
        // collatzFarkas(250);
        // endTime = System.currentTimeMillis();
        // System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        // -- 8) Farkas' Variant for an interval (even or odd) --

        // startTime = System.currentTimeMillis();
        // collatzSequenceFarkas(1, 1000);
        // endTime = System.currentTimeMillis();
        // System.out.println("Execution Time: " + (endTime - startTime) + " ms");

        // -- 8) Farkas' Variant for a number (only for odd numbers) --

        // startTime = System.currentTimeMillis();
        // System.out.println(collatzFarkasOdd(999)); // Input must be odd
        // endTime = System.currentTimeMillis();
        // System.out.println("Execution Time: " + (endTime - startTime) + " ms");
    }

    // Function to print the steps of the Collatz sequence for a given number
    public static void printCollatz(long num) {
        System.out.println("Starting value is " + num);
        long step = 1;

        // Keep calculating sequence until reaching 1
        while (num != 1) {
            System.out.print("Step: " + step + " --> ");
            if (num % 2 == 0) {
                System.out.print(num + " is even, applying operation: " + num + " / 2");
                num /= 2; // If the number is even, divide by 2
                System.out.println(" = " + num);
            } else {
                System.out.print(num + " is odd, applying operation: 3 * " + num + " + 1");
                num = 3 * num + 1; // If the number is odd, apply 3n + 1
                System.out.println(" = " + num);
            }
            step++;
        }

        // Print the final step where the sequence ends at 1
        System.out.println("Sequence successfully ended at step " + step);
    }

    // Function to print the Collatz sequence for all numbers in a given range
    public static void printCollatzSequence(long startValue, long endValue) {
        for (long i = startValue; i <= endValue; i++) {
            printCollatz(i);
        }
    }

    public static boolean collatz(long num) {
        if (num == 1) {
            // System.out.println("Sequence endend succesfully");
            return true;
        }
        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num = 3 * num + 1;
            }
            if (num == 1) {
                // System.out.println("Sequence endend succesfully");
                return true;
            }
        }
        return false;
    }

    public static boolean collatzSequence(long startValue, long endValue) {
        boolean result = false;
        for (long i = startValue; i <= endValue; i++) {
            result = collatz(i);
        }
        if (result) {
            System.out.println("Sequence successfully ended for all numbers between " + startValue + " - " + endValue);
        }
        return result;
    }

    // Function to check and process the Collatz sequence for a number using
    // memoization
    public static boolean collatzMemoization(long num) {
        // Check if the number has been processed before (memoization)
        if (collatzHistory.contains(num)) {
            return true;
        }

        long originalNum = num; // Store the original number to add to history later

        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2; // If the number is even, divide by 2
            } else {
                num = 3 * num + 1; // If the number is odd, apply 3n + 1
            }

        }
        // Add the original number to the history (memoization)

        if (collatzHistory.size() < 65000000)
            collatzHistory.add(originalNum);
        return true;
    }

    // Function to compute the Collatz sequence for all numbers in a given range
    public static boolean CollatzSequenceMemoization(long startValue, long endValue) {
        for (long i = startValue; i <= endValue; i++) {
            collatzMemoization(i);
        }

        System.out.println("Sequence successfully ended for all numbers between " + startValue + " - " + endValue);
        return true;
    }

    // Farkas' Variant of this sequence. Title 4.1 in article
    public static boolean collatzFarkas(long n) {
        if (n == 1) {
            return true;
        }

        if (n % 3 == 1) {
            return collatzFarkas((n - 1) / 3);
        } else if (n % 6 == 0 || n % 6 == 2) {
            return collatzFarkas(n / 2);
        } else if (n % 6 == 3 || n % 6 == 5) {
            return collatzFarkas((3 * n + 1) / 2);
        }
        return false;
    }

    public static boolean collatzSequenceFarkas(long startValue, long endValue) {
        for (long i = startValue; i <= endValue; i++) {
            if (!collatzFarkas(i)) {
                return false;
            }
        }
        return true;
    }
    // Farkas' Variant of this sequence for only odd numbers. Title 4.1 in article
    public static long collatzFarkasOdd(long n) {
        if (n == 1) {
            return 1;
        }
        if (n % 3 == 0) {
            return collatzFarkasOdd(n / 3);
        } else if (n % 3 != 0 && n % 4 == 1) {
            return collatzFarkasOdd((n + 1) / 2);
        } else if (n % 3 != 0 && n % 4 == 3) {
            return collatzFarkasOdd((3 * n + 1) / 2);
        }
        return -1;
    }
}

