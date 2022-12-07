package com.example.demo.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    //원래는 실무에서 저장소 동시접근 오류때문에 HashMap이 아닌 ConcurrentHashMap을 사용해야한다고 한다. (예제니까 넘어감)
    private Map<Long, Member> store = new HashMap<>(); // 저장용 Map , 멤버아이디,멤버객체 를 저장함

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
