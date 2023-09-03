package optimax.bidder;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBidder implements Bidder {

    protected int initialQuantity;
    protected int currentQuantity;
    protected int initialCash;
    protected int currentOwnCash;
    protected int currentOtherCash;
    protected List<BidHistoryRecord> bidHistoryRecordHistory = new ArrayList<>();

    @Override
    public void init(int quantity, int cash) {
        initialQuantity = currentQuantity = quantity;
        initialCash = currentOwnCash = currentOtherCash = cash;
        bidHistoryRecordHistory.clear();
    }

    @Override
    public int placeBid() {
        if (isLastRound()) {
            return currentOwnCash;
        }
        return Math.min(Math.max(calculateBid(), 0), currentOwnCash);
    }

    protected abstract int calculateBid();

    @Override
    public void bids(int own, int other) {
        currentOwnCash -= own;
        currentOtherCash -= other;
        currentQuantity -= 2;
        bidHistoryRecordHistory.add(new BidHistoryRecord(own, other));
    }

    private boolean isLastRound() {
        return currentQuantity <= 2;
    }
}
