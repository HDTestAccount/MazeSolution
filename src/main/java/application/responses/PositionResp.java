package application.responses;

public class PositionResp extends BaseResponse {
    private Position positionObject;

    public Position getPosition() {
        return positionObject;
    }

    @Override
    public String toString() {
        return positionObject.toString();
    }

    public void setPosition(Position positionObject) {
        this.positionObject = positionObject;
    }
}
