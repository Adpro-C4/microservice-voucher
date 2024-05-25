package id.ac.ui.cs.advprog.microservicevoucher.receivermodule.controller;

import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.model.CustomerNotification;
import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.model.dto.DTONotification;
import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.service.CustomerNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer-notification")
public class CustomerNotificationController {
    private final CustomerNotificationService service;

    @Autowired
    public CustomerNotificationController(CustomerNotificationService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public CustomerNotification listByUsername(@RequestParam String username) {
        return service.findAllByUsername(username);
    }

    @PostMapping("/update")
    public CustomerNotification updateVoucher(@RequestBody DTONotification notification, @RequestParam String username) {
        CustomerNotification customerNotification = service.findAllByUsername(username);
        if (customerNotification == null) {
            customerNotification = new CustomerNotification(username);
        }
        customerNotification.addNotification(notification.getMessage());
        return service.save(customerNotification);
    }
}
