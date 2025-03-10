package fr.ensai.library;

public class Magazine extends Item {

    private String issn;
    private int issueNumber;

    public Magazine(String title, int year, int pageCount, String issn, int issueNumber) {
        super(title, year, pageCount); 
        this.issn = issn;
        this.issueNumber = issueNumber;
    }

    @Override
    public String toString() {
        return super.toString() + ", ISSN: " + issn + ", Issue Number: " + issueNumber;
    }



}
