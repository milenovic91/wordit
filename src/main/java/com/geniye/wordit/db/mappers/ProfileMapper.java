package com.geniye.wordit.db.mappers;

import com.geniye.wordit.core.models.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProfileMapper {
  public Profile getByUsername(@Param("currentUser") Long currentUser, @Param("username") String username);

  public int follow(@Param("currentUser") Long currentUser, @Param("followee") Long followee);

  public int unfollow(@Param("currentUser") Long currentUser, @Param("followee") Long followee);
}
