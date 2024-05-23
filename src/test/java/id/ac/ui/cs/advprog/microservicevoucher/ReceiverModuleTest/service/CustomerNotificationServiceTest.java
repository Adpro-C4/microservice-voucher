package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModuleTest.service;

import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.CustomerNotification;
import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.repository.CustomerNotificationRepository;
import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.service.CustomerNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerNotificationServiceTest {
    @Mock
    private CustomerNotificationRepository repository;

    @InjectMocks
    private CustomerNotificationService service;
    private CustomerNotification customerNotification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerNotification = new CustomerNotification("username");
    }

    @Test
    void testSaveCustomerNotification() {
        when(repository.save(customerNotification)).thenReturn(customerNotification);

        CustomerNotification savedCustomerNotification = service.save(customerNotification);
        verify(repository, times(1)).save(customerNotification);

        assertEquals(customerNotification, savedCustomerNotification);
    }

    @Test
    void testFindAllByUsername() {
        when(repository.findByCustomerUsername("username")).thenReturn(customerNotification);

        CustomerNotification foundCustomerNotification = service.findAllByUsername("username");
        verify(repository, times(1)).findByCustomerUsername("username");

        assertEquals(customerNotification, foundCustomerNotification);
    }
}
