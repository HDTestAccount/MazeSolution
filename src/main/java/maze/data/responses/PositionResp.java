package maze.data.responses;

import lombok.Data;

@Data
public class PositionResp extends BaseResponse {
    private Position position;

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    @Data
    static
    class Position {
        int x;
        int y;
    }
}
