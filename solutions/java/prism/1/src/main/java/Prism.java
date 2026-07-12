import java.util.*;

public class Prism {
    public record LaserInfo(double x, double y, double angle) {
    }

    public record PrismInfo(int id, double x, double y, double angle) {
    }

    private static final double EPS_DEG = 0.05;   // tolerance for angle matching (degrees)
    private static final double EPS_DIST = 1e-9;  // tolerance for "same point" check
    private static final int MAX_STEPS = 100_000; // safety net against pathological loops

    public static List<Integer> findSequence(LaserInfo laser, List<PrismInfo> prisms) {
        List<Integer> result = new ArrayList<>();

        double x = laser.x();
        double y = laser.y();
        double angle = normalize(laser.angle());

        for (int step = 0; step < MAX_STEPS; step++) {
            PrismInfo hit = null;
            double bestDist = Double.MAX_VALUE;

            for (PrismInfo p : prisms) {
                double vx = p.x() - x;
                double vy = p.y() - y;
                double dist = Math.hypot(vx, vy);

                if (dist < EPS_DIST) continue; // same point as current position, skip

                double targetAngle = Math.toDegrees(Math.atan2(vy, vx));
                double diff = normalize(targetAngle - angle);
                double circDiff = Math.min(diff, 360.0 - diff); // shortest angular distance

                if (circDiff < EPS_DEG && dist < bestDist) {
                    bestDist = dist;
                    hit = p;
                }
            }

            if (hit == null) break; // beam exits into empty space

            result.add(hit.id());
            x = hit.x();
            y = hit.y();
            angle = normalize(angle + hit.angle());
        }

        return result;
    }

    private static double normalize(double a) {
        double r = a % 360.0;
        if (r < 0) r += 360.0;
        return r;
    }
}