package com.yukon.ita.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yukon.ita.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "templates")
@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long templateId;

    private String type;

    private String file;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "template_user", joinColumns = {@JoinColumn(name = "template_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}