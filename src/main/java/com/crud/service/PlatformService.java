package com.crud.service;

import com.crud.entity.Platform;
import com.crud.mapper.PlatformMapper;
import com.crud.service.exception.DuplicateException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlatformService {
  private final PlatformMapper platformMapper;

  public PlatformService(PlatformMapper platformMapper) {
    this.platformMapper = platformMapper;
  }

  public List<Platform> getPlatform() {
    return platformMapper.findPlatform();
  }

  public void createPlatform(Integer id, String platform) throws DuplicateException {
    List<Platform> platformList = platformMapper.findPlatform();
    if (platformList.stream().anyMatch(registeredPlatform -> registeredPlatform.getPlatform().equals(platform))) {
      throw new DuplicateException("プラットフォームが重複しています。");
    }
    Platform platformData = new Platform(id, platform);
    platformMapper.createPlatform(platformData);
  }

  public void updatePlatform(Integer id, String platform) throws DuplicateException {
    List<Platform> platformList = platformMapper.findPlatform();
    if (platformList.stream().anyMatch(registeredPlatform -> registeredPlatform.getPlatform().equals(platform))) {
      throw new DuplicateException("プラットフォームが重複しています。");
    }
    Platform platformData = new Platform(id, platform);
    platformMapper.updatePlatform(platformData);
  }

  @Transactional
  public void deletePlatform(Integer id) {
    platformMapper.deleteGamePlatformPlatformId(id);
    platformMapper.deletePlatform(id);
  }
}
