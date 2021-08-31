package hodubackspace.highschooltime.api.repository;

import hodubackspace.highschooltime.api.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberRepositoryTest {
    @InjectMocks
    MemberRepository memberRepository;

    @Mock
    EntityManager em;


    @Test
    public void join() {

    }

}