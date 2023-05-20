package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Notification;
import pro.sky.telegrambot.repositoryes.NotificationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationService {

    private final TelegramBot telegramBot;
    private final NotificationRepository notificationRepository;

    public NotificationService(TelegramBot telegramBot, NotificationRepository notificationRepository) {
        this.telegramBot = telegramBot;
        this.notificationRepository = notificationRepository;
    }

    private static final String DATE_TIME_PATTERN = "(\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2})";
    private static final String MESSAGE_PATTERN = "(.+)";

    public void sendWelcomeMessage(Message message) {
        String welcomeText = message.from().languageCode().equals("ru")
                ? "Добро пожаловать в мой бот " + message.chat().firstName() + "!"
                : "Welcome to my bot " + message.chat().firstName() + "!";
        SendMessage welcomeMessage = new SendMessage(message.chat().id(), welcomeText);
        telegramBot.execute(welcomeMessage);
    }

    public void processNotificationMessage(Message message, String messageText) {
        Pattern pattern = Pattern.compile(DATE_TIME_PATTERN + " " + MESSAGE_PATTERN);
        Matcher matcher = pattern.matcher(messageText);

        if (matcher.matches()) {
            saveNotification(message, matcher);
        } else {
            sendInvalidFormatMessage(message);
        }
    }

    private void saveNotification(Message message, Matcher matcher) {
        String dateTimeString = matcher.group(1);
        String notificationText = matcher.group(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        Notification task = new Notification();
        task.setChatId(message.chat().id());
        task.setNotificationText(notificationText);
        task.setNotificationTime(dateTime);
        notificationRepository.save(task);
    }

    private void sendInvalidFormatMessage(Message message) {
        String invalidFormatText = message.from().languageCode().equals("ru")
                ? "Некорректный формат сообщения. Используйте формат дд.мм.гггг чч:мм текст напоминания"
                : "Incorrect message format. Use format dd.MM.yyyy HH:mm notification text";
        SendMessage invalidFormatMessage = new SendMessage(message.chat().id(), invalidFormatText);
        telegramBot.execute(invalidFormatMessage);
    }
}
