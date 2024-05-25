package id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.controller;

import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.dto.DTOCustomer;
import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService service;

    @Autowired
    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/accept-notification")
    public DTOCustomer acceptNotification(@RequestBody DTOCustomer customer) {
        return service.acceptNotification(customer);
    }

    @PostMapping("/reject-notification")
    public DTOCustomer rejectNotification(@RequestBody DTOCustomer customer) {
        return service.rejectNotification(customer);
    }
}
