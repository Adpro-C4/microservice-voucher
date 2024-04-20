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
                0L,
                "Voucher Name",
                "Voucher desc",
                0.5,
                -1
        );
    }

    @Test
    void testGetVoucherId() {
        assertEquals(0L, this.voucher.getVoucherId());
    }

    @Test
    void testGetVoucherName() {
        assertEquals("Voucher Name", this.voucher.getVoucherName());
    }

    @Test
    void testGetVoucherDesc() {
        assertEquals("Voucher desc", this.voucher.getVoucherDesc());
    }

    @Test
    void testGetVoucherDiscount() {
        assertEquals(0.5, this.voucher.getVoucherDiscount());
    }

    @Test
    void testGetVoucherQuota() {
        assertEquals(-1, this.voucher.getVoucherQuota());
    }
}
