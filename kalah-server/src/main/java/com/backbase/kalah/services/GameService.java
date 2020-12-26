package com.backbase.kalah.services;

import com.backbase.kalah.config.GameProperties;
import com.backbase.kalah.exceptions.NotFoundException;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.model.Pits;
import com.backbase.kalah.model.PlayerId;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final NamedParameterJdbcTemplate namedJdbc;
    private final GameProperties gameProperties;

    public Game createNewGame() {
        val game = new Game(gameProperties.getStones());
        val keyHolder = new GeneratedKeyHolder();
        namedJdbc.update("INSERT INTO game (pits) VALUES (:pits)",
                new MapSqlParameterSource("pits", game.getPits().encode()),
                keyHolder);
        long id = keyHolder.getKeyAs(Long.class);
        return game.setId(id);
    }

    public Game getById(long id) {
        try {
            return namedJdbc.queryForObject("SELECT id, current_player, pits, version FROM game WHERE id = :id",
                    new MapSqlParameterSource("id", id), (rs, rowNum) -> {
                        val currentPlayer = rs.getInt("current_player");
                        val pits = Pits.decode(rs.getString("pits"));
                        int version = rs.getInt("version");
                        return new Game(pits)
                                .setId(id)
                                .setCurrentPlayer(PlayerId.fromNum(currentPlayer))
                                .setVersion(version);
                    });
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new NotFoundException("Game " + id + " not found");
        }
    }

}
