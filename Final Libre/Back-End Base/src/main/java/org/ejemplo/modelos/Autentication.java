package org.ejemplo.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Autentication {
    @Id
    String user;
    String token;
    Date vencimiento;

    public Object getUser() {
        return null;
    }
}
