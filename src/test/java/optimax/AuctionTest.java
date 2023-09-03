package optimax;

import optimax.bidder.SimpleBidder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuctionTest {

    @Test
    void noBidsPlacedWhenQuantityLessThen2() {
        Auction auction = new Auction(new SimpleBidder(), new SimpleBidder());
        auction.init(1, 1000);
        var bids = auction.playOneRound();
        Assertions.assertNull(bids);
    }

    @Test
    void auctionFinishedAfterPlayedTillEnd() {
        Auction auction = new Auction(new SimpleBidder(), new SimpleBidder());
        auction.init(100, 100000);
        auction.playTillEnd();
        Assertions.assertTrue(auction.isFinished());
    }
}
