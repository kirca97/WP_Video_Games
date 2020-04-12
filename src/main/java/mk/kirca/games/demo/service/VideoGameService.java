package mk.kirca.games.demo.service;

import mk.kirca.games.demo.model.VideoGame;

import java.util.List;

public interface VideoGameService {
    List<VideoGame> getAllVideoGames();

    VideoGame getVideoGameById(Long id);

    List<VideoGame> getAllVideoGamesByCategoryId(Long categoryId);

    List<VideoGame> getAllVideoGamesByCategoryIdAndManufacturerId(Long categoryId, Long manufacturerId);

    Double getVideoGamesPriceByCategoryId(Long categoryId);

    void addVideoGame(VideoGame videoGame);

    void deleteVideoGame(Long id);

    void editVideoGame(VideoGame videoGame);
}
