package optimax.bidder;

import java.util.Random;

/**
 * Random bidder
 * places random bid between 0 and averageBid * 2
 * used as benchmark for other bidders
 */
public class RandomBidder extends AbstractBidder {

    private final Random random = new Random();

    @Override
    protected int calculateBid() {
        return random.nextInt((currentOwnCash / (currentQuantity / 2)) * 2);
    }
}
