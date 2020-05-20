package com.jiangweijia.community.mapper;

import com.jiangweijia.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Fisherman
 * @date 2020/5/20 22:09
 */
@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO question (title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
     void create(Question question);

}
