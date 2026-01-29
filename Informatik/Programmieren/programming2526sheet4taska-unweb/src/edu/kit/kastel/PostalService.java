package edu.kit.kastel;

/**
 * This class contains all the postal services.
 * @author unweb
 */
public enum PostalService {
    /**
     * Standard Brief.
     */
    BRIEF("Brief", 0.70),
    /**
     * Registered mail dropped into mailbox.
     */
    EINWURFEINSCHREIBEN("Einwurf-Einschreiben", 1.20),
    /**
     * Standard registered mail.  
    */ 
    EINSCHREIBEN("Einschreiben", 2.00),
    /**
     * Small size package. 
    */ 
    PAKETS("PaketL", 7.00),
    /**
     * Medium size package.
    */ 
    PAKETM("PaketM", 6.00),
    /**
     * Large size package.
    */ 
    PAKETL("PaketS", 5.00);

    /**
     * Postal service.
     */
    private final String postalService;
    /**
     * The price of the chosen postal service.
     */
    private final double price;

    /**
     * Constructor of the class.
     * @param postalService postal service
     * @param price the price of the postal service
     */
    PostalService(String postalService, double price) {
        this.postalService = postalService;
        this.price = price;
    }

    /**
     * A getter of the postal service.
     * @return postal service
     */
    public String getPostalService() {
        return postalService;
    }

    /** 
     * A getter of the price of the chosen postal service.
     * @return the price of the chosen postal service
     */
    public double getPrice() {
        return price;
    }

    

}
