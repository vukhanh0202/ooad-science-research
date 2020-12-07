package com.uit.ooad.scienceresearch.mapper.topic.converter;

import com.uit.ooad.scienceresearch.entity.Faculty;
import com.uit.ooad.scienceresearch.entity.FieldTopic;
import com.uit.ooad.scienceresearch.entity.Level;
import com.uit.ooad.scienceresearch.repository.FacultyRepository;
import com.uit.ooad.scienceresearch.repository.FieldTopicRepository;
import com.uit.ooad.scienceresearch.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/7/2020
 */
@Component
public class TopicConverter {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    FieldTopicRepository fieldTopicRepository;


    public Faculty getFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId).orElse(null);
    }

    public Level getLevel(Long levelId) {
        return levelRepository.findById(levelId).orElse(null);
    }

    public FieldTopic getFieldTopic(Long fieldTopicId) {
        return fieldTopicRepository.findById(fieldTopicId).orElse(null);
    }
}
