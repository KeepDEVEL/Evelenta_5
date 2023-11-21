package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageService {

    private Map<Integer, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addMessage(int userId, Message message) {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            user.addMessage(message);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public void deleteMessage(int userId, int messageId) {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            user.deleteMessage(messageId);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public List<Message> getMessages(int userId) {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            return user.getMessages();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public static void main(String[] args) {
        MessageService messageService = new MessageService();

        User user1 = new User(1, "John");
        User user2 = new User(2, "Alice");

        messageService.addUser(user1);
        messageService.addUser(user2);

        Message message1 = new Message(1, "Hello");
        Message message2 = new Message(2, "Hi");

        messageService.addMessage(1, message1);
        messageService.addMessage(2, message2);

        messageService.deleteMessage(1, 1);
        messageService.deleteMessage(2, 2);

        List<Message> user1Messages = messageService.getMessages(1);
        System.out.println("User 1 messages: " + user1Messages);

        List<Message> user2Messages = messageService.getMessages(2);
        System.out.println("User 2 messages: " + user2Messages);
    }
}

class User {
    private int id;
    private String name;
    private List<Message> messages;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.messages = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void deleteMessage(int messageId) {
        Message messageToRemove = null;
        for (Message message : messages) {
            if (message.getId() == messageId) {
                messageToRemove = message;
                break;
            }
        }
        if (messageToRemove != null) {
            messages.remove(messageToRemove);
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }
}

class Message {
    private int id;
    private String content;

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", content=" + content + "]";
    }
}