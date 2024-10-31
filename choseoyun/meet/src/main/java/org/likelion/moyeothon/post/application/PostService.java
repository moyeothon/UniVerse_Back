package org.likelion.moyeothon.post.application;

import org.likelion.moyeothon.post.api.dto.request.PostSaveReqDto;
import org.likelion.moyeothon.post.api.dto.request.PostUpdateReqDto;
import org.likelion.moyeothon.post.api.dto.response.PostInfoResDto;
import org.likelion.moyeothon.post.api.dto.response.PostListResDto;
import org.likelion.moyeothon.post.domain.Post;
import org.likelion.moyeothon.post.domain.PostParticipant;
import org.likelion.moyeothon.post.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    //private final UserRepository userRepository; // 유저 정보를 관리하는 리포지토리
    private final PostParticipantRepository postParticipantRepository; // 참여자 관리 리포지토리

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postParticipantRepository  = postParticipantRepository ;
    }

    // 글 등록
    @Transactional
    public void postSave(PostSaveReqDto postSaveReqDto) {
        User user = memberRepository.findById(postSaveReqDto.userId()).orElseThrow(IllegalArgumentException::new);

        Post post = Post.builder()
            .title(postSaveReqDto.title())
            .contents(postSaveReqDto.contents())
            .user(user)
            .build();

    postRepository.save(post);
}

    //작성자에 따른 게시물들을 조회
    public PostListResDto postFindUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

    List<Post> posts = postRepository.findByUser(user);
    List<PostInfoResDto> postInfoResDtoList = posts.stream()
            .map(PostInfoResDto::from)
            .toList();

    return PostListResDto.from(postInfoResDtoList);
}

    // 모든 구인글 조회
    public List<PostInfoResDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostInfoResDto::from)
                .collect(Collectors.toList());
    }
    //구인글 상세 조회
    public PostInfoResDto findPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + postId));
        return PostInfoResDto.from(post);
    }

    //글 수정
    @Transactional
    public void postUpdate(Long postId, PostUpdateReqDto postUpdateReqDto) {
        Post post = postRepository.findById(postId).orElseThrow
            (() -> new IllegalArgumentException("등록된 게시글이 없습니다" + postId));
    post.update(postUpdateReqDto);
}

    //글 삭제
    @Transactional
    public void postDelete(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);

            postRepository.delete(post);
    }

    //구인글 참여
    public void joinPost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다: " + postId));
        User user = userRepository.findById(userId)
               .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다: " + userId));

        PostParticipant participant = new PostParticipant(post, user);
        postParticipantRepository.save(participant);
    }

    // 구인글 참여 취소
    public void cancelJoinPost(Long postId, Long userId) {
        PostParticipant participant = postParticipantRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 참여 기록이 존재하지 않습니다: postId=" + postId +", userId=" + userId));

        postParticipantRepository.delete(participant);
    }
}

