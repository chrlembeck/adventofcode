package de.chrlembeck.aoc2019.day13;

import de.chrlembeck.aoc2019.day11.Position;
import java.math.BigInteger;
import java.util.Map;
import java.util.function.Consumer;

public class ArcadeOutputConsumer implements Consumer<BigInteger> {

    private final Map<Position, Tile> map;

    private final Consumer<BigInteger> inputConsumer;

    private int mode;

    @SuppressWarnings("PMD.SingularField")
    private int xFromOutput;

    @SuppressWarnings("PMD.SingularField")
    private int yFromOutput;

    private BigInteger score;

    private Position ballPosition;

    private Position paddlePosition;

    private boolean gameStarted;

    public ArcadeOutputConsumer(final Map<Position, Tile> map, final Consumer<BigInteger> inputConsumer) {
        this.map = map;
        this.inputConsumer = inputConsumer;
    }

    @Override
    public void accept(final BigInteger out) {
        switch (mode) {
            case 0:
                xFromOutput = out.intValueExact();
                break;
            case 1:
                yFromOutput = out.intValueExact();
                break;
            case 2:
                if (xFromOutput == -1 && yFromOutput == 0) {
                    score = out;
                    if (!gameStarted) {
                        gameStarted = true;
                        adjustPaddle();
                    }
                } else {
                    final Tile tile = Tile.fromId(out.intValueExact());
                    map.put(new Position(xFromOutput, yFromOutput), tile);
                    if (tile == Tile.BALL) {
                        ballPosition = new Position(xFromOutput, yFromOutput);
                        if (gameStarted) {
                            adjustPaddle();
                        }
                    }
                    if (tile == Tile.HORIZONTAL_PADDLE) {
                        this.paddlePosition = new Position(xFromOutput, yFromOutput);
                    }

                }
                break;
            default:
                throw new IllegalStateException();
        }
        mode = (mode + 1) % 3;
    }

    private void adjustPaddle() {
        inputConsumer.accept(BigInteger.valueOf(Integer.signum(ballPosition.getPosX() - paddlePosition.getPosX())));
    }

    public BigInteger getScore() {
        return score;
    }

    public long countBlocks() {
        return map.values().stream().filter(Tile.BLOCK::equals).count();
    }
}