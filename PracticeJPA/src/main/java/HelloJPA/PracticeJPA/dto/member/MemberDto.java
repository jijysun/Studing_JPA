package HelloJPA.PracticeJPA.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String email;
    private String phone;
    private Boolean veritify;

}