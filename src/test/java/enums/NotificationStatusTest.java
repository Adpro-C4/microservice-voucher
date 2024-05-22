package enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationStatusTest {
    @Test
    void testGetValue() {
        assertEquals("CREATED", NotificationStatus.CREATED.getValue());
    }

    @Test
    void testGetMsg() {
        assertEquals("New voucher available:\n", NotificationStatus.CREATED.getMsg());
    }

    @Test
    void testContains() {
        assertTrue(NotificationStatus.contains(NotificationStatus.CREATED.name()));
    }

    @Test
    void testContainsIfFalse() {
        assertFalse(NotificationStatus.contains("BONJOUR"));
    }
}
