package itu.jca.eval.test.coworking;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;

@Getter
public abstract class ModelService {
    private final String prefix;
    private final String sequence; 
    public ModelService(String prefix, String sequence) {
        this.prefix = prefix;
        this.sequence = sequence;
    }
    @PersistenceContext
    protected EntityManager entityManager;
    
    public String generateId(){
        Object result = entityManager
            .createNativeQuery("SELECT NEXTVAL('" + getSequence() + "')")
            .getSingleResult();
        return getPrefix() + result.toString();
    }


}
