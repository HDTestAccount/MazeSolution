package application.responses;

public class DirectionResp extends BaseResponse {
    public boolean north;
    public boolean south;
    public boolean east;
    public boolean west;

    @Override
    public String toString() {
        return "North:" + north + " South:" + south + " East:" + east + " West:" + west + " ";
    }

    public boolean getNorth() {
        return north;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }

    public boolean getSouth() {
        return south;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public boolean getEast() {
        return east;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public boolean getWest() {
        return west;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

}
