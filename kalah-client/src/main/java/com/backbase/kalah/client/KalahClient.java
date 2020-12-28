package com.backbase.kalah.client;

import com.backbase.kalah.dto.GameDto;
import com.backbase.kalah.exceptions.GameNotFoundException;
import com.backbase.kalah.exceptions.MoveNotAllowedException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.backbase.kalah.api.ApiGames.ENDPOINT_GAMES;
import static com.backbase.kalah.api.ApiGames.ENDPOINT_GAME_ID;
import static com.backbase.kalah.api.ApiGames.ENDPOINT_MOVE;
import static org.springframework.http.RequestEntity.get;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.http.RequestEntity.put;

@RequiredArgsConstructor
public class KalahClient {

    private final RestTemplate rest;
    private final URI baseUri;

    public GameDto createGame() {
        val request = post(baseUri + ENDPOINT_GAMES)
                .build();

        val response = rest.exchange(request, GameDto.class);
        return response.getBody();
    }

    public GameDto getGameById(long gameId) {
        val request = get(baseUri + ENDPOINT_GAMES + ENDPOINT_GAME_ID, gameId)
                .build();

        try {
            val response = rest.exchange(request, GameDto.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound notFound) {
            throw new GameNotFoundException(gameId);
        }
    }

    public GameDto makeMove(long gameId, int pitId) {
        val request = put(baseUri + ENDPOINT_GAMES + ENDPOINT_MOVE, gameId, pitId)
                .build();

        try {
            val response = rest.exchange(request, GameDto.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound notFound) {
            throw new GameNotFoundException(gameId);
        } catch (HttpClientErrorException.BadRequest badRequest) {
            throw new MoveNotAllowedException();
        }

    }

}
