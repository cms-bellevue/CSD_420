/*
 Clint Scott
 CSD 402
 Fan Manager – Automated Test Suite for FanDAO
 10/05/2025

 This class contains a set of automated tests for the FanDAO class.
 It runs without external libraries like JUnit and provides clear PASS/FAIL results.
 It verifies the correctness of the following core data operations:
  - Retrieving an existing fan by their ID.
  - Handling cases where a fan ID does not exist.
  - Successfully updating a fan's information in the database.
 */

public class FanTest {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("--- Running FanDAO Test Suite ---");

        // Run each test and report the result
        runTest("testShouldRetrieveExistingFan", testShouldRetrieveExistingFan());
        runTest("testShouldReturnNullForNonExistentFan", testShouldReturnNullForNonExistentFan());
        runTest("testShouldUpdateFan", testShouldUpdateFan());

        System.out.println("\n--- Test Summary ---");
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("--------------------");
    }

    // A simple helper method to run a test and print its result
    private static void runTest(String testName, boolean success) {
        if (success) {
            System.out.println("PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("FAIL: " + testName);
            testsFailed++;
        }
    }

    // TEST CASE 1: Verify we can get a fan that we know exists.
    private static boolean testShouldRetrieveExistingFan() {
        FanDAO dao = new FanDAO();
        int existingId = 1; // Assuming a fan with ID=1 exists

        // Action
        Fan fan = dao.getFanById(existingId);

        // Assertion: The fan should not be null and the ID should match.
        if (fan != null && fan.getId() == existingId) {
            return true; // Test passes
        }
        return false; // Test fails
    }

    // TEST CASE 2: Verify the DAO returns null for an ID that doesn't exist.
    private static boolean testShouldReturnNullForNonExistentFan() {
        FanDAO dao = new FanDAO();
        int nonExistentId = 9999;

        // Action
        Fan fan = dao.getFanById(nonExistentId);

        // Assertion: The fan object should be null.
        if (fan == null) {
            return true; // Test passes
        }
        return false; // Test fails
    }

    // TEST CASE 3: Verify the update functionality works correctly.
    private static boolean testShouldUpdateFan() {
        FanDAO dao = new FanDAO();
        int existingId = 1;

        // Step 1: Arrange - Get the fan and store its original team
        Fan fanToUpdate = dao.getFanById(existingId);
        if (fanToUpdate == null) {
            System.err.println("  PRE-CONDITION FAILED: Cannot test update. Fan with ID " + existingId + " does not exist.");
            return false;
        }
        String originalTeam = fanToUpdate.getFavoriteteam();
        String updatedTeam = "The Java Testers";

        // Step 2: Action - Update the team in the object and call the DAO method
        fanToUpdate.setFavoriteteam(updatedTeam);
        boolean updateResult = dao.updateFan(fanToUpdate);

        if (!updateResult) {
            System.err.println("  FAILURE: updateFan() method returned false.");
            return false; // The update method itself failed
        }

        // Step 3: Assert - Retrieve the fan again and check if the team was changed in the DB
        Fan fanAfterUpdate = dao.getFanById(existingId);
        boolean isTeamUpdated = fanAfterUpdate != null && fanAfterUpdate.getFavoriteteam().equals(updatedTeam);

        // Step 4: Cleanup - Revert the change so the database is in its original state
        fanAfterUpdate.setFavoriteteam(originalTeam);
        dao.updateFan(fanAfterUpdate);

        return isTeamUpdated; // Return true only if the team was successfully updated
    }
}