package ru.academits.trofimov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public void print() {
        System.out.print("(" + from + ", " + to + ")");
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range ob) {
        double minTo = Math.min(to, ob.to);
        double maxFrom = Math.max(from, ob.from);

        if (maxFrom < minTo) {
            return new Range(maxFrom, minTo);
        }

        return null;
    }

    public Range[] getUnion(Range ob) {
        double minFrom = Math.min(from, ob.from);
        double maxTo = Math.max(to, ob.to);

        if (Math.min(to, ob.to) < Math.max(from, ob.from)) {
            return new Range[]{new Range(from, to), new Range(ob.from, ob.to)};
        }

        return new Range[]{new Range(minFrom, maxTo)};
    }

    public Range[] getDifference(Range ob) {
        if ((from > ob.from && to < ob.to) || (from == ob.from && to == ob.to)) {
            return new Range[]{};
        } else if (from > ob.to || to < ob.from) {
            return new Range[]{new Range(from, to)};
        } else if (from < ob.from && to > ob.to) {
            return new Range[]{new Range(from, ob.from), new Range(ob.to, to)};
        }

        if (from < ob.from) {
            return new Range[]{new Range(from, ob.from)};
        } else {
            return new Range[]{new Range(ob.to, to)};
        }
    }
}