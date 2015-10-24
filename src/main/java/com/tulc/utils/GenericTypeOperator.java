package com.tulc.utils;

public class GenericTypeOperator<E extends Number> {
    @SuppressWarnings({ "unchecked" })
    public static <E> E sum(E one, E two) {
      if (one.getClass() == Integer.class) {
        return (E) (Integer) ((Integer) one + (Integer) two);
      } 
      else if (one.getClass() == Long.class) {
        return (E) Long.valueOf(((Long) one).longValue() + ((Long) two).longValue());
      } 
      else if (one.getClass() == Double.class) {
        return (E) Double.valueOf(((Double) one).doubleValue() + ((Double) two).doubleValue());
      }
      return one;
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <E> E subtract(E one, E two) {
      if (one.getClass() == Integer.class) {
        return (E) (Integer) ((Integer) one - (Integer) two);
      } 
      else if (one.getClass() == Long.class) {
        return (E) Long.valueOf(((Long) one).longValue() - ((Long) two).longValue());
      } 
      else if (one.getClass() == Double.class) {
        return (E) Double.valueOf(((Double) one).doubleValue() - ((Double) two).doubleValue());
      }
      return one;
    }
    
    @SuppressWarnings({ "unchecked" })
    public static <E> E multiply(E one, E two) {
      if (one.getClass() == Integer.class) {
        return (E) (Integer) ((Integer) one * (Integer) two);
      } 
      else if (one.getClass() == Long.class) {
        return (E) Long.valueOf(((Long) one).longValue() * ((Long) two).longValue());
      } 
      else if (one.getClass() == Double.class) {
        return (E) Double.valueOf(((Double) one).doubleValue() * ((Double) two).doubleValue());
      }
      return one;
    }

}
