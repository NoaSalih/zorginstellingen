package zorginstellingen.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Patient extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
