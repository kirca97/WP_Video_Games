package mk.kirca.games.demo.web;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import mk.kirca.games.demo.model.ChargeRequest;
import mk.kirca.games.demo.model.Transaction;
import mk.kirca.games.demo.model.VideoGame;
import mk.kirca.games.demo.service.StripePaymentService;
import mk.kirca.games.demo.service.TransactionService;
import mk.kirca.games.demo.service.VideoGameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class PaymentController {
    private final StripePaymentService stripePaymentService;

    private final TransactionService transactionService;

    private final VideoGameService videoGameService;

    public PaymentController(StripePaymentService stripePaymentService, TransactionService transactionService, VideoGameService videoGameService) {
        this.stripePaymentService = stripePaymentService;
        this.transactionService = transactionService;
        this.videoGameService = videoGameService;
    }

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = stripePaymentService.charge(chargeRequest);
        VideoGame videoGame = videoGameService.getVideoGameById(chargeRequest.getVideoGameId());

        Transaction transaction = new Transaction();
        transaction.setStripeId(charge.getId());
        transaction.setAmount(charge.getAmount());
        transaction.setVideoGame(videoGame);
        this.transactionService.saveTransaction(transaction);

        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
