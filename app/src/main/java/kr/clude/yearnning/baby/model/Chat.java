package kr.clude.yearnning.baby.model;

import java.util.Date;

/**
 * Created by yearnning on 15. 2. 24..
 */
public class Chat {

    public enum Type {
        PARENT, SITTER
    }

    public Type type = null;
    public String content = null;
    public Person person = null;
    public String date_str = null;

    public static Chat newInstanceById(int chat_id) {

        Chat chat = new Chat();
        chat.date_str = "방금 전";
        chat.person = PersonSitter.newInstanceById(1);
        chat.type = Type.SITTER;

        switch (chat_id) {
            case 1:
                chat.content = "안녕하세요, 연락주셔서 감사합니다. 그 때 가능합니다. 피아노있으시면 피아노 교습도 가능하고," +
                        "책시터도 가능합니다.";
                break;
            case 2:
                chat.content = "그럼 그 때 뵙겠습니다. 감사합니다.";
                break;
            default:
                chat.content = "감사합니다.";
                break;
        }

        return chat;
    }

    public static Chat newInstance(String content) {
        Chat chat = new Chat();
        chat.type = Type.PARENT;
        chat.date_str = "방금 전";
        chat.person = PersonSitter.newInstanceById(2);
        chat.content = content;
        return chat;
    }
}
