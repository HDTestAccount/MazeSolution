package application.responses;

public class positionResp extends baseResponse {
    private position PositionObject;

    public position getPosition() {
        return PositionObject;
    }

    @Override
    public String toString() {
        return PositionObject.toString();
    }

    public void setPosition(position PositionObject) {
        this.PositionObject = PositionObject;
    }
}
