package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookReqDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;


    public String issueBook(IssueBookReqDto issueBookReqDto) throws Exception {
        int bookId=issueBookReqDto.getBookId();
        int cardId=issueBookReqDto.getCardId();

        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        Transactions transactions =new Transactions();
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssue(true);

        if(book==null || book.isIssued()==true){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book is not available");
        }
        if(card==null || card.getCardStatus()!= CardStatus.ACTIVATED){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("card is not activated ");
        }

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIssued(true);
        List<Transactions> listOfTransactionforBook = book.getTransactionsList();
        listOfTransactionforBook.add(transactions);
        book.setTransactionsList(listOfTransactionforBook);

        List<Book> issuedBookForCard =card.getBookIssued();
        issuedBookForCard.add(book);
        card.setBookIssued(issuedBookForCard);

        List<Transactions> listOfTransactionForCard =card.getTransactionsList();
        listOfTransactionForCard.add(transactions);
        card.setTransactionsList(listOfTransactionForCard);

        cardRepository.save(card);


        return "Book Issued";
    }
    public String getTransactionInfo(int bookId,int cardId){
        List<Transactions> transactionsList=transactionRepository.listOfTransactionForBookAndCars(bookId,cardId);
        return transactionsList.get(0).getTransactionId();
    }

}
