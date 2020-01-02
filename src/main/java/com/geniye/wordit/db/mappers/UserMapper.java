package com.geniye.wordit.db.mappers;

import com.geniye.wordit.core.models.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
  @Insert(
    "insert into users (username, password, email, bio, image) " +
    "values (#{username}, #{password}, #{email}, #{bio}, #{image})"
  )
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
  void save(User user);

  @Update(
    "update users set " +
    "email = #{email}, " +
    "password = #{password}, " +
    "bio = #{bio}, " +
    "image = #{image} " +
    "where id = #{id}"
  )
  int update(User user);

  @Select("select * from users where id = #{id}")
  User findById(@Param("id") long id);

  @Select("select * from users where username = #{username}")
  User findByUsername(@Param("username") String username);

  @Select("select * from users where email = #{email}")
  User findByEmail(@Param("email") String email);
}
