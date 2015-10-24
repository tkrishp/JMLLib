package com.tulc.math;

public class GenericTypeOp<E extends Number> {
    @SuppressWarnings({ "unchecked" })
    public static <E> E sum(E one, E two) {
        if (one.getClass() == Double.class || two.getClass() == Double.class) {
            return (E) (Double) (((Double) one).doubleValue() + ((Double) two).doubleValue());
        } 
        else if (one.getClass() == Long.class || two.getClass() == Long.class) {
            return (E) (Long) (((Long) one).longValue() + ((Long) two).longValue());
        } 
        else
            return (E) (Integer) ((Integer) one + (Integer) two);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <E> E subtract(E one, E two) {
        if (one.getClass() == Double.class || two.getClass() == Double.class) {
            return (E) (Double) (((Double) one).doubleValue() - ((Double) two).doubleValue());
        } 
        else if (one.getClass() == Long.class || two.getClass() == Long.class) {
            return (E) (Long) (((Long) one).longValue() - ((Long) two).longValue());
        } 
        else
            return (E) (Integer) ((Integer) one - (Integer) two);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <E> E multiply(E one, E two) {
        if (one.getClass() == Double.class || two.getClass() == Double.class) {
            return (E) (Double) (((Double) one).doubleValue() * ((Double) two).doubleValue());
        } 
        else if (one.getClass() == Long.class || two.getClass() == Long.class) {
            return (E) (Long) (((Long) one).longValue() * ((Long) two).longValue());
        } 
        else
            return (E) (Integer) ((Integer) one * (Integer) two);
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <E> E divide(E one, E two) {
        if (one.getClass() == Double.class || two.getClass() == Double.class) {
            return (E) (Double) (((Double) one).doubleValue() / ((Double) two).doubleValue());
        } 
        else if (one.getClass() == Long.class || two.getClass() == Long.class) {
            return (E) (Long) (((Long) one).longValue() / ((Long) two).longValue());
        } 
        else
            return (E) (Integer) ((Integer) one / (Integer) two);
    }

}
