
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class RobotMap {

    private final int n;
    private final int m;

    private final Map<UUID, Robot> robots;

    public RobotMap(int n, int m) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException("Недопустимые значения размера карты!");
        }
        this.n = n;
        this.m = m;
        this.robots = new HashMap<>();
    }

    public Optional<Robot> findRobotById(UUID id) {
        return Optional.ofNullable(robots.get(id));
    }

    public Robot createRobot(Point position) throws PositionException {
        checkPosition(position);

        Robot robot = new Robot(position);
        robots.put(robot.id, robot);
        return robot;
    }

    private void checkPosition(Point position) throws PositionException {
        if (position.getX() < 0 || position.getY() < 0 || position.getX() > n || position.getY() > m) {
            throw new PositionException("Некорректное значение точки: " + position);
        }
        else if (!isFree(position)) {
            throw new PositionException("Точка " + position + " занята!");
        }
    }
    public boolean isValidPosition(Point point) {
        return point.getX() >= 0 && point.getX() < RobotMap.this.n && point.getY() >= 0 && point.getY() < RobotMap.this.m;
    }
    private boolean isFree(Point position) {
        return robots.values().stream() // Robot
                .map(Robot::getPosition) // Point
                .noneMatch(position::equals);
    }

    public class Robot {

        private final UUID id;
        private Point position;
        private Direction direction;

        public Robot(Point position) {
            this.id = UUID.randomUUID();
            this.position = position;
            this.direction = Direction.UP;
        }

        public UUID getId() {
            return id;
        }

        public Point getPosition() {
            return position;
        }


        
        public void move() throws PositionException {
            Point newPosition = switch (direction) {
                case UP -> new Point(position.getX() + 1, position.getY());
                case RIGHT -> new Point(position.getX(), position.getY() + 1);
                case DOWN -> new Point(position.getX() - 1, position.getY());
                case LEFT -> new Point(position.getX(), position.getY() - 1);
            };
            if (RobotMap.this.isValidPosition(newPosition)) {
                System.out.println("Текущее расположение робота: " + getPosition());
            }
            if (!RobotMap.this.isValidPosition(newPosition)) {
                throw new PositionException("Невозможно переместить робота за пределы карты");
            }
        
            position = newPosition;
        }
        

        public void changeDirection(Direction direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", id.toString(), position.toString());
        }
    }

    public enum Direction {

        UP, RIGHT, DOWN, LEFT

    }

}
