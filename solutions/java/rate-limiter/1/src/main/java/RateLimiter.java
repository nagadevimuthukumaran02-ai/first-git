import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter<K> {

    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;

    private final Map<K, Integer> counts = new HashMap<>();
    private final Map<K, Instant> windowStart = new HashMap<>();

    public RateLimiter(int limit, Duration windowSize, TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;
    }

    public boolean allow(K clientId) {
        Instant now = timeSource.now();

        if (!windowStart.containsKey(clientId)) {
            windowStart.put(clientId, now);
            counts.put(clientId, 1);
            return true;
        }

        Instant start = windowStart.get(clientId);

        if (!now.isBefore(start.plus(windowSize))) {
            windowStart.put(clientId, now);
            counts.put(clientId, 1);
            return true;
        }

        int count = counts.get(clientId);

        if (count < limit) {
            counts.put(clientId, count + 1);
            return true;
        }

        return false;
    }
}