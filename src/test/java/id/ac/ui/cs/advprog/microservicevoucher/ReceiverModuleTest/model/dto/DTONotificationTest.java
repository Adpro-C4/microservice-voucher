package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModuleTest.model.dto;

import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.dto.DTONotification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTONotificationTest {
    private DTONotification notification;

    @BeforeEach
    void setUp() {
        notification = new DTONotification(
                "Voucher Name",
                0.5,
                1,
                NotificationStatus.CREATED.getValue(),
                "New voucher available:\nVoucher Name: Voucher Name\nValue: 50.00% off\nUsage Quota: 1"
        );
    }

    @Test
    void testGetVoucherName() {
        assertEquals("Voucher Name", notification.getVoucherName());
    }

    @Test
    void testSetVoucherName() {
        notification.setVoucherName("New Name");
        assertEquals("New Name", notification.getVoucherName());
    }

    @Test
    void testGetVoucherDiscount() {
        assertEquals(0.5, notification.getVoucherDiscount());
    }

    @Test
    void testSetVoucherDiscount() {
        notification.setVoucherDiscount(0.4);
        assertEquals(0.4, notification.getVoucherDiscount());
    }

    @Test
    void testGetVoucherQuota() {
        assertEquals(1, notification.getVoucherQuota());
    }

    @Test
    void testSetVoucherQuota() {
        notification.setVoucherQuota(2);
        assertEquals(2, notification.getVoucherQuota());
    }

    @Test
    void testGetStatus() {
        assertEquals(NotificationStatus.CREATED.getValue(), notification.getStatus());
    }

    @Test
    void testSetStatus() {
        notification.setStatus(NotificationStatus.UPDATED.getValue());
        assertEquals(NotificationStatus.UPDATED.getValue(), notification.getStatus());
    }

    @Test
    void testGetMessage() {
        notification.setMessage("New message");
        assertEquals("New message", notification.getMessage());
    }
}
