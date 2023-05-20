package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repositoryes.NotificationRepository;
import pro.sky.telegrambot.service.NotificationService;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, NotificationRepository notificationRepository, NotificationService notificationService) {
        this.telegramBot = telegramBot;
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(this::processUpdate);
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void processUpdate(Update update) {
        try {
            logger.info("Processing update: {}", update);
            Message message = update.message() != null ? update.message() : update.editedMessage();
            if (message == null) return;

            String messageText = message.text();
            if (messageText == null) return;

            if ("/start".equals(messageText)) {
                notificationService.sendWelcomeMessage(message);
            } else {
                notificationService.processNotificationMessage(message, messageText);
            }
        } catch (Exception e) {
            logger.error("Error processing update: {}", update, e);
        }
    }
}
