package optimax;

import optimax.bidder.BidHistoryRecord;
import optimax.bidder.RandomBidder;
import optimax.bidder.SimpleBidder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BidderTest {

    @Test
    void simpleBidderPlaceSimpleBid() {
        Auction auction = new Auction(new SimpleBidder(), new SimpleBidder());
        auction.init(100, 1000);
        BidHistoryRecord historyRecord = auction.playOneRound();
        Assertions.assertEquals(20, historyRecord.ownBid());
        Assertions.assertEquals(20, historyRecord.otherBid());
    }

    @Test
    void bidderPlacesAllMoney() {
        Auction auction = new Auction(new RandomBidder(), new RandomBidder());
        auction.init(100, 10000);
        int totalBid1 = 0;
        int totalBid2 = 0;
        while (!auction.isFinished()) {
            BidHistoryRecord historyRecord = auction.playOneRound();
            totalBid1 += historyRecord.ownBid();
            totalBid2 += historyRecord.otherBid();
        }
        Assertions.assertEquals(10000, totalBid1);
        Assertions.assertEquals(10000, totalBid2);
    }
}
