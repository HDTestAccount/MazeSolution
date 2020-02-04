package application.responses;

public class responseFactory {

    public baseResponse getResponseTemplate(String endpoint) {
        if (endpoint.contains("East")) {
            return new directionResp();
        } else if (endpoint.contains("State")) {
            return new stateResp();
        } else if (endpoint.contains("Position")) {
            return new positionResp();
        } else {
            return null;
        }
    }
}