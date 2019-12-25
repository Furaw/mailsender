package com.yukon.ita.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TemplateDTO {

    private int templateId;

    private String type;

    private String file;

    public TemplateDTO(int templateId, String type, String file) {
        this.templateId = templateId;
        this.type = type;
        this.file = file;
    }

    public int getTemplateId() {
        return templateId;
    }

    public String getType() {
        return type;
    }
    public String getFile() {
        return file;
    }
}