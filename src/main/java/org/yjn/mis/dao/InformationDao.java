package org.yjn.mis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yjn.mis.model.Information;

import java.util.List;

@Mapper
public interface InformationDao {
    String table_name = " Information ";
    String basic_information =
            "id, university, name, phone_number, title, mailbox, college, research_direction, state ";
    String detailed_information = basic_information + "lab, resume, education_experience, " +
            "work_experience, courses, scientific_research_item, monograph, patent, awards";
    String update_time = "update_time";
    String state = "state";

    @Select({"select", basic_information, "from", table_name})
    List<Information> select();
}
