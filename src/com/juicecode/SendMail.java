package com.juicecode;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {

    private String from;
    private String password;
    private String host;
    private String port;
    private Map<String, MailContent> mailingList;

    public SendMail(String from, String password, String host, String port, Map<String, MailContent> mailingList) {
        this.from = from;
        this.password = password;
        this.host = host;
        this.port = port;
        this.mailingList = mailingList;
    }

    public void send(boolean mailingListContent) {
        String to = "dzusmin@gmail.com";

        final String username = this.from;
        final String password = this.password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        for (Map.Entry<String, MailContent> entry : this.mailingList.entrySet()) {
            if (mailingListContent) {
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(this.from));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(entry.getKey()));

                    message.setSubject(entry.getValue().getSubject());
                    message.setContent(entry.getValue().getContent(), "text/html");

                    // Send message
                    Transport.send(message);

                    System.out.println("Sent message successfully....");

                } catch (MessagingException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
