package id.ac.ui.cs.advprog.microservicevoucher.VoucherModuleTest.model;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VoucherTest {
    private Voucher voucher;

    @BeforeEach
    void setUp() {
        voucher = new Voucher(
                "Voucher Name",
                "Voucher description",
                0.5,
                1
        );
    }

    @Test
    void testGetVoucherId() {
        assertNull(this.voucher.getVoucherId());
    }

    @Test
    void testGetVoucherName() {
        assertEquals("Voucher Name", this.voucher.getVoucherName());
    }

    @Test
    void testGetVoucherDescription() {
        assertEquals("Voucher description", this.voucher.getVoucherDescription());
    }

    @Test
    void testGetVoucherDiscount() {
        assertEquals(0.5, this.voucher.getVoucherDiscount());
    }

    @Test
    void testGetVoucherQuota() {
        assertEquals(1, this.voucher.getVoucherQuota());
    }

    @Test
    void testSetNameIfNameIsNotEmpty() {
        voucher.setVoucherName("Diskon Lebaran");
        assertEquals("Diskon Lebaran", voucher.getVoucherName());
    }

    @Test
    void testSetNameIfNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            voucher.setVoucherName("");
        });
    }

    @Test
    void testSetDescription() {
        voucher.setVoucherDescription("");
        assertEquals("", voucher.getVoucherDescription());
    }

    @Test
    void testSetDiscountIfDiscountBetweenZeroAndOneHundredPercent() {
        voucher.setVoucherDiscount(0.001);
        assertEquals(0.001, voucher.getVoucherDiscount());
    }

    @Test
    void testSetDiscountIfDiscountAboveOneHundredPercent() {
        assertThrows(IllegalArgumentException.class, () -> {
            voucher.setVoucherDiscount(1.01);
        });
    }

    @Test
    void testSetDiscountIfDiscountBelowZeroPercent() {
        assertThrows(IllegalArgumentException.class, () -> {
            voucher.setVoucherDiscount(-0.01);
        });
    }

    @Test
    void testSetQuotaIfQuotaIsMinusOne() {
        voucher.setVoucherQuota(-1);
        assertEquals(-1, voucher.getVoucherQuota());
    }

    @Test
    void testSetQuotaIfQuotaIsMoreThanOne() {
        voucher.setVoucherQuota(5);
        assertEquals(5, voucher.getVoucherQuota());
    }

    @Test
    void testSetQuotaIfQuotaIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            voucher.setVoucherQuota(0);
        });
    }

    @Test
    void testSetQuotaIfQuotaBelowMinusOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            voucher.setVoucherQuota(-3);
        });
    }
}
