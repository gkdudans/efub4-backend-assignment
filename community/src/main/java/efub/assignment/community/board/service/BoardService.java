package efub.assignment.community.board.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardRequestDto;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.exception.CustomDeleteException;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberUpdateRequestDto;
import efub.assignment.community.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static efub.assignment.community.exception.ErrorCode.PERMISSION_REJECTED_USER;
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberService memberService;

    public Board createNewBoard(BoardRequestDto requestDto) {
        Member member = memberService.findMemberById(Long.parseLong(requestDto.getMemberId()));
        Board board = requestDto.toEntity(member);
        Board savedBoard = boardRepository.save(board);
        return savedBoard;
    }

    @Transactional(readOnly = true)
    public Board findBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId)
               .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Board를 찾을 수 없습니다.id="+boardId));
        return board;
    }

    public Long updateBoard(Long boardId, BoardRequestDto requestDto) {
        Board board = findBoardById(boardId);
        Member member = memberService.findMemberById(Long.parseLong(requestDto.getMemberId()));
        board.updateBoard(requestDto, member);
        return board.getBoardId();

    }

    public void deleteBoard(Long boardId, Long memberId) {
        Board board = findBoardById(boardId);
        if(!boardRepository.existsByBoardIdAndMember_MemberId(boardId, memberId)) {
            throw new CustomDeleteException(PERMISSION_REJECTED_USER);
        }
        boardRepository.delete(board);
    }
}
