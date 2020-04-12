package mk.kirca.games.demo.web;

import mk.kirca.games.demo.model.VideoGame;
import mk.kirca.games.demo.service.VideoGameService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RestControllerVideoGames {
    private final VideoGameService videoGameService;

    public RestControllerVideoGames(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @GetMapping("/rest/videoGames")
    public List<VideoGame> getVideoGames() {
        return this.videoGameService.getAllVideoGames();
    }

    @GetMapping("/rest/videoGames/recommended")
    public List<VideoGame> getRecommendedVideoGames() {
        return this.videoGameService.getAllVideoGames().subList(0,3);
    }

    @GetMapping("/rest/videoGames/{id}")
    public VideoGame getVideoGameById(@PathVariable Long id) {
        return this.videoGameService.getVideoGameById(id);
    }

    @GetMapping("rest/videoGame/category/{categoryId}")
    public List<VideoGame> getVideoGameByCategoryId(@PathVariable Long categoryId) {
        return this.videoGameService.getAllVideoGamesByCategoryId(categoryId);
    }

    @GetMapping("rest/videoGame/category/{categoryId}/manufacturer/{manufacturerId}")
    public List<VideoGame> getVideoGameByCategoryId(@PathVariable Long categoryId, @PathVariable Long manufacturerId) {
        return this.videoGameService.getAllVideoGamesByCategoryIdAndManufacturerId(categoryId, manufacturerId);
    }

    @GetMapping("/rest/videoGame/category/{categoryId}/price")
    public Map<String, Double> getVideoGamesPriceByCategoryId(@PathVariable Long categoryId) {
        Double videoGamesPrice = this.videoGameService.getVideoGamesPriceByCategoryId(categoryId);
        Map<String, Double> response = new HashMap<>();
        response.put("price", videoGamesPrice);

        return response;
    }
}
