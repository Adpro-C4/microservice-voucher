package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModuleTest.model;

import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.CustomerNotification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerNotificationTest {
    private CustomerNotification customerNotification;

    @BeforeEach
    void setUp() {
        customerNotification = new CustomerNotification("username");
    }

    @Test
    void testGetCustomerUsername() {
        assertEquals("username", customerNotification.getCustomerUsername());
    }

    @Test
    void testGetNotifications() {
        assertEquals(new ArrayList<>(), customerNotification.getNotifications());
    }

    @Test
    void testSetCustomerUsername() {
        customerNotification.setCustomerUsername("username1");
        assertEquals("username1", customerNotification.getCustomerUsername());
    }

    @Test
    void testSetNotifications() {
        List<String> testList = new ArrayList<>();
        testList.add("Test message");
        customerNotification.setNotifications(testList);

        assertEquals(testList, customerNotification.getNotifications());
    }

    @Test
    void testAddNotification() {
        List<String> testList = new ArrayList<>();
        testList.add("Test message 1");
        customerNotification.setNotifications(testList);

        testList.add("Test message 2");
        customerNotification.addNotification("Test message 2");

        assertEquals(testList, customerNotification.getNotifications());
    }
}
