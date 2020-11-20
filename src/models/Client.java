package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="clients")
@NamedQueries({
    @NamedQuery(
            name = "getAllClients",
            query = "SELECT c FROM Client AS c ORDER BY c.id DESC"
            ),
    @NamedQuery(
            name = "getClientsCount",
            query = "SELECT COUNT(c) FROM Client AS c"
            ),
    @NamedQuery(
            name = "checkRegistedCode",
            query = "SELECT COUNT(c) FROM Client AS c WHERE c.code = :code"
            )
})
@Entity
public class Client {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code",nullable = false,unique = false)
    private String code;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "age",nullable = true)
    private Integer age;

    @Column(name = "delete_flag",nullable = false)
    private Integer delete_flag;



    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
