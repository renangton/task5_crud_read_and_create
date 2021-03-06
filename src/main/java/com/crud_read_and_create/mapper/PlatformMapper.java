package com.crud_read_and_create.mapper;

import com.crud_read_and_create.entity.Platform;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlatformMapper {

    public List<Platform> findPlatform();

    public void createPlatform(Platform platformData);

    public void updatePlatform(Platform platformData);

    public void deleteGamePlatformPlatformId(Integer id);

    public void deletePlatformAndGamePlatform(Integer id);

    public void deletePlatform(Integer id);
}
