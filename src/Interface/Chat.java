package Interface;

import User.*;

public interface Chat {
    public void sendChat(User sender, String message);
    public void showChat();
}
