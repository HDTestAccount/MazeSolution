package application;

public class MazeSolution {

    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        DIRECTIONS currDirections;
        if (robot.GetStatus().contains("Failed") || robot.GetStatus().contains("TargetReached")) {
            robot.reset();
        }

        while (!robot.GetStatus().contains("TargetReached")) {
            if (robot.GetStatus().contains("Failed")) {
                robot.reset();
            }

            currDirections = robot.GetAvaibleDirection();
            if (currDirections != null) {
                robot.Move(currDirections);
            } else {
                robot.MoveBack();
            }
        }
        System.out.println("Win");
    }
}
