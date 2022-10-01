package hello.jdbc.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class MemberRepositoryVoTest {

    MemberRepositoryV0 memberRepositoryV0 = new MemberRepositoryV0();

    /**
     * insertTest
     *
     * @throws Exception
     */
    @Test
    public void crud() throws Exception {
        Member member = new Member();
        member.setMember_id("kibong");
        member.setMoney(30000);
        memberRepositoryV0.save(member);
    }

    /**
     * Select 테스트
     * @throws Exception
     */
    @Test
    public void getById()throws Exception{
        Member findMember = new Member();
        findMember = memberRepositoryV0.getById("kibong");
        log.info("findMember={}", findMember);

    }

    /**
     * 업데이트 테스트
     * @throws Exception
     */
    @Test
    public void update()throws Exception{
        memberRepositoryV0.update(50000, "kibong");
    }

    /**
     * 삭제 테스트
     * @throws Exception
     */
    @Test
    public void delete()throws Exception{
        memberRepositoryV0.delete("kibong");
    }
}
