package id.ac.ui.cs.advprog.microservicevoucher.ReceiverModule.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class CustomerNotification {
    @Id
    @Column(name = "customer_username")
    private String customerUsername;

    @Column(name = "notifications", nullable = false)
    private List<String> notifications;

    public CustomerNotification() {
        this.notifications = new ArrayList<>();
    }

    public CustomerNotification(String username) {
        this();
        this.customerUsername = username;
    }

    public void addNotification(String newMsg) {
        this.notifications.add(newMsg);
    }
}
