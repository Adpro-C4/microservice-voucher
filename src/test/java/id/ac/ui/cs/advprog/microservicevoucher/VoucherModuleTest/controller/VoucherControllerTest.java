package id.ac.ui.cs.advprog.microservicevoucher.VoucherModuleTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.controller.VoucherController;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Voucher;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service.VoucherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VoucherControllerTest {
    private MockMvc mockMvc;
    @Mock
    private VoucherServiceImpl service;
    @InjectMocks
    private VoucherController controller;
    private Voucher voucher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        voucher = new Voucher(
                "Voucher Name",
                "Voucher description",
                0.5,
                1
        );
    }

    @Test
    void testFindAll() throws Exception {
        List<Voucher> vouchers = Collections.singletonList(voucher);

        when(service.findAll()).thenReturn(vouchers);

        mockMvc.perform(get("/voucher/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].voucherName").value("Voucher Name"))
                .andExpect(jsonPath("$[0].voucherDescription").value("Voucher description"))
                .andExpect(jsonPath("$[0].voucherDiscount").value(0.5))
                .andExpect(jsonPath("$[0].voucherQuota").value(1));

        verify(service, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {
        when(service.findVoucherById(1L)).thenReturn(voucher);

        mockMvc.perform(get("/voucher/id?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.voucherName").value("Voucher Name"))
                .andExpect(jsonPath("$.voucherDescription").value("Voucher description"))
                .andExpect(jsonPath("$.voucherDiscount").value(0.5))
                .andExpect(jsonPath("$.voucherQuota").value(1));

        verify(service, times(1)).findVoucherById(1L);
    }

    @Test
    void testCreateVoucher() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String voucherJson = objectMapper.writeValueAsString(voucher);

        when(service.save(any(Voucher.class))).thenReturn(voucher);

        mockMvc.perform(post("/voucher/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voucherJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.voucherName").value("Voucher Name"))
                .andExpect(jsonPath("$.voucherDescription").value("Voucher description"))
                .andExpect(jsonPath("$.voucherDiscount").value(0.5))
                .andExpect(jsonPath("$.voucherQuota").value(1));

        verify(service, times(1)).save(any(Voucher.class));
    }

    @Test
    void testUpdateVoucher() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String voucherJson = objectMapper.writeValueAsString(voucher);

        when(service.save(any(Voucher.class))).thenReturn(voucher);

        mockMvc.perform(post("/voucher/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voucherJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.voucherName").value("Voucher Name"))
                .andExpect(jsonPath("$.voucherDescription").value("Voucher description"))
                .andExpect(jsonPath("$.voucherDiscount").value(0.5))
                .andExpect(jsonPath("$.voucherQuota").value(1));

        verify(service, times(1)).save(any(Voucher.class));
    }

    @Test
    void testDeleteVoucherById() throws Exception {
        mockMvc.perform(post("/voucher/delete?id=1"))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(1L);
    }
}
