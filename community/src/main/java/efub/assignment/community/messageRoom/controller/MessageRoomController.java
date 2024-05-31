package efub.assignment.community.messageRoom.controller;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageRoom.domain.MessageRoom;
<<<<<<< HEAD
=======
import efub.assignment.community.messageRoom.dto.MessageRoomListResponseDto;
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
import efub.assignment.community.messageRoom.dto.MessageRoomRequestDto;
import efub.assignment.community.messageRoom.dto.MessageRoomStatusResponseDto;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;

=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
@RestController
@RequestMapping("/messageRooms")
@RequiredArgsConstructor
public class MessageRoomController {
    private final MessageRoomService messageRoomService;
    private final MemberService memberService;
    private final PostService postService;

<<<<<<< HEAD
    @PostMapping("/{receiverId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageRoomStatusResponseDto addmessageRoom(@PathVariable final Long receiverId,
                                                   @RequestBody final MessageRoomRequestDto requestDto){
=======

    /* 메시지룸 생성 */
    @PostMapping("/{receiverId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageRoomStatusResponseDto addmessageRoom(@PathVariable final Long receiverId,
                                                       @RequestBody final MessageRoomRequestDto requestDto) {
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
        MessageRoom messageRoom = messageRoomService.add(receiverId, requestDto);
        Boolean ismessageRoom = messageRoomService.isMessageRoom(receiverId, requestDto.getSenderId());
        Member recevier = memberService.findMemberById(receiverId);
        Member sender = memberService.findMemberById(requestDto.getSenderId());
        return new MessageRoomStatusResponseDto(messageRoom, ismessageRoom, recevier, sender);
    }

<<<<<<< HEAD
    @GetMapping("/{receiverId}/{senderId}/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Object getmessageRoom(@PathVariable final Long receiverId,
                             @PathVariable final Long senderId,
                             @PathVariable final Long postId) {
        MessageRoom messageRoom = messageRoomService.findByReceiverIdAndSenderIdAndPostId(receiverId, senderId, postId);
        if (messageRoom != null) {
            return "messageRoomId: "+messageRoom.getMessageRoomId();
        } else {
            messageRoom = messageRoomService.findByReceiverIdAndSenderIdAndPostId(senderId, receiverId, postId);
            if (messageRoom != null){
                return "messageRoomId: "+messageRoom.getMessageRoomId();
=======
    /* 메시지룸 여부 조회 */
    @GetMapping("/{receiverId}/{senderId}/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Object getmessageRoom(@PathVariable final Long receiverId,
                                 @PathVariable final Long senderId,
                                 @PathVariable final Long postId) {
        MessageRoom messageRoom = messageRoomService.findByReceiverIdAndSenderIdAndPostId(receiverId, senderId, postId);
        if (messageRoom != null) {
            return "messageRoomId: " + messageRoom.getMessageRoomId();
        } else {
            messageRoom = messageRoomService.findByReceiverIdAndSenderIdAndPostId(senderId, receiverId, postId);
            if (messageRoom != null) {
                return "messageRoomId: " + messageRoom.getMessageRoomId();
>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
            } else {
                return new Long[0];
            }
        }
    }
<<<<<<< HEAD
}

//    @GetMapping("/{memberId}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public messageRoomListResponseDto getmessageRoomList(@PathVariable final Long memberId){
//        List<messageRoom> messageRoomList = messageRoomService.findAllByMemberId(memberId);
//        return messageRoomListResponseDto.of(messageRoomList);
//    }

=======

    /* 메시지룸 목록 조회 */
    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MessageRoomListResponseDto getMessageRoomList(@PathVariable Long memberId) {
        return messageRoomService.getMessageRoomList(memberId);
    }

    /* 메시지룸 삭제 */
    @DeleteMapping("/{messageRoomId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MessageRoomStatusResponseDto deleteMessageRoom(@PathVariable Long messageRoomId,
                                                          @RequestParam("memberId") Long memberId) {
        messageRoomService.deleteMessageRoom(messageRoomId, memberId);
        return new MessageRoomStatusResponseDto("메시지룸 삭제가 완료되었습니다.");
    }
}

>>>>>>> 93c92618c5b8794e53eba62e077404a3fb51181c
