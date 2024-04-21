package id.ac.ui.cs.advprog.microservicevoucher.VoucherModuleTest.repository;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository.VoucherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VoucherRepositoryTest {

    VoucherRepository voucherRepository;
    List<Voucher> vouchers;

    @BeforeEach
    void setUp() {}
}
