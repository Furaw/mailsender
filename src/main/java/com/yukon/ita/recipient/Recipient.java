package com.yukon.ita.recipient;

import com.yukon.ita.template.Template;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Table(name = "recipients")
@Entity
public class Recipient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipient_id")
    private Long recipientId;

    private String recipientEmail;
    private String recipientSubject;
    private String recipientText;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Template> templates;



    public Recipient(Long recipientId, String recipientEmail, String recipientSubject, String recipientText, List<Template> templates) {
        this.recipientId = recipientId;
        this.recipientEmail = recipientEmail;
        this.recipientSubject = recipientSubject;
        this.recipientText = recipientText;
        this.templates = templates;
    }
    public Recipient(String recipientEmail, String recipientSubject, String recipientText, List<Template> templates) {
        this.recipientEmail = recipientEmail;
        this.recipientSubject = recipientSubject;
        this.recipientText = recipientText;
        this.templates = templates;
    }

    public Recipient(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public Recipient() {
    }



    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientEmail() {
        return this.recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientSubject() {
        return this.recipientSubject;
    }

    public void setRecipientSubject(String recipientSubject) {
        this.recipientSubject = recipientSubject;
    }

    public String getRecipientText() {
        return this.recipientText;
    }
    public void setRecipientText(String recipientText) {
        this.recipientText = recipientText;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(recipientId, recipient.recipientId) &&
                Objects.equals(recipientEmail, recipient.recipientEmail) &&
                Objects.equals(recipientSubject, recipient.recipientSubject) &&
                Objects.equals(recipientText, recipient.recipientText) &&
                Objects.equals(templates, recipient.templates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipientId, recipientEmail, recipientSubject, recipientText, templates);
    }
}

