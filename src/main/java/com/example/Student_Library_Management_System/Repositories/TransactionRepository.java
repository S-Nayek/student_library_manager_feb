package com.example.Student_Library_Management_System.Repositories;

import com.example.Student_Library_Management_System.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.AbstractList;
import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Integer> {

    @Query(value = "select * from transactions  where book_id =BookId and card_id=CardId and is_issue=true",
    nativeQuery = true)
    List<Transactions> listOfTransactionForBookAndCars(int BookId,int CardId);
}
