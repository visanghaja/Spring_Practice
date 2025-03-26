package hello.hello_spring.domain;

import jakarta.persistence.*;

@Entity // jpa가 관리하는 entitiy가 됨
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 ID 직접 생성해줌
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
