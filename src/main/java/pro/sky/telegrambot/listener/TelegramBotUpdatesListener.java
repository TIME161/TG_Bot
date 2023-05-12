package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message().text() != null
                    && update.message().text().equals("/start")) {
                if (update.message().from().languageCode().equals("ru")) {
                    SendMessage message = new SendMessage(update.message().chat().id(),
                            "Добро пожаловать в мой бот " + update.message().chat().firstName() + "!");
                    SendResponse response = telegramBot.execute(message);
                } else {SendMessage message = new SendMessage(update.message().chat().id(),
                        "Welcome to my bot " + update.message().chat().firstName() + "!");
                    SendResponse response = telegramBot.execute(message);}
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}