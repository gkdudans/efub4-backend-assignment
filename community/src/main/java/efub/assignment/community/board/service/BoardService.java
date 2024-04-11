package efub.assignment.community.board.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardRequestDto;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
