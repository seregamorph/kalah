package com.backbase.kalah.api;

public interface ApiGames {

    String ENDPOINT_GAMES = "/games";

    String PARAM_GAME_ID = "gameId";
    String PARAM_PIT_ID = "pitId";
    String ENDPOINT_GAME_ID = "/{" + PARAM_GAME_ID + "}";
    String ENDPOINT_MOVE = ENDPOINT_GAME_ID + "/pits/{" + PARAM_PIT_ID + "}";

}
