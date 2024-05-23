package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.controller;

import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.CustomerNotification;
import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.dto.DTONotification;
import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.service.CustomerNotificationService;
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
    public CustomerNotification updateVoucher(@RequestBody DTONotification notification, @RequestParam String username) {
        System.out.println(username);
        CustomerNotification customerNotification = service.findAllByUsername(username);
        System.out.println("dkdesu2");
        if (customerNotification == null) {
            customerNotification = new CustomerNotification(username);
            System.out.println("dkdesu3");
        }
        System.out.println("dkdesu4");
        customerNotification.addNotification(notification.getMessage());
        System.out.println("dkdesu5");
        return service.save(customerNotification);
    }
}
