/**
 * @author huisheng.jin
 * @version 2019/12/21.
 */
public class ParkingLot {
    private int parkingSpaceNumber;
    private int takeUpNumber;

    public ParkingLot(int parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
        this.takeUpNumber = 0;
    }

    public Boolean park() {
        if (parkingSpaceNumber == takeUpNumber) {
            return false;
        }
        this.takeUpNumber += 1;
        return true;
    }

    public Boolean takeout() {
        if (takeUpNumber == 0) {
            return false;
        }
        takeUpNumber -= 1;
        return true;
    }

    Integer getFreeCount() {
        return parkingSpaceNumber - takeUpNumber;
    }
}