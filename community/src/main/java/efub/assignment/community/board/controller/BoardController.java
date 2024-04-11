package efub.assignment.community.board.controller;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardRequestDto;
import efub.assignment.community.board.dto.BoardResponseDto;
import efub.assignment.community.board.service.BoardService;
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
        return BoardResponseDto.from(board);
    }

    /* 게시판 조회 */
    @GetMapping("/{boardId}")
    public BoardResponseDto getBoard(@PathVariable Long boardId){
        Board board = boardService.findBoardById(boardId);
        return BoardResponseDto.from(board);
    }

}
