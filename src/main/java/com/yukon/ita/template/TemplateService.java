package com.yukon.ita.template;

import java.util.List;
import java.util.Optional;

public interface TemplateService {

    Template insert(Template template);

    List<Template> findAll();

    Optional<Template> findById(Long id);

    Template update(Template template, Long id);

    void deleteById(Long id);
}
