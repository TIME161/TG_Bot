package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pro.sky.telegrambot.repositoryes.NotificationRepository;

@RunWith(MockitoJUnitRunner.class)
public class TelegramBotUpdatesListenerTest {
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private Message message;
    @Mock
    private Chat chat;
    @Mock
    private User user;
    @Mock
    private Update update;
    private TelegramBotUpdatesListener updatesListener;


}