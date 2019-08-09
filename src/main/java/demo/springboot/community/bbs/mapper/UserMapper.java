package demo.springboot.community.bbs.mapper;

import demo.springboot.community.bbs.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

// mybatis integration, insert data from the model "user" into datasource(mysql database)
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
