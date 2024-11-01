package org.likelion.moyeothon.post.api.dto;

import org.apache.coyote.Response;
import org.likelion.moyeothon.post.api.dto.request.PostSaveReqDto;
import org.likelion.moyeothon.post.api.dto.request.PostUpdateReqDto;
import org.likelion.moyeothon.post.api.dto.response.PostInfoResDto;
import org.likelion.moyeothon.post.api.dto.response.PostListResDto;
import org.likelion.moyeothon.post.application.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    //글 등록
    @PostMapping("/posts")
    public ResponseEntity<String> postSave(@RequestBody PostSaveReqDto postSaveReqDto) {
        postService.postSave(postSaveReqDto);
        return new ResponseEntity<>("게시글이 등록되었습니다.", HttpStatus.CREATED);
    }

    //작성자에 따른 게시글 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<PostListResDto>myPostFindAll(@PathVariable("memberId") Long memberId){
        PostListResDto postListResDto = postService.postFindMember(memberId);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostInfoResDto>> getAllPosts() {
        List<PostInfoResDto> posts = postService.findAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 특정 게시글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostInfoResDto> getPostById(@PathVariable Long postId) {
        PostInfoResDto post = postService.findPostById(postId);
        return ResponseEntity.ok(post);
    }

    //글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<String>postUpdate(@PathVariable("postId") Long postId,
                                            @RequestBody PostUpdateReqDto postUpdateReqDto){
        postService.postUpdate(postId, postUpdateReqDto);
        return new ResponseEntity<>("게시글이 수정되었습니다.",HttpStatus.OK);
    }

    //글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> postDelete (@PathVariable("postId") Long postId){
        postService.postDelete(postId);
        return new ResponseEntity<>("게시물이 삭제되었습니다.", HttpStatus.OK);
    }

    // 구인글 참여
    @PostMapping("/{postId}/join")
    public ResponseEntity<String> joinPost(@PathVariable Long postId, @RequestParam Long memberId) {
        postService.joinPost(postId, memberId);
        return ResponseEntity.ok("참여가 성공적으로 완료되었습니다.");
    }

    // 구인글 참여 취소
    @DeleteMapping("/{postId}/join/{memberId}")
    public ResponseEntity<String> cancelJoinPost(@PathVariable Long postId, @PathVariable Long memberId) {
        postService.cancelJoinPost(postId, memberId);
        return ResponseEntity.ok("참여가 성공적으로 취소되었습니다.");
    }


}
