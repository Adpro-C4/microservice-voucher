package id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.dto;

import id.ac.ui.cs.advprog.microservicevoucher.vouchermodule.model.Notification;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Getter
@Setter
@Entity
@Embeddable
public class DTOCustomer {
    @Id
    private String userId;
    private String fullname;
    private String username;
    private String phoneNumber;
    private String email;

    @Async
    public CompletableFuture<Void> update(Notification payload, String uri, RestTemplate restTemplate) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Notification> requestEntity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    Void.class
            );

            HttpStatusCode statusCode = responseEntity.getStatusCode();
            int statusCodeValue = statusCode.value();
            System.out.println("Status Code: " + statusCodeValue);

            if (statusCodeValue == HttpStatus.OK.value()) {
                System.out.println("Sent:\n" + responseEntity.getBody());
            } else {
                System.out.println("Nothing was sent");
            }

            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            throw new RestClientException("Failed to connect to the server");
        }
    }
}