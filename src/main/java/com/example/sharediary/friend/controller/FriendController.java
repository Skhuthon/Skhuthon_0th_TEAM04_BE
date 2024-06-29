package com.example.sharediary.friend.controller;

import com.example.sharediary.friend.dto.reqeust.FriendRequestDto;
import com.example.sharediary.friend.dto.response.FriendResponseDto;
import com.example.sharediary.friend.service.FriendService;
import com.example.sharediary.member.dto.request.LoginMemberResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/v1/friends")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    // 친구 요청
    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestBody FriendRequestDto friendRequestDto, LoginMemberResponseDto loginMember) {

        friendService.senderFriendRequest(friendRequestDto, loginMember);
        return ResponseEntity.ok("친구 요청 발송");
    }

    // 친구 요청 수락
    @PostMapping("/accept")
    public ResponseEntity<String> acceptFriendRequest(@RequestBody FriendRequestDto friendRequestDto) {
        friendService.acceptFriendRequest(friendRequestDto);
        return ResponseEntity.ok("친구 요청 수락 성공, 친구 맺음!");
    }

    // 친구 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<FriendResponseDto>> getAcceptFriend(@RequestParam String memberId) {
        List<FriendResponseDto>acceptedFriends = friendService.getAcceptFriends(memberId);
        return ResponseEntity.ok(acceptedFriends);
    }

    // 친구 요청 대기 목록 조회
    @GetMapping("/request")
    public ResponseEntity<List<FriendResponseDto>> getPendingRequests(@RequestParam String receiverName) {
        List<FriendResponseDto> pendingRequests = friendService.getPendingRequests(receiverName);
        return ResponseEntity.ok(pendingRequests);
    }
}
