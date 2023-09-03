package optimax;

import optimax.bidder.BidHistoryRecord;
import optimax.bidder.Bidder;

public class Auction {

    private final Bidder bidder1;
    private final Bidder bidder2;
    private int currentQuantityUnits;
    private int bidder1QuantityUnits;
    private int bidder2QuantityUnits;

    public Auction(Bidder bidder1, Bidder bidder2) {
        this.bidder1 = bidder1;
        this.bidder2 = bidder2;
    }

    public void init(int quantity, int cash) {
        bidder1.init(quantity, cash);
        bidder2.init(quantity, cash);
        currentQuantityUnits = quantity;
        bidder1QuantityUnits = bidder2QuantityUnits = 0;
    }

    public BidHistoryRecord playOneRound() {
        if (isFinished()) {
            return null;
        }
        int bid1 = bidder1.placeBid();
        int bid2 = bidder2.placeBid();
        bidder1.bids(bid1, bid2);
        bidder2.bids(bid2, bid1);
        determineWinner(bid1, bid2);
        currentQuantityUnits -= 2;
        return new BidHistoryRecord(bid1, bid2);
    }

    public void playTillEnd() {
        while (!isFinished()) {
            playOneRound();
        }
    }

    private void determineWinner(int bid1, int bid2) {
        if (bid1 == bid2) {
            bidder1QuantityUnits += 1;
            bidder2QuantityUnits += 1;
        } else if (bid1 > bid2) {
            bidder1QuantityUnits += 2;
        } else {
            bidder2QuantityUnits += 2;
        }
    }

    public boolean isFinished() {
        return currentQuantityUnits < 2;
    }

    public int getBidder1QuantityUnits() {
        return bidder1QuantityUnits;
    }

    public void setBidder1QuantityUnits(int bidder1QuantityUnits) {
        this.bidder1QuantityUnits = bidder1QuantityUnits;
    }

    public int getBidder2QuantityUnits() {
        return bidder2QuantityUnits;
    }

    public void setBidder2QuantityUnits(int bidder2QuantityUnits) {
        this.bidder2QuantityUnits = bidder2QuantityUnits;
    }
}
