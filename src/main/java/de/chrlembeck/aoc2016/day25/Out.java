package de.chrlembeck.aoc2016.day25;

import de.chrlembeck.aoc2016.day12.Environment;
import de.chrlembeck.aoc2016.day12.Expression;
import de.chrlembeck.aoc2016.day12.Inc;
import de.chrlembeck.aoc2016.day12.Operation;

public class Out implements Operation {

    private Expression expression;

    public Out(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute(Environment env) {
        env.output(expression.evaluate(env));
        env.incPC();
    }

    @Override
    public Operation toggle() {
        return new Inc(expression);
    }
}
