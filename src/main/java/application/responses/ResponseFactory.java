package application.responses;

public class ResponseFactory {

    public BaseResponse getResponseTemplate(String endpoint) {

        if (endpoint.contains("East")) {
            return new DirectionResp();
        } else if (endpoint.contains("State")) {
            return new StateResp();
        } else if (endpoint.contains("Position")) {
            return new PositionResp();
        } else {
            return null;
        }
    }
}