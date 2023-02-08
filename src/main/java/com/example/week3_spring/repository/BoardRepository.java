package com.example.week3_spring.repository;

import com.example.week3_spring.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
