package mk.kirca.games.demo.service;

import mk.kirca.games.demo.model.Transaction;
import mk.kirca.games.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }
}
