package hello.jdbc.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Member {

    private String member_id; //멤버 아이디
    private int money; //머니
}
