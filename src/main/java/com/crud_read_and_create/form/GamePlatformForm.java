package com.crud_read_and_create.form;

public class GamePlatformForm {
	private String id;
	private String gameId;
	private String platformId;

	public GamePlatformForm(String gameId, String platformId) {
		super();
		this.gameId = gameId;
		this.platformId = platformId;
	}

	public String getId() {
		return id;
	}

	public String getGameId() {
		return gameId;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

}
