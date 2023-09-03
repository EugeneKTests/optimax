package optimax.bidder;

import java.util.Random;

public class RandomBidder extends AbstractBidder {

    private final Random random = new Random();

    @Override
    protected int calculateBid() {
        return random.nextInt((currentOwnCash / (currentQuantity / 2)) * 2);
    }
}
