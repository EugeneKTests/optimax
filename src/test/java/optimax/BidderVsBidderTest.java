package optimax;

import optimax.bidder.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BidderVsBidderTest {

    @Test
    void simpleBiddersDraw() {
        Bidder bidder1 = new SimpleBidder();
        Bidder bidder2 = new SimpleBidder();
        Auction auction = new Auction(bidder1, bidder2);
        auction.init(100, 1000);
        auction.playTillEnd();
        Assertions.assertEquals(50, auction.getBidder2QuantityUnits());
        Assertions.assertEquals(50, auction.getBidder2QuantityUnits());
    }

    @Test
    void overBidderVsSimpleBidder() {
        Bidder bidder1 = new OverBidder();
        Bidder bidder2 = new SimpleBidder();
        Auction auction = new Auction(bidder1, bidder2);
        auction.init(1000, 5000000);
        auction.playTillEnd();
        Assertions.assertTrue(auction.getBidder1QuantityUnits() > auction.getBidder2QuantityUnits());
    }

    @Test
    void overAverageBidderVsRandomBidder() {
        int win1 = 0;
        int win2 = 0;
        for (int i = 1; i <= 20000; i++) {
            Bidder bidder1 = new OverAverageBidder(1.05, 15);
            Bidder bidder2 = new RandomBidder();
            Auction auction = new Auction(bidder1, bidder2);
            auction.init(1000, Integer.MAX_VALUE);
            auction.playTillEnd();
            if (auction.getBidder1QuantityUnits() > auction.getBidder2QuantityUnits()) {
                win1++;
            } else if (auction.getBidder1QuantityUnits() < auction.getBidder2QuantityUnits()) {
                win2++;
            }
        }
        System.out.println(win1 + " : " + win2);
        Assertions.assertTrue(win1 > win2);
    }

    @Test
    void overAverageWithZerosBidderVsRandomBidder() {
        int win1 = 0;
        int win2 = 0;
        for (int i = 1; i <= 20000; i++) {
            Bidder bidder1 = new OverAverageWithRandomZerosBidder(1.05, 15);
            Bidder bidder2 = new RandomBidder();
            Auction auction = new Auction(bidder1, bidder2);
            auction.init(1000, Integer.MAX_VALUE);
            auction.playTillEnd();
            if (auction.getBidder1QuantityUnits() > auction.getBidder2QuantityUnits()) {
                win1++;
            } else if (auction.getBidder1QuantityUnits() < auction.getBidder2QuantityUnits()) {
                win2++;
            }
        }
        System.out.println(win1 + " : " + win2);
        Assertions.assertTrue(win1 > win2);
    }

    @Test
    void overAverageWithZerosVsOverAverage() {
        int win1 = 0;
        int win2 = 0;
        for (int i = 1; i <= 10; i++) {
            Bidder bidder1 = new OverAverageWithRandomZerosBidder(1.05, 15);
            Bidder bidder2 = new OverAverageBidder(1.05, 15);
            Auction auction = new Auction(bidder1, bidder2);
            auction.init(1000, Integer.MAX_VALUE);
            auction.playTillEnd();
            if (auction.getBidder1QuantityUnits() > auction.getBidder2QuantityUnits()) {
                win1++;
            } else if (auction.getBidder1QuantityUnits() < auction.getBidder2QuantityUnits()) {
                win2++;
            }
        }
        System.out.println(win1 + " : " + win2);
        Assertions.assertTrue(win1 > win2);
    }

    @Test
    void customVsRandom() {
        int win1 = 0;
        int win2 = 0;
        for (int i = 1; i <= 10000; i++) {
            Bidder bidder1 = new CustomBidder(1.05, 10);
            Bidder bidder2 = new RandomBidder();
            Auction auction = new Auction(bidder1, bidder2);
            auction.init(1000, Integer.MAX_VALUE);
            auction.playTillEnd();
            if (auction.getBidder1QuantityUnits() > auction.getBidder2QuantityUnits()) {
                win1++;
            } else if (auction.getBidder1QuantityUnits() < auction.getBidder2QuantityUnits()) {
                win2++;
            }
        }
        System.out.println(win1 + " : " + win2);
        Assertions.assertTrue(win1 > win2);
    }

    @Test
    void customVsOverAverage() {
        int win1 = 0;
        int win2 = 0;
        for (int i = 1; i <= 10000; i++) {
            Bidder bidder1 = new CustomBidder(1.05, 10);
            Bidder bidder2 = new OverAverageBidder(1.05, 10);
            Auction auction = new Auction(bidder1, bidder2);
            auction.init(1000, Integer.MAX_VALUE);
            auction.playTillEnd();
            if (auction.getBidder1QuantityUnits() > auction.getBidder2QuantityUnits()) {
                win1++;
            } else if (auction.getBidder1QuantityUnits() < auction.getBidder2QuantityUnits()) {
                win2++;
            }
        }
        System.out.println(win1 + " : " + win2);
        Assertions.assertTrue(win1 > win2);
    }

    @Test
    void customVsOverAverageWithZeros() {
        int win1 = 0;
        int win2 = 0;
        for (int i = 1; i <= 10000; i++) {
            Bidder bidder1 = new CustomBidder(1.05, 10);
            Bidder bidder2 = new OverAverageWithRandomZerosBidder(1.05, 10);
            Auction auction = new Auction(bidder1, bidder2);
            auction.init(1000, Integer.MAX_VALUE);
            auction.playTillEnd();
            if (auction.getBidder1QuantityUnits() > auction.getBidder2QuantityUnits()) {
                win1++;
            } else if (auction.getBidder1QuantityUnits() < auction.getBidder2QuantityUnits()) {
                win2++;
            }
        }
        System.out.println(win1 + " : " + win2);
        Assertions.assertTrue(win1 > win2);
    }
}
