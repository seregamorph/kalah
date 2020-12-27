package com.backbase.kalah.repositories;

import com.backbase.kalah.model.Game;
import com.backbase.kalah.model.Pits;
import com.backbase.kalah.model.PlayerId;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

@Component
@RequiredArgsConstructor
public class GameRepository {

    private final NamedParameterJdbcTemplate namedJdbc;

    public Game createNewGame(int stones) {
        val game = new Game(stones);
        val keyHolder = new GeneratedKeyHolder();
        namedJdbc.update("INSERT INTO game (pits) VALUES (:pits)",
                new MapSqlParameterSource("pits", game.getPits().encode()),
                keyHolder);
        long id = keyHolder.getKeyAs(Long.class);
        return game.setId(id);
    }

    @Nullable
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
            return null;
        }
    }

    public void save(Game game) {
        val currentPlayer = game.getCurrentPlayer();
        val currentPlayerNum = currentPlayer == null ? 0 : currentPlayer.getNum();
        val pitsEncoded = game.getPits().encode();
        val updated = namedJdbc.update(
                "UPDATE game SET " +
                        "current_player = :current_player, " +
                        "pits = :pits, " +
                        "version = :next_version " +
                        "WHERE id = :id AND " +
                        "version = :current_version",
                new MapSqlParameterSource()
                        .addValue("id", game.getId())
                        .addValue("current_player", currentPlayerNum)
                        .addValue("pits", pitsEncoded)
                        .addValue("current_version", game.getVersion())
                        .addValue("next_version", game.getVersion() + 1));
        if (updated != 1) {
            throw new IllegalStateException("Data race detected, concurrent change in other transaction");
        }
    }
}
