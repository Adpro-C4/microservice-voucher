package id.ac.ui.cs.advprog.microservicevoucher.VoucherModuleTest.model.dto;

import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Notification;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.dto.DTOCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class DTOCustomerTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DTOCustomer customer;
    private Notification notification;

    private static final String FAKE_URI = "http://fake-url.com/api/update";

    @BeforeEach
    void setUp() {
        openMocks(this);

        notification = new Notification(
                "Voucher Name",
                0.5,
                1,
                NotificationStatus.CREATED.getValue()
        );

        customer = new DTOCustomer();
        customer.setUserId("1533113");
        customer.setFullname("Dk Desu");
        customer.setUsername("dkDesu");
        customer.setPhoneNumber("081053112003");
        customer.setEmail("dkdesu@gmail.com");
    }

    @Test
    void testGetUserId() {assertEquals("1533113", customer.getUserId());}

    @Test
    void testGetFullname() {assertEquals("Dk Desu", customer.getFullname());}

    @Test
    void testGetUsername() {assertEquals("dkDesu", customer.getUsername());}

    @Test
    void testGetPhoneNumber() {assertEquals("081053112003", customer.getPhoneNumber());}

    @Test
    void testGetEmail() {assertEquals("dkdesu@gmail.com", customer.getEmail());}

    @Test
    void testUpdateSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Notification> requestEntity = new HttpEntity<>(notification, headers);

        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.exchange(FAKE_URI, HttpMethod.POST, requestEntity, Void.class)).thenReturn(responseEntity);

        CompletableFuture<Void> future = customer.update(notification, FAKE_URI, restTemplate);

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            fail("The async method threw an exception: " + e.getMessage());
        }

        verify(restTemplate, times(1)).exchange(FAKE_URI, HttpMethod.POST, requestEntity, Void.class);
    }

    @Test
    void testUpdateFailToSend() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Notification> requestEntity = new HttpEntity<>(notification, headers);

        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        when(restTemplate.exchange(FAKE_URI, HttpMethod.POST, requestEntity, Void.class)).thenReturn(responseEntity);

        CompletableFuture<Void> future = customer.update(notification, FAKE_URI, restTemplate);

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            fail("The async method threw an exception: " + e.getMessage());
        }

        verify(restTemplate, times(1)).exchange(FAKE_URI, HttpMethod.POST, requestEntity, Void.class);
    }

    @Test
    void testUpdateThrowsRestClientException() {
        when(restTemplate.exchange(eq(FAKE_URI), eq(HttpMethod.POST), any(HttpEntity.class), eq(Void.class)))
                .thenThrow(new RestClientException("Failed to connect to the server"));

        assertThrows(RestClientException.class, () -> {
            CompletableFuture<Void> future = customer.update(notification, FAKE_URI, restTemplate);
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
