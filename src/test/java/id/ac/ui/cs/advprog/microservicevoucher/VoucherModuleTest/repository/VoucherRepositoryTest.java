package id.ac.ui.cs.advprog.microservicevoucher.VoucherModuleTest.repository;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository.VoucherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VoucherRepositoryTest {

    VoucherRepository voucherRepository;
    List<Voucher> vouchers;

    @BeforeEach
    void setUp() {
        Voucher voucher1 = new Voucher(
                "Voucher Name",
                "Voucher description",
                0.5,
                1
        );
        vouchers = new ArrayList<>();
        vouchers.add(voucher1);
    }

    @Test
    void testSaveNewVoucher() {
        Voucher voucher = vouchers.getFirst();
        voucherRepository.save(voucher);

        Voucher findResult = voucherRepository.findAll().getFirst();
        assertEquals(1L, findResult.getVoucherId());
        assertEquals(voucher.getVoucherName(), findResult.getVoucherName());
    }
}
