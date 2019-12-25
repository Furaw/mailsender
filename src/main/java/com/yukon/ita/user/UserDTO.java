package com.yukon.ita.user;

import com.yukon.ita.template.Template;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    private String email;

    private String password;

    private String role;

    private List<Template> templates;
}
