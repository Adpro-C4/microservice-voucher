package id.ac.ui.cs.advprog.microservicevoucher.vouchermoduletest.service;

import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.repository.VoucherRepository;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service.NotificationService;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service.VoucherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VoucherServiceTest {

    @Mock
    private VoucherRepository voucherRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private VoucherServiceImpl voucherService;

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
    void testSaveVoucher() {
        when(voucherRepository.save(voucher)).thenReturn(voucher);

        Voucher savedVoucher = voucherService.save(voucher);

        assertEquals(voucher, savedVoucher);
        verify(notificationService, times(1)).notify(NotificationStatus.CREATED.getValue(), voucher);
    }

    @Test
    void testDeleteVoucherById() {
        Long voucherId = 1L;

        assertDoesNotThrow(() -> voucherService.deleteById(voucherId));
        verify(voucherRepository, times(1)).deleteById(voucherId);
    }

    @Test
    void testFindAllVouchers() {
        List<Voucher> voucherList = new ArrayList<>();
        voucherList.add(voucher);

        when(voucherRepository.findAll()).thenReturn(voucherList);

        List<Voucher> foundVouchers = voucherService.findAll();

        assertEquals(voucherList, foundVouchers);
    }

    @Test
    void testFindVoucherById() {
        Long voucherId = 1L;

        when(voucherRepository.findById(voucherId)).thenReturn(Optional.of(voucher));

        Voucher foundVoucher = voucherService.findVoucherById(voucherId);

        assertEquals(voucher, foundVoucher);
    }

    @Test
    void testFindAllVoucherByName() {
        String voucherName = "Voucher Name";
        List<Voucher> voucherList = new ArrayList<>();
        voucherList.add(voucher);

        when(voucherRepository.findAllByVoucherNameContainingIgnoreCase(voucherName)).thenReturn(voucherList);

        List<Voucher> foundVouchers = voucherService.findAllVoucherByName(voucherName);

        assertEquals(voucherList, foundVouchers);
    }

    @Test
    void testFindAllVoucherByDiscount() {
        double discount = 0.5;
        List<Voucher> voucherList = new ArrayList<>();
        voucherList.add(voucher);

        when(voucherRepository.findAllByVoucherDiscount(discount)).thenReturn(voucherList);

        List<Voucher> foundVouchers = voucherService.findAllVoucherByDiscount(discount);

        assertEquals(voucherList, foundVouchers);
    }

    @Test
    void testFindAllVoucherByUsageQuota() {
        int quota = 1;
        List<Voucher> voucherList = new ArrayList<>();
        voucherList.add(voucher);

        when(voucherRepository.findAllByVoucherQuota(quota)).thenReturn(voucherList);

        List<Voucher> foundVouchers = voucherService.findAllVoucherByUsageQuota(quota);

        assertEquals(voucherList, foundVouchers);
    }

    @Test
    void testFindVoucherByIdNotFound() {
        Long voucherId = 1L;

        when(voucherRepository.findById(voucherId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> voucherService.findVoucherById(voucherId));
    }
}
