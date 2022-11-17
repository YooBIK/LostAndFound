package hongik.ce.LostAndFound.config;

import hongik.ce.LostAndFound.domain.entity.UploadFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static hongik.ce.LostAndFound.config.ResponseStatus.NOT_EXIST_ACCOUNT;

@Component
public class FileStore {

    // 루트 경로 불러오기
    private final String rootPath = System.getProperty("user.dir");
    // 프로젝트 루트 경로에 있는 files 디렉토리
    private final String fileDir = rootPath + "/img/";

    public String getFullPath(String filename) {
        System.out.println(fileDir + filename);
        return fileDir + filename;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        System.out.println(0);
        System.out.println(multipartFile.isEmpty());
        if(multipartFile.isEmpty()) {
            return null;
        }
        System.out.println(1);
        System.out.println(multipartFile.getOriginalFilename());
        String originalFilename = multipartFile.getOriginalFilename();
        // 작성자가 업로드한 파일명 -> 서버 내부에서 관리하는 파일명
        // ( 파일명을 중복되지 않게끔 UUID로 정하고 ".확장자"는 그대로 )
        System.out.println(2);
        System.out.println(UUID.randomUUID().toString() + "." + extractExt(originalFilename));
        String storeFilename = UUID.randomUUID().toString() + "." + extractExt(originalFilename);

        // 파일을 저장하는 부분 -> 파일경로 + storeFilename 에 저장
        System.out.println(3);
        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        System.out.println(4);
        return new UploadFile(originalFilename, storeFilename);

    }

    // 확장자 추출
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}