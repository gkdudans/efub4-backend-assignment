package efub.assignment.community.messageRoom.controller;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.MessageRoomRequestDto;
import efub.assignment.community.messageRoom.dto.MessageRoomStatusResponseDto;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import efub.assignment.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messageRooms")
@RequiredArgsConstructor
public class MessageRoomController {
    private final MessageRoomService messageRoomService;
    private final MemberService memberService;
    private final PostService postService;

    @PostMapping("/{receiverId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageRoomStatusResponseDto addmessageRoom(@PathVariable final Long receiverId,
                                                   @RequestBody final MessageRoomRequestDto requestDto){
        MessageRoom messageRoom = messageRoomService.add(receiverId, requestDto);
        Boolean ismessageRoom = messageRoomService.ismessageRoom(receiverId, requestDto.getSenderId());
        Member recevier = memberService.findMemberById(receiverId);
        Member sender = memberService.findMemberById(requestDto.getSenderId());
        return new MessageRoomStatusResponseDto(messageRoom, ismessageRoom, recevier, sender);
    }

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
            } else {
                return new Long[0];
            }
        }
    }
}

//    @GetMapping("/{memberId}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public messageRoomListResponseDto getmessageRoomList(@PathVariable final Long memberId){
//        List<messageRoom> messageRoomList = messageRoomService.findAllByMemberId(memberId);
//        return messageRoomListResponseDto.of(messageRoomList);
//    }

