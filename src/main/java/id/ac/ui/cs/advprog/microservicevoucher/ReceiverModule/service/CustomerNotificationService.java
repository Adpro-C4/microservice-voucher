package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.service;

import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model.CustomerNotification;
import id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.repository.CustomerNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
