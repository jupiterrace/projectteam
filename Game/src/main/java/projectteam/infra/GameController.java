package projectteam.infra;
import projectteam.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 @RestController
 @RequestMapping(value="/games")
 public class GameController {
        @Autowired
        GameRepository gameRepository;

        @RequestMapping(value = "/{id}/release",
                method = RequestMethod.PUT,
                produces = "application/json;charset=UTF-8")
        public Game release(@PathVariable(value = "id") Long id, HttpServletRequest request, HttpServletResponse response)
                throws Exception {
                        System.out.println("##### /game/release  called #####");
                        Optional<Game> optionalGame = gameRepository.findById(id);
                        
                        optionalGame.orElseThrow(()-> new Exception("No Entity Found"));
                        Game game = optionalGame.get();
                        game.release();
                        
                        gameRepository.save(game);
                        return game;
                }

 }
