package id.ac.ui.cs.advprog.microservicevoucher.VoucherModuleTest.service;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.repository.VoucherRepository;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service.VoucherService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

public class VoucherServiceTest {
    @InjectMocks
    private VoucherService service;
    @Mock
    private VoucherRepository repository;
    List<Voucher> vouchers;

    @BeforeEach
    void setUp() {
        Voucher voucher = new Voucher(
                "Voucher Name",
                "Voucher description",
                0.5,
                1
        );
    }
}
