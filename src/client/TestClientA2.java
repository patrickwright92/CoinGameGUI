package client;

import java.util.logging.Level;
import java.util.logging.Logger;

import src.model.GameEngineImpl;
import src.model.SimplePlayer;
import src.model.enumeration.BetType;
import src.model.interfaces.GameEngine;
import src.model.interfaces.Player;
import validate.Validator;
import view.GameEngineCallbackGUI;
import view.components.GameFrame;
import src.view.GameEngineCallbackImpl;

/**
 * <pre> Simple console client for Further Programming assignment 2, 2019
 * <b>NOTE:</b> This code will not compile until you have implemented code for the supplied interfaces!
 * 
 * You must be able to compile your code WITHOUT modifying this class.
 * Additional testing should be done by copying and adding to this class while ensuring this class still works.
 * 
 * The provided Validator.jar will check if your code adheres to the specified interfaces!</pre>
 * 
 * @author Caspar Ryan
 * 
 */
public class TestClientA2
{
   private static final Logger logger = Logger.getLogger(TestClientA2.class.getName());

   public static void main(String args[])
   {
      final GameEngine gameEngine = new GameEngineImpl();
   
     
      //final GameFrame gameFrame = new GameFrame(gameEngine);

      // call method in Validator.jar to test *structural* correctness
      // just passing this does not mean it actually works .. you need to test yourself!
      // pass false if you want to show minimal logging (pass/fail) .. (i.e. ONLY once it passes)
      //Validator.validate(true);

      // create some test players
      Player[] players = new Player[] { new SimplePlayer("1", "The Coin Master", 1000),
         new SimplePlayer("2", "The Loser", 750), new SimplePlayer("3", "The Dabbler", 500) };

      // add logging callback
      gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
      

      // main loop to add players and place a bet
      int enumOrdinal = 0;
      for (Player player : players)
      {
         gameEngine.addPlayer(player);
         // mod with BetType length so we always stay in range even if num players increases
         // NOTE: we are passing a different BetType each time!
//         gameEngine.placeBet(player, 100, BetType.values()[enumOrdinal++ % BetType
//            .values().length]);
//         gameEngine.spinPlayer(player, 100, 1000, 100, 50, 500, 50);
      }
      final GameFrame gameFrame = new GameFrame(gameEngine);
     gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameFrame, gameEngine));
//      logger.log(Level.INFO, "SPINNING ...");
//      // OutputTrace.pdf was generated with these parameter values (using only first 3 params as per spec)
//      gameEngine.spinSpinner(100, 1000, 200, 50, 500, 25);

      // reset bets for next round if you were playing again
   }
}
