package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> map = new HashMap<>();

    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);

        log.info("save member {}" , member);
        map.put(member.getId(),member);

        return member;
    }

    public Member findById(Long id) {
        return map.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().filter(ele -> ele.getLoginId().equals(loginId)).findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(map.values());
    }

    public void clear() {
        map.clear();
    }
}
