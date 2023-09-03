package optimax.bidder;

public class OverBidder extends AbstractBidder {

    private final double overBidCoef;

    public OverBidder() {
        this(1.1);
    }

    public OverBidder(double overBidCoef) {
        this.overBidCoef = overBidCoef;
    }

    @Override
    protected int calculateBid() {
        BidHistoryRecord lastBid = !bidHistoryRecordHistory.isEmpty() ?
                bidHistoryRecordHistory.get(bidHistoryRecordHistory.size() - 1) : null;
        return lastBid == null ? currentOwnCash / (currentQuantity / 2) : (int) (lastBid.getOtherBid() * overBidCoef);
    }

}
