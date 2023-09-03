package optimax.bidder;

public class OverAverageBidder extends AbstractBidder {

    protected final double overBidCoef;
    protected final int historySizeForAverage;

    public OverAverageBidder() {
        this(1.05, 15);
    }

    public OverAverageBidder(double overBidCoef, int historySizeForAverage) {
        this.overBidCoef = overBidCoef;
        this.historySizeForAverage = historySizeForAverage;
    }

    @Override
    protected int calculateBid() {
        double otherAverage = bidHistoryRecordHistory.stream().skip(Math.max(0, bidHistoryRecordHistory.size() - historySizeForAverage))
                .mapToInt(BidHistoryRecord::getOtherBid)
                .average().orElse(1);
        return (int) Math.ceil(otherAverage * overBidCoef);
    }
}
