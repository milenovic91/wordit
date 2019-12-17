package com.geniye.wordit.db.mappers;

import com.geniye.wordit.core.models.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public interface UserMapper {
  @Insert(
    "insert into users (username, password, email, bio, image) " +
    "values (#{username}, #{password}, #{email}, #{bio}, #{image})"
  )
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
  void save(User user);

  @Select("select * from users where id = #{id}")
  User findById(@Param("id") long id);

  @Select("select * from users where username = #{username}")
  User findByUsername(@Param("username") String username);

  @Select("select * from users where email = #{email}")
  User findByEmail(@Param("email") String email);
}
