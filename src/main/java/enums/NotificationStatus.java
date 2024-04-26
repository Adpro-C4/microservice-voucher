package enums;

import lombok.Getter;

@Getter
public enum NotificationStatus {
    CREATED("CREATED"),
    UPDATED("UPDATED"),
    DELETED("DELETED");

    private final String value;
    NotificationStatus(String value) { this.value = value; }

    public static boolean contains(String param) {
        for (NotificationStatus notificationStatus : NotificationStatus.values()) {
            if (notificationStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
