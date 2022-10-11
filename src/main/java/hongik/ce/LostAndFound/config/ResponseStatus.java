package hongik.ce.LostAndFound.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {



    SUCCESS(true, 200, "Success"),


/*
    2000 USER ERROR
 */
    EMPTY_STUDENT_NUMBER(false, 2000, "학번을 입력해주세요"),
    EMPTY_STUDENT_NAME(false, 2001, "이름을 입력해주세요."),
    EMPTY_STUDENT_EMAIL(false, 2002, "E-Mail을 입력해주세요"),
    EMPTY_STUDENT_NICKNAME(false, 2003, "별명을 입력해주세요."),
    EMPTY_PASSWORD(false, 2004, "비밀번호를 입력해주세요."),




    NOT_EXIST_ACCOUNT(false, 2100, "등록되지 않은 계정입니다."),
    ALREADY_EXIST_ACCOUNT(false,2101,"이미 등록된 계정입니다."),
    INVALID_PASSWORD(false, 2102, "비밀번호가 다릅니다."),


/*
    3000 Lost ERROR
 */

/*
    5000 Server ERROR
 */
    DATABASE_ERROR(false,5000,"DATABASE 에러");

    private final boolean isSuccess;
    private final int code;
    private final String message;

}
