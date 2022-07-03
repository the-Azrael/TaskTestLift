import java.util.LinkedList;
import java.util.Queue;

public class Lift {
    private static final String DELIMITER = " -> ";
    private int floorsCount;
    private int waitMoveInSeconds;
    private int waitDoorsInSeconds;
    private Queue<Integer> liftQueue;
    private int totalSeconds = 0;
    private StringBuilder floors;

    public Lift(int floorsCount, int waitMoveInSeconds, int waitDoorsInSeconds) {
        this.floorsCount = floorsCount;
        this.waitMoveInSeconds = waitMoveInSeconds;
        this.waitDoorsInSeconds = waitDoorsInSeconds;
        this.liftQueue = new LinkedList<>();
    }

    public boolean isInFloorRange(int floorNumber) {
        return floorNumber > 0 && floorNumber <= this.floorsCount;
    }

    public void addFloor(int floorNumber) {
        if (isInFloorRange(floorNumber)) {
            liftQueue.offer(floorNumber);
        } else {
            System.out.println(String.format("Такого этажа нет в доме! " +
                    "[ 0 .. %s ] ", floorsCount));
        }
    }

    public void run() {
        totalSeconds = 0;
        floors = new StringBuilder();
        //System.out.println("Лифт проследовал по следующим этажам:");
        int previousFloor = -1;
        while (!liftQueue.isEmpty()) {
            int nextFloor = liftQueue.poll();
            floors.append("этаж " + nextFloor + DELIMITER);
            if (previousFloor != -1) {
                totalSeconds += Math.abs(previousFloor - nextFloor) * waitMoveInSeconds;
            }
            previousFloor = nextFloor;
            totalSeconds += waitDoorsInSeconds;
        }
        floors.replace(floors.length() - DELIMITER.length(), floors.length(), "");
    }

    public int getTotalSeconds() {
        if (totalSeconds == 0) {
            run();
        }
        return totalSeconds;
    }

    public StringBuilder getFloors() {
        if (floors == null) {
            run();
        }
        return floors;
    }

    public void fillFloors(Queue<Integer> liftQueue) {
        this.liftQueue = liftQueue;
    }

    public Queue<Integer> getLiftQueue() {
        return liftQueue;
    }

    public int getFloorsCount() {
        return floorsCount;
    }
}
