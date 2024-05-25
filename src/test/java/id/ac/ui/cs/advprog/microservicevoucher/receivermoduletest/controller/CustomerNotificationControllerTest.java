package id.ac.ui.cs.advprog.microservicevoucher.receivermoduletest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.NotificationStatus;
import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.controller.CustomerNotificationController;
import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.model.CustomerNotification;
import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.model.dto.DTONotification;
import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.service.CustomerNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerNotificationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerNotificationService service;

    @InjectMocks
    private CustomerNotificationController controller;
    private CustomerNotification customerNotification;
    private DTONotification notification;

    private String expectedUsername;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        customerNotification = new CustomerNotification("username");

        notification = new DTONotification(
                "Voucher Name",
                0.5,
                1,
                NotificationStatus.CREATED.getValue(),
                "New voucher available:\nVoucher Name: Voucher Name\nValue: 50.00% off\nUsage Quota: 1"
        );

        expectedUsername = customerNotification.getCustomerUsername();
    }

    @Test
    void testListByUsername() throws Exception {
        when(service.findAllByUsername(expectedUsername)).thenReturn(customerNotification);

        mockMvc.perform(get("/customer-notification/list")
                        .param("username", expectedUsername))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerUsername").value(expectedUsername));

        verify(service, times(1)).findAllByUsername(expectedUsername);
    }

    @Test
    void testUpdateVoucher() throws Exception {
        when(service.findAllByUsername(expectedUsername)).thenReturn(null); // Simulate customer not found
        when(service.save(any(CustomerNotification.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ObjectMapper objectMapper = new ObjectMapper();
        String notificationJson = objectMapper.writeValueAsString(notification);

        mockMvc.perform(post("/customer-notification/update")
                        .param("username", expectedUsername)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(notificationJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerUsername").value(expectedUsername))
                .andExpect(jsonPath("$.notifications[0]").value("New voucher available:\nVoucher Name: Voucher Name\nValue: 50.00% off\nUsage Quota: 1"));

        verify(service, times(1)).findAllByUsername(expectedUsername);
        verify(service, times(1)).save(any(CustomerNotification.class));
    }
}
