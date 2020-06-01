package by.yason.maze.data.responses;

public class ResponseFactory {
    /**
     * check response string and return current type of response
     *
     * @param responseString
     * @return
     */
    public BaseResponse getResponseTemplateByType(String responseString) {

        if (responseString.contains("East")) {
            return new DirectionResp();
        } else if (responseString.contains("State")) {
            return new StateResp();
        } else if (responseString.contains("Position")) {
            return new PositionResp();
        } else {
            return null;
        }
    }
}