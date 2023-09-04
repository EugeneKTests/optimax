package optimax.bidder;

import java.util.Random;

/**
 * Adds some random zeros with {@link #zeroProbability} probability to {@link OverAverageBidder} behaviour
 * which makes it less predictable
 */
public class OverAverageWithRandomZerosBidder extends OverAverageBidder {

    private final double zeroProbability;
    private final Random random = new Random();

    public OverAverageWithRandomZerosBidder() {
        this(1.05, 15);
    }

    public OverAverageWithRandomZerosBidder(double overBidCoef, int historySizeForAverage) {
        super(overBidCoef, historySizeForAverage);
        this.zeroProbability = (overBidCoef - 1) * 0.67;
    }

    @Override
    protected int calculateBid() {
        if(random.nextDouble() < zeroProbability) {
            return 0;
        }
        return super.calculateBid();
    }
}
