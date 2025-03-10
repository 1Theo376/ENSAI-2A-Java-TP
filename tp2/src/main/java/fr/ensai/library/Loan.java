package fr.ensai.library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {
    private Item item;
    private Student borrower;
    private LocalDate startDate;
    private LocalDate returnDate;

    public Loan(Item item, Student borrower, LocalDate startDate) {
        this.item = item;
        this.borrower = borrower;
        this.startDate = startDate;
        this.returnDate = null; 
    }

    public Item getItem() {
        return item;
    }

    public Student getStudent() {
        return borrower;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate newReturnDate) {
        this.returnDate = newReturnDate;
    }

    public void completeLoan(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String startDateFormatted = startDate.format(formatter);
        String returnDateFormatted = (returnDate != null) ? returnDate.format(formatter) : "Not returned yet";
        return "Item: " + item.getTitle() + ", Borrowed by: " + borrower.getName() + ", Start Date: " + startDateFormatted + ", Return Date: " + returnDateFormatted;
    }
}
