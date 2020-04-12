package mk.kirca.games.demo.web;

import mk.kirca.games.demo.model.*;
import mk.kirca.games.demo.repository.CategoryRepository;
import mk.kirca.games.demo.repository.ManufacturerRepository;
import mk.kirca.games.demo.service.VideoGameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VideoGameController {
    private VideoGameService videoGameService;

    private CategoryRepository categoryRepository;

    private ManufacturerRepository manufacturerRepository;

    @Value("${stripe.pk}")
    private String stripePublicKey;

    public VideoGameController(VideoGameService videoGameService, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.videoGameService = videoGameService;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/videoGames")
    public ModelAndView videoGames() {
        ModelAndView modelAndView = new ModelAndView("videoGames");
        List<VideoGame> videoGames = this.videoGameService.getAllVideoGames();
        modelAndView.addObject("videoGames", videoGames);
        return modelAndView;
    }

    @GetMapping("/videoGames/{id}")
    public ModelAndView videoGame(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("videoGame");
        VideoGame videoGame = this.videoGameService.getVideoGameById(id);
        modelAndView.addObject("videoGame", videoGame);
        modelAndView.addObject("amount", (int) (videoGame.getPrice() * 100));
        modelAndView.addObject("currency", ChargeRequest.Currency.EUR.name());
        modelAndView.addObject("stripePublicKey", stripePublicKey);

        return modelAndView;
    }

    @GetMapping("/videoGames/edit/{id}")
    public ModelAndView editVideoGamePage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-videoGame");
        VideoGame videoGameToEdit = this.videoGameService.getVideoGameById(id);
        VideoGameDTO videoGame = new VideoGameDTO();
        List<Category> categories = this.categoryRepository.findAll();
        List<Manufacturer> manufacturers = this.manufacturerRepository.findAll();
        videoGame.setId(videoGameToEdit.getId());
        videoGame.setDescription(videoGameToEdit.getDescription());
        videoGame.setLink(videoGameToEdit.getLink());
        videoGame.setName(videoGameToEdit.getName());
        videoGame.setPrice(videoGameToEdit.getPrice());
        videoGame.setCategoryId(videoGameToEdit.getCategory().getId());
        videoGame.setManufacturerId(videoGameToEdit.getManufacturer().getId());

        modelAndView.addObject("videoGame", videoGame);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("manufacturers", manufacturers);

        return modelAndView;
    }

    @PostMapping("/videoGames/editvideogame")
    public ModelAndView editVideoGame(@ModelAttribute VideoGameDTO videoGameDTO) {
        ModelAndView modelAndView = new ModelAndView("redirect:/videoGames");
        VideoGame videoGame = this.videoGameService.getVideoGameById(videoGameDTO.getId());
        videoGame.setName(videoGameDTO.getName());
        videoGame.setDescription(videoGameDTO.getDescription());
        videoGame.setLink(videoGameDTO.getLink());
        videoGame.setPrice(videoGameDTO.getPrice());
        for (Category category : this.categoryRepository.findAll()) {
            if (category.getId().equals(videoGameDTO.getCategoryId())) {
                videoGame.setCategory(category);
                break;
            }
        }
        for (Manufacturer manufacturer : this.manufacturerRepository.findAll()) {
            if (manufacturer.getId().equals(videoGameDTO.getManufacturerId())) {
                videoGame.setManufacturer(manufacturer);
                break;
            }
        }

        this.videoGameService.editVideoGame(videoGame);

        return modelAndView;
    }

    @GetMapping("/videoGames/add")
    public ModelAndView getAddVideoGamePage() {
        ModelAndView modelAndView = new ModelAndView("add-videoGame");
        List<Category> categories = this.categoryRepository.findAll();
        List<Manufacturer> manufacturers = this.manufacturerRepository.findAll();
        VideoGameDTO videoGame = new VideoGameDTO();

        modelAndView.addObject("videoGame", videoGame);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("manufacturers", manufacturers);

        return modelAndView;
    }

    @PostMapping("/videoGames")
    public ModelAndView createVideoGame(@ModelAttribute VideoGameDTO videoGame) {
        ModelAndView modelAndView = new ModelAndView("redirect:/videoGames");
        VideoGame newVideoGame = new VideoGame();
        newVideoGame.setId(videoGame.getId());
        newVideoGame.setName(videoGame.getName());
        newVideoGame.setDescription(videoGame.getDescription());
        newVideoGame.setLink(videoGame.getLink());
        newVideoGame.setPrice(videoGame.getPrice());

        List<Category> categories = this.categoryRepository.findAll();
        for (Category category : categories) {
            if (category.getId().equals(videoGame.getCategoryId())) {
                newVideoGame.setCategory(category);
                break;
            }
        }
        List<Manufacturer> manufacturers = this.manufacturerRepository.findAll();
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.getId().equals(videoGame.getManufacturerId())) {
                newVideoGame.setManufacturer(manufacturer);
                break;
            }
        }

        this.videoGameService.addVideoGame(newVideoGame);

        return modelAndView;
    }

    @GetMapping("/videoGames/delete/{id}")
    public ModelAndView deleteVideoGame(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/videoGames");
        this.videoGameService.deleteVideoGame(id);
        return modelAndView;
    }
}
