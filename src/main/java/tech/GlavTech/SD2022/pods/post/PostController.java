package tech.GlavTech.SD2022.pods.post;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path="/api/post")
public class PostController {

    private PostService postService;

    @PostMapping(path = "/thread")
    public ResponseEntity<Object> postThread(@RequestBody ThreadRequest tr) {
        return postService.postThread(tr);
    }
}
