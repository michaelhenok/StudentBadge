package miu.cs544.badgemembershipsystem.repository;

import miu.cs544.badgemembershipsystem.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member,Long> {
}
