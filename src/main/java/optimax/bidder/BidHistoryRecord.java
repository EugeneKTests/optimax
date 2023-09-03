package optimax.bidder;

public class BidHistoryRecord {

    private final int ownBid;
    private final int otherBid;

    public BidHistoryRecord(int ownBid, int otherBid) {
        this.ownBid = ownBid;
        this.otherBid = otherBid;
    }

    public int getOwnBid() {
        return ownBid;
    }

    public int getOtherBid() {
        return otherBid;
    }
}
