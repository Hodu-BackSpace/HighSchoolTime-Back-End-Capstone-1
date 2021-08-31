package hodubackspace.highschooltime.api.repository;

import hodubackspace.highschooltime.api.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonFunctionRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testInit() {
        memberRepository.findOne(1l);

    }
}