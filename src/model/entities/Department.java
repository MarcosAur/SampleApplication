package model.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author marcos
 */
public class Department implements Serializable{
    private String name;
    private Integer id;
    
    public Department(String name){
        this.name = name;
    }

    public Department(Integer id, String name) {
        this.name = name;
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Department{" + "name= " + name + ", id= " + id + '}';
    }
    
    
    
    
}
