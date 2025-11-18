package Iface;

@FunctionalInterface
public interface DiscountPolicy {
    double apply(double originalPrice, double discountRate);
}
