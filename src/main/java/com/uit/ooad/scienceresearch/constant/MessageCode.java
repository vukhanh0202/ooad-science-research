package com.uit.ooad.scienceresearch.constant;

/**
 * @author VuKhanh [18520903@gm.uit.edu.vn]
 * @project Manage Science Research
 * @since 12/15/2020
 */
public interface MessageCode {
    interface Lecturer {
        String LECTURER_NOT_FOUND = "message.lecturer.LecturerNotFound";
    }
    interface Faculty {
        String FACULTY_NOT_FOUND = "message.faculty.FacultyNotFound";
        String FACULTY_EXIST = "message.faculty.FacultyExist";
    }
    interface Contract {
        String CONTRACT_NOT_FOUND = "message.contract.ContractNotFound";
        String CONTRACT_EXIST = "message.contract.ContractExist";
    }
    interface Level {
        String LEVEL_NOT_FOUND = "message.level.LevelNotFound";
    }
    interface Field {
        String FIELD_NOT_FOUND = "message.field.FieldNotFound";
    }
    interface User {
        String USER_NOT_FOUND = "message.user.UserNotFound";
        String USER_EXIST = "message.user.UserExist";
    }

    interface Topic {
        String TOPIC_NOT_FOUND = "message.user.TopicNotFound";
        String TOPIC_EXIST = "message.user.TopicExist";
    }
    interface Token {
        String INVALID_TOKEN = "message.token.InvalidToken";
    }
    interface Register {
        String EMPTY_REGISTER = "message.register.EmptyLecturer";
        String BAD_REQUEST = "message.register.BadRequest";
    }
}
