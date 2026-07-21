class Clock {

    private int hours;
    private int minutes;

    Clock(int hours, int minutes) {
        int totalMinutes = hours * 60 + minutes;
        totalMinutes = ((totalMinutes % 1440) + 1440) % 1440;

        this.hours = totalMinutes / 60;
        this.minutes = totalMinutes % 60;
    }

    void add(int minutes) {
        int totalMinutes = this.hours * 60 + this.minutes + minutes;
        totalMinutes = ((totalMinutes % 1440) + 1440) % 1440;

        this.hours = totalMinutes / 60;
        this.minutes = totalMinutes % 60;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Clock)) {
            return false;
        }

        Clock other = (Clock) obj;
        return this.hours == other.hours && this.minutes == other.minutes;
    }

    @Override
    public int hashCode() {
        return hours * 60 + minutes;
    }
}