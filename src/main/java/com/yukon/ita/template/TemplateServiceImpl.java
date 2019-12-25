package com.yukon.ita.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository){
        this.templateRepository = templateRepository;
    }

    @Override
    public Template insert(Template template) {
        return templateRepository.save(template);
    }

    @Override
    public List<Template> findAll() {
        return templateRepository.findAll();
    }

    @Override
    public Optional<Template> findById(Long id) {
        return templateRepository.findById(id);
    }

    @Override
    public Template update(Template template, Long id) {
        template.setTemplateId(id);
        return templateRepository.save(template);
    }

    @Override
    public void deleteById(Long id) {
        templateRepository.deleteById(id);
    }
}
