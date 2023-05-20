package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pro.sky.telegrambot.repositoryes.NotificationRepository;

import static org.mockito.Mockito.*;

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

    @Before
    public void setUp() {
        updatesListener = new TelegramBotUpdatesListener(telegramBot, notificationRepository);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(123L);
        when(message.from()).thenReturn(user);
        when(user.languageCode()).thenReturn("en");
        when(message.text()).thenReturn("/start");
        when(update.message()).thenReturn(message);
    }

    @Test
    public void testProcessUpdateWithStartCommand() {
        /* тут должен быть тест но его нет*/

    }
}