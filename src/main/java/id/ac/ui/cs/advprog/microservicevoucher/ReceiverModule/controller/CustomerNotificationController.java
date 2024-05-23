package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.controller;

import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.CustomerNotification;
import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.service.CustomerNotificationService;
import id.ac.ui.cs.advprog.microservicevoucher.VoucherModule.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer-notification")
public class CustomerNotificationController {
    @Autowired
    CustomerNotificationService service;

    @GetMapping("/list")
    public CustomerNotification listByUsername(@RequestParam String username) {
        return service.findAllByUsername(username);
    }

    @PostMapping("/update")
    public CustomerNotification updateVoucher(@RequestBody Notification notification, @RequestParam String username) {
        CustomerNotification customerNotification = service.findAllByUsername(username);
        if (customerNotification == null) {
            customerNotification = new CustomerNotification(username);
        }
        customerNotification.addNotification(notification.getMessage());
        return service.save(customerNotification);
    }
}
