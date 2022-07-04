import org.junit.jupiter.api.*;
import java.util.*;

public class LiftTest {
    public static final int TEST_FLOORS_COUNT = 10;
    public static final int TEST_SPEED_FAST = 2;
    public static final int TEST_DOOR_SPEED_FAST = 5;
    public static final int TEST_SPEED_SLOW = 5;
    public static final int TEST_DOOR_SPEED_SLOW = 10;
    public static final int TEST_SPEED_MIDDLE = 3;
    public static final int TEST_DOOR_SPEED_MIDDLE = 8;
    public static final Integer[] ALL_FLOORS_WAY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static Lift liftFast = new Lift(TEST_FLOORS_COUNT, TEST_SPEED_FAST, TEST_DOOR_SPEED_FAST);
    public static Lift liftSlow = new Lift(TEST_FLOORS_COUNT, TEST_SPEED_SLOW, TEST_DOOR_SPEED_SLOW);
    public static Lift liftMiddle = new Lift(TEST_FLOORS_COUNT, TEST_SPEED_MIDDLE, TEST_DOOR_SPEED_MIDDLE);
    public static HashSet<Lift> liftSet =  new HashSet<>();
    private static final String DELIMITER = " -> ";
    
    @BeforeAll
    public static void testsStart() {
        System.out.println("Tests started");
        liftSet.add(liftFast);
        liftSet.add(liftSlow);
        liftSet.add(liftMiddle);
    }

    @BeforeEach
    public void testStart() {
        System.out.println("Test started");
        for (Lift lift : liftSet) {
            lift.fillFloors(new LinkedList<>(Arrays.asList(ALL_FLOORS_WAY)));
            lift.run();
        }
    }

    @AfterAll
    public static  void testsEnd() {
        System.out.println("Tests completed");
    }

    @AfterEach
    public void testEnd() {
        System.out.println("Test complete");
    }

    @Test
    public void testFloorsCount() {
        System.out.println("Test testFloorsCount running...");
        for (Lift lift : liftSet) {
            Assertions.assertEquals(TEST_FLOORS_COUNT, lift.getFloorsCount());
        }
    }

    @Test
    public void testTotalSeconds() {
        System.out.println("Test testTotalSeconds running...");
        for (Lift actualLift : liftSet) {
            Lift expectedLift = new Lift(actualLift.getFloorsCount()
                    , actualLift.getWaitMoveInSeconds()
                    , actualLift.getWaitDoorsInSeconds());
            expectedLift.fillFloors(new LinkedList<>(Arrays.asList(ALL_FLOORS_WAY)));
            int totalSeconds = 0;
            int previousFloor = -1;
            while (!expectedLift.getLiftQueue().isEmpty()) {
                int nextFloor = expectedLift.getLiftQueue().poll();
                if (previousFloor != -1) {
                    totalSeconds += Math.abs(previousFloor - nextFloor) * expectedLift.getWaitMoveInSeconds();
                }
                previousFloor = nextFloor;
                totalSeconds += expectedLift.getWaitDoorsInSeconds();
            }
            Assertions.assertEquals(totalSeconds, actualLift.getTotalSeconds());
        }
    }

    @Test
    public void testFloorsWay() {
        System.out.println("Test testFloorsWay running...");
        for (Lift actualLift : liftSet) {
            Lift expectedLift = new Lift(actualLift.getFloorsCount()
                    , actualLift.getWaitMoveInSeconds()
                    , actualLift.getWaitDoorsInSeconds());
            expectedLift.fillFloors(new LinkedList<>(Arrays.asList(ALL_FLOORS_WAY)));
            StringBuilder floors = new StringBuilder();
            while (!expectedLift.getLiftQueue().isEmpty()) {
                int nextFloor = expectedLift.getLiftQueue().poll();
                floors.append("этаж " + nextFloor + DELIMITER);
            }
            floors.replace(floors.length() - DELIMITER.length(), floors.length(), "");
            String actualStr = actualLift.getFloors().toString();
            String expectedStr = floors.toString();
            System.out.println(actualStr);
            Assertions.assertEquals(expectedStr, actualStr);
        }
    }
}
