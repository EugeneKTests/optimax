package optimax.bidder;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of {@link Bidder} interface
 * implements basic logic like tracking history and counting own and opponent cash
 */
public abstract class AbstractBidder implements Bidder {

    protected int initialQuantity;
    protected int currentQuantity;
    protected int initialCash;
    protected int currentOwnCash;
    protected int currentOtherCash;
    protected List<BidHistoryRecord> bidHistoryRecordHistory = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(int quantity, int cash) {
        if (quantity < 0 || cash < 0) {
            throw new IllegalArgumentException();
        }
        initialQuantity = currentQuantity = quantity;
        initialCash = currentOwnCash = currentOtherCash = cash;
        bidHistoryRecordHistory.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int placeBid() {
        if (isLastRound()) {
            return currentOwnCash;
        }
        return Math.min(Math.max(calculateBid(), 0), currentOwnCash);
    }

    /**
     * Calculates next bid
     *
     * @return next bid
     */
    protected abstract int calculateBid();

    /**
     * {@inheritDoc}
     */
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
