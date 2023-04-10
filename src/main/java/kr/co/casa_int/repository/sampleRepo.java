package kr.co.casa_int.repository;

import kr.co.casa_int.entity.sampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gyutae park
 * @since 2023-04-10
 */

@Repository
public interface sampleRepo extends JpaRepository<sampleEntity, String> {
    // 어떠한 쿼리도 사용하지 않는다.
}
