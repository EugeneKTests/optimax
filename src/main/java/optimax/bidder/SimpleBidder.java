package optimax.bidder;

/**
 * Simple bidder
 * places equal bids every round
 */
public class SimpleBidder extends AbstractBidder {
    @Override
    protected int calculateBid() {
        return currentOwnCash / (currentQuantity / 2);
    }
}
