package application.responses;

public class directionResp extends baseResponse {
    public boolean North;
    public boolean South;
    public boolean East;
    public boolean West;

    @Override
    public String toString() {
        return "North:" + North + " South:" + South + " East:" + East + " West:" + West + " ";
    }

    public boolean getNorth() {
        return North;
    }

    public void setNorth(boolean north) {
        North = north;
    }

    public boolean getSouth() {
        return South;
    }

    public void setSouth(boolean south) {
        South = south;
    }

    public boolean getEast() {
        return East;
    }

    public void setEast(boolean east) {
        East = east;
    }

    public boolean getWest() {
        return West;
    }

    public void setWest(boolean west) {
        West = west;
    }

}
