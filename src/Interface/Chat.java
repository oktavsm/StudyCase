package Interface;

import User.*;
import java.util.*;

public interface Chat {
    public void saveChat(User sender, String message);

    public ArrayList<String> getChat();
}
