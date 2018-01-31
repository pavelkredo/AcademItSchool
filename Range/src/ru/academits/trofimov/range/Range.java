package ru.academits.trofimov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getIntervalLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getRangeCrossing(Range ob) {
        if (ob.from >= to || ob.to <= from) {
            return null;
        } else {
            Range newRange;

            if (ob.from < from) {
                if (ob.to < to) {
                    newRange = new Range(from, ob.to);
                } else {
                    newRange = new Range(from, to);
                }
            } else {
                if (ob.to < to) {
                    newRange = new Range(ob.from, ob.to);
                } else {
                    newRange = new Range(ob.from, to);
                }
            }

            return newRange;
        }
    }

    public Range[] getRangeCombination(Range ob) {
        Range[] newRange = new Range[2];

        if (to < ob.from || ob.to < from) {
            newRange[0] = new Range(from, to);
            newRange[1] = new Range(ob.from, ob.to);
        } else {
            if (from < ob.from) {
                if (to < ob.to) {
                    newRange[0] = new Range(from, ob.to);
                } else {
                    newRange[0] = new Range(from, to);
                }
            } else {
                if (to < ob.to) {
                    newRange[0] = new Range(ob.from, ob.to);
                } else {
                    newRange[0] = new Range(ob.from, to);
                }
            }
        }

        return newRange;
    }

    public Range[] getRangeDifference(Range ob) {
        Range[] newRange = new Range[2];

        if (from > ob.from && to < ob.to) {
            return null;
        } else if (from > ob.to || to < ob.from) {
            newRange[0] = new Range(from, to);
            return newRange;
        } else if (from < ob.from && to > ob.to) {
            newRange[0] = new Range(from, ob.from);
            newRange[1] = new Range(ob.to, to);
        } else {
            if (from < ob.from) {
                newRange[0] = new Range(from, ob.from);
            } else {
                newRange[0] = new Range(ob.to, to);
            }
        }

        return newRange;
    }

    public void print() {
        System.out.print("(" + from + ", " + to + ")");
    }
}