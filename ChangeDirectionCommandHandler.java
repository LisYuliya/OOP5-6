import java.util.UUID;

public class ChangeDirectionCommandHandler implements CommandHandler {

    @Override
    public String commandName() {
        return "change-direction";
    }

    @Override
    public void handleCommand(RobotMap map, String[] args) {
        UUID robotId = UUID.fromString(args[0]);
        RobotMap.Direction newDirection = RobotMap.Direction.valueOf(args[1]);

        RobotMap.Robot robot = map.findRobotById(robotId).orElseThrow();
        robot.changeDirection(newDirection);
    }

}
