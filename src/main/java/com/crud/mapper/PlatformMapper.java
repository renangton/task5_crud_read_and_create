package com.crud.mapper;

import com.crud.entity.Platform;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformMapper {

  public List<Platform> findPlatform();

  public Optional<Platform> findByIdPlatform(Integer id);

  public void createPlatform(Platform platformData);

  public void updatePlatform(Platform platformData);

  public void deleteGamePlatformPlatformId(Integer id);

  public void deletePlatformAndGamePlatform(Integer id);

  public void deletePlatform(Integer id);
}
