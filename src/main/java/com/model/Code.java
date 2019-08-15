package com.model;

import com.util.IdCreator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Code", schema = "test_spring")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCode")
    private Long codeID;

    @Column(name = "email")
    private String email;

    @Column(name = "code")
    private String code;

    public Code() {
    }

    public Code(Long codeID, String code, Order order, String email) {
        this.codeID = codeID;
        this.email = email;
        this.code = code;
    }

    public Code(String code, Order order, String email) {
        this.codeID = IdCreator.idCreator();
        this.email = email;
        this.code = code;
    }

    public Long getCodeID() {
        return codeID;
    }

    public void setCodeID(Long codeID) {
        this.codeID = codeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Code)) return false;
        Code code1 = (Code) o;
        return Objects.equals(codeID, code1.codeID) &&
                Objects.equals(email, code1.email) &&
                Objects.equals(code, code1.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeID, email, code);
    }

    @Override
    public String toString() {
        return "Code{" +
                "codeID=" + codeID +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
