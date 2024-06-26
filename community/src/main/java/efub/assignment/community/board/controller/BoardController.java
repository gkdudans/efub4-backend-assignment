package efub.assignment.community.board.controller;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardRequestDto;
import efub.assignment.community.board.dto.BoardResponseDto;
import efub.assignment.community.board.service.BoardService;
import efub.assignment.community.member.domain.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // json 형태로 객체 데이터 반환
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    /* 게시판 생성 */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoardResponseDto createBoard(@RequestBody @Valid final BoardRequestDto requestDto){
        Board board = boardService.createNewBoard(requestDto);
        return BoardResponseDto.from(board, board.getMember().getNickname());
    }

    /* 게시판 조회 */
    @GetMapping("/{boardId}")
    public BoardResponseDto getBoard(@PathVariable("boardId") Long boardId){
        Board board = boardService.findBoardById(boardId);
        return BoardResponseDto.from(board, board.getMember().getNickname());
    }

    /* 게시판 주인 수정 */
    @PutMapping("/{boardId}")
    public BoardResponseDto updateBoard(@PathVariable("boardId") Long id,
                                        @RequestBody @Valid final BoardRequestDto requestDto){
        Board board = boardService.updateBoard(id, requestDto);
        return BoardResponseDto.from(board, board.getMember().getNickname());
    }

    @DeleteMapping("/{boardId}")
    /* 게시판 삭제 */
    public String deleteBoard(@PathVariable Long boardId,
                              @RequestParam(name = "memberId") Long memberId){
        boardService.deleteBoard(boardId, memberId);
        return "게시판이 삭제되었습니다.";
    }

}
