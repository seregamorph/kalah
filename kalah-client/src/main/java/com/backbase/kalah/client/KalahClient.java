package com.backbase.kalah.client;

import static com.backbase.kalah.api.ApiGames.ENDPOINT_GAMES;
import static org.springframework.http.RequestEntity.post;

import com.backbase.kalah.dto.GameDto;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class KalahClient {

    private final RestTemplate rest;
    private final URI baseUri;

    public GameDto createGame() {
        val request = post(baseUri + ENDPOINT_GAMES)
                .build();

        //return rest.;
        throw new UnsupportedOperationException();
    }

}
