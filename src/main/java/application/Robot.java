package application;

import application.responses.*;
import application.webWork.WebRQ;

import java.util.*;

import static application.utils.JacsonParser.*;

public class Robot {
    WebRQ webrq = new WebRQ();
    Map<String, Integer> cells = new HashMap<String, Integer>();
    stateResp status;
    positionResp position;
    directionResp direction;
    List<DIRECTIONS> list = new ArrayList<>();
    int x, y;

    public positionResp GetPosition() throws Exception {
        position = (positionResp) responseParser(webrq.sendGet("position"));
        x = position.getPosition().getX();
        y = position.getPosition().getY();
        return position;
    }

    DIRECTIONS GetAvaibleDirection() throws Exception {
        list.clear();
        String respString = webrq.sendGet("directions");
        direction = (directionResp) responseParser(respString);
        if (direction.East && !cells.containsKey("x" + (x + 1) + "y" + y)) {
            return DIRECTIONS.East;
        }
        if (direction.North && !cells.containsKey("x" + x + "y" + (y - 1))) {
            return DIRECTIONS.North;
        }
        if (direction.West && !cells.containsKey("x" + (x - 1) + "y" + y)) {
            return DIRECTIONS.West;
        }
        if (direction.South && !cells.containsKey("x" + x + "y" + (y + 1))) {
            return DIRECTIONS.South;
        }
        return null;
    }

    String GetStatus() throws Exception {
        status = (stateResp) responseParser(webrq.sendGet("state"));
        return status.getState();
    }

    positionResp Move(DIRECTIONS directionValue) throws Exception {
        String brackets = "\"" + directionValue.toString() + "\"";
        cells.put("x" + x + "y" + y, 1);
        System.out.println("Move " + position + "  " + direction + "  " + brackets);
        webrq.sendPost("move", brackets);
        return GetPosition();
    }

    positionResp MoveBack() throws Exception {
        String qwe = "";
        if (direction.East && cells.get("x" + (x + 1) + "y" + y).equals(1)) {
            qwe = DIRECTIONS.East.toString();
        }
        if (direction.North && cells.get("x" + x + "y" + (y - 1)).equals(1)) {
            qwe = DIRECTIONS.North.toString();
        }
        if (direction.West && cells.get("x" + (x - 1) + "y" + y).equals(1)) {
            qwe = DIRECTIONS.West.toString();
        }
        if (direction.South && cells.get("x" + x + "y" + (y + 1)).equals(1)) {
            qwe = DIRECTIONS.South.toString();
        }
        cells.put("x" + x + "y" + y, 2);
        String brackets = "\"" + qwe + "\"";
        System.out.println("MoveBack " + position + "  " + direction + "  " + qwe);
        webrq.sendPost("move", brackets);
        return GetPosition();
    }

    void reset() throws Exception {
        webrq.sendPost("reset", "");
    }
}
