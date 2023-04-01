import java.util.UUID;

public class MoveRobotCommandHandler implements CommandHandler {
    @Override
    public String commandName() {
        return "move-robot";
    }
    
    @Override
    public void handleCommand(RobotMap map, String[] args) {
        try {
            UUID robotId = UUID.fromString(args[0]);
    
            RobotMap.Robot robot = map.findRobotById(robotId)
                                       .orElseThrow(() -> new IllegalArgumentException("Такой робот не найден"));
            robot.move();
        } catch (IllegalArgumentException | PositionException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
}
}

