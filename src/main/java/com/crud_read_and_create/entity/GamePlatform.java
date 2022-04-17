package com.crud_read_and_create.entity;

public class GamePlatform {
	private String id;
	private Integer gameId;
	private String platformId;

	public GamePlatform(Integer gameId, String platformId) {
		this.gameId = gameId;
		this.platformId = platformId;
	}

	public String getId() {
		return id;
	}

	public Integer getGameId() {
		return gameId;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

}
