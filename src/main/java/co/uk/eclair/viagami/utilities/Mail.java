package co.uk.eclair.viagami.utilities;

/**
 * Created by ${Eclair} on 9/4/2018.
 */
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String content;
    private String attachementPath;

    public Mail() {
    }

    public Mail(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public Mail(String from, String to, String subject, String content, String attachementPath) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.attachementPath = attachementPath;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachementPath() {
        return attachementPath;
    }

    public void setAttachementPath(String attachementPath) {
        this.attachementPath = attachementPath;
    }
}
