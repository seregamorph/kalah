# Kalah
## Rules
You can find rules in [Wikipedia](https://en.wikipedia.org/wiki/Kalah).

## Flow
Create the game via `POST /games`, then to make a move `PUT /games/{gameId}/pits/{pitId}`.
To get the current state you can call `GET /games/{gameId}`.
New game is created with 6 stones, it can be configured via spring-boot custom configuration.

