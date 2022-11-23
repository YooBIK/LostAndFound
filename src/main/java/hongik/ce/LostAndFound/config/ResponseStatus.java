package hongik.ce.LostAndFound.config;

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
    EMPTY_USER_NAME(false, 2001, "이름을 입력해주세요."),
    EMPTY_USER_EMAIL(false, 2002, "E-Mail을 입력해주세요"),
    EMPTY_USER_NICKNAME(false, 2003, "별명을 입력해주세요."),
    EMPTY_PASSWORD(false, 2004, "비밀번호를 입력해주세요."),

    EMPTY_PHONE_NUMBER(false, 2005, "휴대 전화 번호를 입력해주세요."),


    NOT_EXIST_ACCOUNT(false, 2100, "등록되지 않은 계정입니다."),
    ALREADY_EXIST_ACCOUNT(false, 2101, "이미 등록된 계정입니다."),
    INVALID_PASSWORD(false, 2102, "비밀번호가 다릅니다."),


    /*
        3000 Lost & FOUND ERROR
    */
    EMPTY_TITLE(false, 3000, "제목을 입력하세요."),
    EMPTY_CONTENTS(false, 3001, "내용을 입력하세요."),
    EMPTY_CATEGORY(false, 3002, "물건 종류를 선택하세요."),
    EMPTY_USER_ID(false, 3003, "User ID를 입력하세요."),

    EMPTY_LOCATION(false, 3004, "위치를 입력하세요"),
    NOT_EXIST_LOST(false, 3100, "게시글이 존재하지 않습니다."),
    CANNOT_STORE_FILE(false,3101, "파일 저장에 실패했습니다."),

    FAIL(false,3102, "실패!"),
    /*
        5000 Server ERROR
    */
    DATABASE_ERROR(false, 5000, "DATABASE 에러");

    private final boolean isSuccess;
    private final int code;
    private final String message;

}
