package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    private final Game game;

    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    @PostConstruct
    void init() {
        System.out.println(game.toString());
    }

    @Override
    public String getMainMessage() {
        return String.format("Number is between %n and %n, can you guess it?", game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()) {
            return String.format("You guessed it! the number was %d", game.getNumber());
        } else if (game.isGameLost()) {
            return String.format("You lost. The number was %d", game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return "Invalid Number range";
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return "What is your first guess";
        } else {
            String direction = "Lower";

            if (game.getGuess() < game.getNumber()) {
                direction = "Higher";
            }

            return String.format("%s! You have %d guesses remaining", direction, game.getRemainingGuesses());
        }
    }
}
