package movie.model.pay;

public enum Payment {
    CreditCard(0.05), Cash(0.02);

    private final double discountRate;

    Payment(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }
}
