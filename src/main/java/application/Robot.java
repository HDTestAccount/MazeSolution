package application;

import application.responses.*;
import application.webWork.WebRQ;

import java.util.*;

import static application.utils.JacsonParser.*;

public class Robot {
    WebRQ webrq = new WebRQ();
    Map<String, Integer> cells = new HashMap<String, Integer>();
    StateResp status;
    PositionResp position;
    DirectionResp direction;
    List<DIRECTIONS> list = new ArrayList<>();
    int x, y;

    public PositionResp GetPosition() throws Exception {
        position = (PositionResp) responseParser(webrq.sendGet("position"));
        x = position.getPosition().getX();
        y = position.getPosition().getY();
        return position;
    }

    DIRECTIONS GetAvaibleDirection() throws Exception {
        list.clear();
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

    String GetStatus() throws Exception {
        status = (StateResp) responseParser(webrq.sendGet("state"));
        return status.getState();
    }

    PositionResp Move(DIRECTIONS directionValue) throws Exception {
        String brackets = "\"" + directionValue.toString() + "\"";
        cells.put("x" + x + "y" + y, 1);
//        System.out.println("Move " + position + "  " + direction + "  " + brackets);
        webrq.sendPost("move", brackets);
        return GetPosition();
    }

    PositionResp MoveBack() throws Exception {
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
        String brackets = "\"" + directionToMove + "\"";
        webrq.sendPost("move", brackets);
        return GetPosition();
    }

    void reset() throws Exception {
        webrq.sendPost("reset", "");
    }
}
