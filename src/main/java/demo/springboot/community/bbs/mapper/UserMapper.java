package demo.springboot.community.bbs.mapper;

import demo.springboot.community.bbs.model.User;
import org.apache.ibatis.annotations.*;

// mybatis integration, insert data from the model "user" into datasource(mysql database)
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{account},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl}, bio = #{bio} where id = #{id}")
    void update(User user);

}
