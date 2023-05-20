package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notification_task")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long chatId;
    private String notificationText;
    private LocalDateTime notificationTime;

    public Notification() {
    }

    public Notification(Long id, Long chatId, String notificationText, LocalDateTime notificationTime) {
        this.id = id;
        this.chatId = chatId;
        this.notificationText = notificationText;
        this.notificationTime = notificationTime;
    }

    public Notification(Long chatId, String notificationText, LocalDateTime notificationTime) {
        this.chatId = chatId;
        this.notificationText = notificationText;
        this.notificationTime = notificationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "Id=" + id +
                ", chatId=" + chatId +
                ", notificationText='" + notificationText + '\'' +
                ", notificationTime=" + notificationTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id.equals(that.id) && chatId.equals(that.chatId) && notificationText.equals(that.notificationText) && notificationTime.equals(that.notificationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, notificationText, notificationTime);
    }
}
