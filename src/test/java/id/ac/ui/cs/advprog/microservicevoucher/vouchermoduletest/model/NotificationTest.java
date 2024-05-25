package id.ac.ui.cs.advprog.microservicevoucher.vouchermoduletest.model;

import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {
    private Notification notification;

    @BeforeEach
    void setUp() {
        notification = new Notification(
                "Voucher Name",
                0.5,
                1,
                NotificationStatus.CREATED.getValue()
        );
    }

    @Test
    void testGetVoucherName() {assertEquals("Voucher Name", this.notification.getVoucherName());}

    @Test
    void testGetVoucherDiscount() {assertEquals(0.5, this.notification.getVoucherDiscount());}

    @Test
    void testGetVoucherQuota() {assertEquals(1, this.notification.getVoucherQuota());}

    @Test
    void testGetVoucherStatus() {assertEquals(NotificationStatus.CREATED.getValue(), this.notification.getStatus());}

    @Test
    void testGetVoucherStatusIfFalse() {assertThrows(IllegalArgumentException.class, () -> notification.setStatus("BONJOUR"));}

    @Test
    void testGetVoucherMessage() {assertEquals("New voucher available:\nVoucher Name: Voucher Name\nValue: 50.00% off\nUsage Quota: 1", this.notification.getMessage());}
}
