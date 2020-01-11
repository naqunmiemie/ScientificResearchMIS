package org.yjn.mis.dao;

import org.apache.ibatis.annotations.*;
import org.yjn.mis.model.Information;

import java.util.List;

@Mapper
public interface InformationDao {
    String table_name = " Information ";
    String basic_information =
            "id, university, name, phone_number, title, mailbox, college, research_direction, state ";
    String detailed_information = basic_information + ", lab, resume, education_experience, " +
            "work_experience, courses, scientific_research_item, monograph, patent, awards, update_time";

    String update_time = "update_time";
    String state = "state";

    @Select({"select", basic_information, "from", table_name })
    List<Information> select();

    @Select({"select", detailed_information, "from", table_name, "where id = #{id}"})
    List<Information> selectId(@Param("id") int id);


    @Insert({"insert into", table_name,
            "(`university`,`name`,`phone_number`,`title`,`mailbox`,`college`," +
                    "`lab`,`resume`,`education_experience`,`work_experience`," +
                    "`research_direction`,`courses`,`scientific_research_item`," +
                    "`monograph`,`patent`,`awards`) " +
            "values " +
                    "(#{university},#{name},#{phone_number},#{title},#{mailbox},#{college}," +
                    "#{lab},#{resume},#{education_experience},#{work_experience}," +
                    "#{research_direction},#{courses},#{scientific_research_item}," +
                    "#{monograph},#{patent},#{awards})"})
    int add(Information information);

    @Update({"update ", table_name, " set state=#{state} where id=#{id}"})
    void changeState(@Param("id") int id);

}
