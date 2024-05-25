package id.ac.ui.cs.advprog.microservicevoucher.vouchermoduletest.service;

import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.dto.DTOCustomer;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.repository.dto.DTOCustomerRepository;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NotificationServiceTest {
    @Mock
    private DTOCustomerRepository customerRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NotificationServiceImpl notificationService;
    DTOCustomer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new DTOCustomer();
        customer.setUserId("1533113");
        customer.setFullname("Dk Desu");
        customer.setUsername("dkDesu");
        customer.setPhoneNumber("081053112003");
        customer.setEmail("dkdesu@gmail.com");
    }

    @Test
    void testAcceptNotification() {
        when(customerRepository.save(customer)).thenReturn(customer);

        DTOCustomer savedCustomer = notificationService.acceptNotification(customer);

        assertEquals(customer, savedCustomer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testRejectNotification() {
        notificationService.rejectNotification(customer);

        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    void testNotifyIfNoListener() {
        Voucher voucher = new Voucher(
                "Voucher Name",
                "Voucher description",
                0.5,
                1
        );

        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        notificationService.notify(NotificationStatus.CREATED.getValue(), voucher);

        verify(customerRepository, times(1)).findAll();
        verify(customerRepository, times(0)).save(any(DTOCustomer.class)); // Because the repository is mocked
        verify(restTemplate, times(0)).exchange(anyString(), any(), any(), eq(Void.class));
    }
}