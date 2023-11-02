package com.team_liquid.review_and_rating.data.repositories;

import com.team_liquid.review_and_rating.data.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, String> {
}
