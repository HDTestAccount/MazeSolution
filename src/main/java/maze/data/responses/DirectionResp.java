package maze.data.responses;

import lombok.Data;

@Data
public class DirectionResp extends BaseResponse {
    public boolean north;
    public boolean south;
    public boolean east;
    public boolean west;
}
