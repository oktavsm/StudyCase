package Interface;

import domain.user.*;
import java.util.*;

public interface Chat {
    public void saveChat(User sender, String message);

    public ArrayList<String> getChat();
}
