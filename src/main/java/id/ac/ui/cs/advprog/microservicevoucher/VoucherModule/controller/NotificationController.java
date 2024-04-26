package id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.controller;

import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.dto.DTOCustomer;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService service;

    @PostMapping("/accept-notification")
    public DTOCustomer acceptNotification(@RequestBody DTOCustomer customer) {
        return service.acceptNotification(customer);
    }

    @PostMapping("/create")
    public DTOCustomer createVoucher(@RequestBody DTOCustomer customer) {
        return service.rejectNotification(customer);
    }
}
