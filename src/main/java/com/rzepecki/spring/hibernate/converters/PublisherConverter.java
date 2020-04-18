package com.rzepecki.spring.hibernate.converters;

import com.rzepecki.spring.hibernate.domain.dao.PublisherDao;
import com.rzepecki.spring.hibernate.domain.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class PublisherConverter implements Converter<String, Publisher> {

    private PublisherDao publisherDao;

    @Autowired
    public void setPublisherDao(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public Publisher convert(String source) {
        Long id = Long.parseLong(source);
        Publisher publisher = publisherDao.getById(id);
        if(publisher==null){
            throw new IllegalStateException("Publisher not found");
        }
        return publisher;
    }
}
