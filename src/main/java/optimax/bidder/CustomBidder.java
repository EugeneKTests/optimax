package optimax.bidder;

import java.util.Random;

/**
 * bidding logic is similar to {@link OverAverageWithRandomZerosBidder}
 * never makes a bid more than two times bigger than average
 * increases bid if too many quantity units left to increase probability of win
 */
public class CustomBidder extends AbstractBidder {

    private final double overBidCoef;
    private final int historySizeForAverage;
    private final double zeroProbability;
    private final Random random = new Random();

    public CustomBidder() {
        this(1.05, 15);
    }

    public CustomBidder(double overBidCoef, int historySizeForAverage) {
        this.overBidCoef = overBidCoef;
        this.historySizeForAverage = historySizeForAverage;
        this.zeroProbability = (overBidCoef - 1) * 0.5;
    }

    @Override
    protected int calculateBid() {
        if (random.nextDouble() < zeroProbability) {
            return 0;
        }
        double otherAverage = bidHistoryRecordHistory.stream().skip(Math.max(0, bidHistoryRecordHistory.size() - historySizeForAverage))
                .mapToInt(BidHistoryRecord::otherBid)
                .average().orElse(1);
        int bid = (int) Math.ceil(otherAverage * overBidCoef);
        if ((bid > averageBid() * 2) && (bid > currentAverageBid())) {
            return 0;
        }
        int currentAverageBid = currentAverageBid();
        if (bid < currentAverageBid()) {
            return bid + (int) (0.3 * (currentAverageBid - bid));
        }
        return bid;
    }

    private int currentAverageBid() {
        return currentOwnCash / (currentQuantity / 2);
    }

    private int averageBid() {
        return initialCash / (initialQuantity / 2);
    }
}
