package com.crud_read_and_create.service.exception;

import com.crud_read_and_create.entity.Platform;
import com.crud_read_and_create.mapper.PlatformMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
