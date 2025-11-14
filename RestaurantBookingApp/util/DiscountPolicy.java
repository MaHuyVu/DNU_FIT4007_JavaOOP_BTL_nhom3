package util;

@FunctionalInterface
public interface DiscountPolicy {
    double apply(double originalPrice, double discountRate);
}