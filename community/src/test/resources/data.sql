/* data.sql */
CREATE TABLE IF NOT EXISTS member (
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(60) NOT NULL,
    encoded_password VARCHAR(255) NOT NULL,
    nickname VARCHAR(16) NOT NULL,
    university VARCHAR(100) NOT NULL,
    student_no VARCHAR(20) NOT NULL,
    status VARCHAR(255) NOT NULL
);

INSERT INTO member (email, encoded_password, nickname, university, student_no, status) VALUES
('test@ewhain.net', 'efub1234!!', 'fubi', '이화여자대학교', '1234567', 'REGISTERED');

CREATE TABLE IF NOT EXISTS board (
    board_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    board_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    notice VARCHAR(255),
    FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS post (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    anonymous BOOLEAN NOT NULL,
    content VARCHAR(255) NOT NULL
);

INSERT INTO board (member_id, board_name, description, notice) VALUES
(1, '게시판 이름', '테스트 데이터', '테스트 데이터입니다.');

INSERT INTO post (board_id, member_id, anonymous, content)
VALUES (1, 1, TRUE, '테스트 게시글입니다.');

