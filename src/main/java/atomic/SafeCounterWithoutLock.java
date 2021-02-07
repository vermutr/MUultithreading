package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounterWithoutLock {
    private final AtomicInteger counter = new AtomicInteger(0);

    public int getValue() {
        return counter.get();
    }

    public void increment(int value) {
        while(true) {
            int existingValue = getValue();
            int newValue = existingValue + value;
            if(counter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
}
