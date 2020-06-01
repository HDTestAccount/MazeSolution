package by.yason.maze;

import by.yason.maze.data.DIRECTIONS;
import by.yason.maze.data.responses.DirectionResp;
import by.yason.maze.data.responses.PositionResp;
import by.yason.maze.data.responses.StateResp;
import by.yason.maze.utils.webWork.WebRQ;

import java.util.HashMap;
import java.util.Map;

import static by.yason.maze.utils.jsonWork.JacsonParser.responseParser;

public class mazeSover {
    WebRQ webrq = new WebRQ();
    Map<String, Integer> cells = new HashMap<String, Integer>();
    StateResp status;
    PositionResp positionResp;
    DirectionResp direction;
    int x, y;

    /**
     * returned current position
     *
     * @return current position (x,y)
     * @throws Exception
     */
    public PositionResp getPositionResp() throws Exception {
        positionResp = (PositionResp) responseParser(webrq.sendGet("position"));
        x = positionResp.getX();
        y = positionResp.getY();
        return positionResp;
    }

    /**
     * check not visited avaible directions if not exist return null
     *
     * @return avaible direction for move
     * @throws Exception
     */
    DIRECTIONS getAvaibleDirection() throws Exception {
        String respString = webrq.sendGet("directions");
        direction = (DirectionResp) responseParser(respString);
        if (direction.east && !cells.containsKey("x" + (x + 1) + "y" + y)) {
            return DIRECTIONS.East;
        }
        if (direction.north && !cells.containsKey("x" + x + "y" + (y - 1))) {
            return DIRECTIONS.North;
        }
        if (direction.west && !cells.containsKey("x" + (x - 1) + "y" + y)) {
            return DIRECTIONS.West;
        }
        if (direction.south && !cells.containsKey("x" + x + "y" + (y + 1))) {
            return DIRECTIONS.South;
        }
        return null;
    }

    /**
     * check current status of by.yason.maze solution
     *
     * @return current status (win, failed, in progress)
     * @throws Exception
     */
    String getStatus() throws Exception {
        status = (StateResp) responseParser(webrq.sendGet("state"));
        return status.getState();
    }

    /**
     * mark position as one visited and moving to direction
     *
     * @param directionValue
     * @return
     * @throws Exception
     */
    PositionResp move(DIRECTIONS directionValue) throws Exception {
        String requestBody = "\"" + directionValue.toString() + "\"";
        cells.put("x" + x + "y" + y, 1);
        webrq.sendPost("move", requestBody);
        return getPositionResp();
    }

    /**
     * mark position as two visited and moving to direction
     *
     * @return position after move one step back
     * @throws Exception
     */
    PositionResp moveBack() throws Exception {
        String directionToMove = "";
        if (direction.east && cells.get("x" + (x + 1) + "y" + y).equals(1)) {
            directionToMove = DIRECTIONS.East.toString();
        }
        if (direction.north && cells.get("x" + x + "y" + (y - 1)).equals(1)) {
            directionToMove = DIRECTIONS.North.toString();
        }
        if (direction.west && cells.get("x" + (x - 1) + "y" + y).equals(1)) {
            directionToMove = DIRECTIONS.West.toString();
        }
        if (direction.south && cells.get("x" + x + "y" + (y + 1)).equals(1)) {
            directionToMove = DIRECTIONS.South.toString();
        }
        cells.put("x" + x + "y" + y, 2);
        String requestBody = "\"" + directionToMove + "\"";
        webrq.sendPost("move", requestBody);
        return getPositionResp();
    }

    /**
     * reset by.yason.maze status to begin
     *
     * @throws Exception
     */
    void reset() throws Exception {
        webrq.sendPost("reset", "");
    }

    /**
     * by.yason.maze solving main method
     *
     * @throws Exception
     */
    public void doMagic() throws Exception {
        DIRECTIONS currDirections;
        if (getStatus().contains("Failed") || getStatus().contains("TargetReached")) {
            reset();
        }
        String status = getStatus();
        while (!status.contains("TargetReached")) {
            if (status.contains("Failed")) {
                reset();
            }

            currDirections = getAvaibleDirection();
            if (currDirections != null) {
                move(currDirections);
            } else {
                moveBack();
            }

            status = getStatus();
        }
        System.out.println("Win");
    }
}
