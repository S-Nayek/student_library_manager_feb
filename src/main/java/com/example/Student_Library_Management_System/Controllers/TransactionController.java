package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.IssueBookReqDto;
import com.example.Student_Library_Management_System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/issueBook")
    public String issueBook(@RequestBody IssueBookReqDto issueBookReqDto) throws Exception {
        return transactionService.issueBook(issueBookReqDto);
    }
    @GetMapping("/transactionInfo")
    public String getTransactionInfo(@RequestParam("bookId") int bookId,@RequestParam("cardId") int cardId){
        return transactionService.getTransactionInfo(bookId,cardId);
    }

}
