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

    @Select({"select", detailed_information, "from", table_name, "where ${options} like '${keyword}'"})
    List<Information> normalSearch(@Param("options") String options,@Param("keyword") String keyword);

    @Insert({"insert into", table_name,
            "(`university`,`name`,`phone_number`,`title`,`mailbox`,`college`," +
                    "`lab`,`resume`,`education_experience`,`work_experience`," +
                    "`research_direction`,`courses`,`scientific_research_item`," +
                    "`monograph`,`patent`,`awards`) " +
            "values " +
                    "(#{university},#{name},#{phoneNumber},#{title},#{mailbox},#{college}," +
                    "#{lab},#{resume},#{educationExperience},#{workExperience}," +
                    "#{researchDirection},#{courses},#{scientificResearchItem}," +
                    "#{monograph},#{patent},#{awards})"})
    void add(Information information);

    @Update({"update ", table_name, " set state=#{state} where id=#{id}"})
    void changeState(@Param("id") int id,@Param("state") int state);

    @Update({"update", table_name, "set university = #{university}," +
            "name = #{name}, phone_number = #{phoneNumber}, " +
            "title = #{title}, mailbox = #{mailbox}, " +
            "college = #{college}, lab = #{lab}, " +
            "resume = #{resume}, education_experience = #{educationExperience}," +
            "work_experience = #{workExperience}, research_direction = #{researchDirection}, " +
            "courses = #{courses}, scientific_research_item = #{scientificResearchItem}, " +
            "monograph = #{monograph}, patent = #{patent}, awards = #{awards} " +
            "where id = #{id}"})
    void change(Information information);

}
