package itu.jca.eval.test.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itu.jca.eval.test.coworking.models.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, String> {
} 