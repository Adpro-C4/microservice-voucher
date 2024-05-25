package id.ac.ui.cs.advprog.microservicevoucher.receivermodule.service;

import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.model.CustomerNotification;
import id.ac.ui.cs.advprog.microservicevoucher.receivermodule.repository.CustomerNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerNotificationService {
    @Autowired
    CustomerNotificationRepository repository;

    public CustomerNotification save(CustomerNotification customerNotification) {
        repository.save(customerNotification);
        return customerNotification;
    }

    public CustomerNotification findAllByUsername(String username) {
        return repository.findByCustomerUsername(username);
    }
}
