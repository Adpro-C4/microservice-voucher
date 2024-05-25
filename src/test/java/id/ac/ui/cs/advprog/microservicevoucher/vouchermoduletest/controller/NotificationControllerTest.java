package id.ac.ui.cs.advprog.microservicevoucher.vouchermoduletest.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.controller.NotificationController;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.dto.DTOCustomer;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service.NotificationService;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NotificationControllerTest {
    private MockMvc mockMvc;

    @Mock
    private NotificationService service;

    @InjectMocks
    private NotificationController controller;

    @Mock
    private RestTemplate restTemplate;

    private DTOCustomer customer;

    private static final Logger logger = Logger.getLogger(NotificationServiceImpl.class.getName());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        customer = new DTOCustomer();
        customer.setUserId("1533113");
        customer.setFullname("Dk Desu");
        customer.setUsername("dkDesu");
        customer.setPhoneNumber("081053112003");
        customer.setEmail("dkdesu@gmail.com");
    }

    @Test
    void testAcceptNotification() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(customer);

        when(service.acceptNotification(any(DTOCustomer.class))).thenReturn(customer);

        mockMvc.perform(post("/notification/accept-notification")
                        .contentType("application/json")
                        .content(customerJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.userId").value("1533113"));

        verify(service, times(1)).acceptNotification(any(DTOCustomer.class));
    }

    @Test
    void testRejectNotification() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(customer);

        when(service.rejectNotification(any(DTOCustomer.class))).thenReturn(customer);

        mockMvc.perform(post("/notification/reject-notification")
                        .contentType("application/json")
                        .content(customerJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.userId").value("1533113"));

        verify(service, times(1)).rejectNotification(any(DTOCustomer.class));
    }

    @Test
    void testAsyncUpdate() throws InterruptedException, ExecutionException {
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.exchange(anyString(), any(), any(), eq(Void.class))).thenReturn(responseEntity);

        CompletableFuture<Void> future = customer.update(null, String.format("http://34.143.184.254/customer-notification/update?username=%s", customer.getUsername()), restTemplate, logger);
        future.get();
        verify(restTemplate, times(1)).exchange(anyString(), any(), any(), eq(Void.class));
    }

    @Test
    void testAsyncUpdateWithError() {
        when(restTemplate.exchange(anyString(), any(), any(), eq(Void.class))).thenThrow(new RestClientException("Failed"));

        try {
            CompletableFuture<Void> future = customer.update(null, String.format("http://34.143.184.254/customer-notification/update?username=%s", customer.getUsername()), restTemplate, logger);
            future.get();
        } catch (Exception ignored) {
        }
        verify(restTemplate, times(1)).exchange(anyString(), any(), any(), eq(Void.class));
    }
}
