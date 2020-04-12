package mk.kirca.games.demo.service;

import mk.kirca.games.demo.model.VideoGame;
import mk.kirca.games.demo.repository.VideoGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGameServiceImpl implements VideoGameService {
    private final VideoGameRepository videoGameRepository;

    public VideoGameServiceImpl(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }


    @Override
    public List<VideoGame> getAllVideoGames() {
        return this.videoGameRepository.findAll();
    }

    @Override
    public VideoGame getVideoGameById(Long id) {
        return this.videoGameRepository.findById(id).orElse(null);
    }

    @Override
    public List<VideoGame> getAllVideoGamesByCategoryId(Long categoryId) {
        return this.videoGameRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<VideoGame> getAllVideoGamesByCategoryIdAndManufacturerId(Long categoryId, Long manufacturerId) {
        return this.videoGameRepository.findByCategoryIdAndManufacturerId(categoryId, manufacturerId);
    }

    @Override
    public Double getVideoGamesPriceByCategoryId(Long categoryId) {
        List<VideoGame> videoGames = this.videoGameRepository.findByCategoryId(categoryId);
        double sum = 0;
        for(VideoGame videoGame : videoGames) {
            sum+=videoGame.getPrice();
        }
        return sum;
    }

    @Override
    public void addVideoGame(VideoGame videoGame) {
        this.videoGameRepository.save(videoGame);
    }

    @Override
    public void deleteVideoGame(Long id) {
        this.videoGameRepository.deleteById(id);
    }

    @Override
    public void editVideoGame(VideoGame videoGame) {
        this.videoGameRepository.save(videoGame);
    }
}
