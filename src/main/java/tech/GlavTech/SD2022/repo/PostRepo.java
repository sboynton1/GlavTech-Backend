package tech.GlavTech.SD2022.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.GlavTech.SD2022.model.post.Post;
import tech.GlavTech.SD2022.model.User;


import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
      List<Post> findPostsByUserID(long userID);
      Post findPostsByPostID(long postID);
      void deletePostByPostID(long postID);

}
