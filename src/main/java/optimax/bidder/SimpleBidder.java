package optimax.bidder;

public class SimpleBidder extends AbstractBidder {
    @Override
    protected int calculateBid() {
        return currentOwnCash / (currentQuantity / 2);
    }
}
