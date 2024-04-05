package efub.assignment.community.member.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.dto.MemberUpdateRequestDto;
import efub.assignment.community.member.dto.SignUpRequestDto;
import efub.assignment.community.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long signUp(SignUpRequestDto requestDto){
        if (exixtsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 email입니다." + requestDto.getEmail());
        }
        else if (existsByStudentId(requestDto.getStudentId())) {
            throw new IllegalArgumentException("이미 존재하는 studentId입니다." + requestDto.getStudentId());
        }
        Member member = memberRepository.save(requestDto.toEntity());
        return member.getMemberId();
    }

    @Transactional(readOnly = true)
    public boolean exixtsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean existsByStudentId(String studentId){
        return memberRepository.existsByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 Member를 찾을 수 없습니다.id="+id));
    }

    public Long update(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = findMemberById(memberId);
        member.updateMember(requestDto.getNickname());
        return member.getMemberId();
    }

//    @Transactional
//    public void withdraw(Long accountId) {
//        Account account = findAccountById(accountId);
//        account.withdrawAccount();
//    }
//
//    public void delete(Long accountId) {
//        Account account = findAccountById(accountId);
//        accountRepository.delete(account);
//    }


}
