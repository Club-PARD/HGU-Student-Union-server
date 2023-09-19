package com.server.HGUStudentUnion_server.common;

import com.server.HGUStudentUnion_server.auth.domain.LoginUser;
import com.server.HGUStudentUnion_server.auth.domain.logins.ManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.logins.NormalLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSUManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSuperManagerLogin;
import com.server.HGUStudentUnion_server.suggest.presentation.request.SuggestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
//
//    private final AppUserService appUserService;
//    private final AttachFile attachFile;
//    private final EventService eventService;
//    private final InquiryService inquiryService;
//    private final NoticeService noticeService;
//    private final ShareNoticeService shareNoticeService;
//    private final ShopService shopService;
//    private final SuggestService suggestService;




    @Value("${EncSecretKey}")
    private String secretKey;
//    private String secretKey = "12345678901234567890123456789012"; // 32자리 비밀키

    @Value("${EncIv}")
    private String iv;
//    private String iv = "abcdefghijklmnop"; // 16자리 iv
    @PostMapping("/encrypt")
    public ResponseEntity<Void> encryptTest(@RequestParam("string") String email){
        System.out.println(DataEnDecryption.encrypt(email, secretKey, iv ));
//        DataEnDecryption.encrypt(email);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/decrypt")
    public ResponseEntity<Void> decryptTest(@RequestParam("code") String code){
        System.out.println(DataEnDecryption.decrypt(code, secretKey, iv));
//        DataEnDecryption.decrypt(code, secretKey, iv);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/normal")
    @RequiredLogin
    public ResponseEntity<String> normalTest(@NormalLogin LoginUser loginNormal){
        return ResponseEntity.ok("This is normal Method"+loginNormal.getId());
    }
    @GetMapping("/manager")
    @RequiredManagerLogin
    public ResponseEntity<String> managerTest(@ManagerLogin LoginUser loginUser){
        return ResponseEntity.ok("This is manager Method"+ loginUser.getId());
    }
    @GetMapping("/sumanager")
    @RequiredSUManagerLogin
    public ResponseEntity<String> sumanagerTest(@ManagerLogin LoginUser loginUser){
        return ResponseEntity.ok("This is sumanager Method"+ loginUser.getId());
    }
    @GetMapping("/supermanager")
    @RequiredSuperManagerLogin
    public ResponseEntity<String> supermanagerTest(@ManagerLogin LoginUser loginUser){
        return ResponseEntity.ok("This is supermanager Method, id:"+ loginUser.getId());
    }

//    private final AppUserRepo appUserRepo;
//    private final EventRepo eventRepo;
//    private final ShopRepo shopRepo;
//    @GetMapping("/dummies1")
//    public ResponseEntity<String> dummiesFirst(){
//        // 1~7
//        shopRepo.save(Shop.builder().hide(false).name("[더미] 커피유야").category("카페").content("아메리카노 2000원").image("www.google.com").build());
//        shopRepo.save(Shop.builder().hide(true).name("[더미] 명성").category("음식점").content("제육덥밥 500원").image("www.google.com").build());
//        shopRepo.save(Shop.builder().hide(false).name("[더미] 인브리즈").category("카페").content("요거트 스무디 3000원").image("www.google.com").build());
//        shopRepo.save(Shop.builder().hide(false).name("[더미] 로아").category("카페").content("라떼 4000원").image("www.google.com").build());
//        shopRepo.save(Shop.builder().hide(false).name("[더미] 멋짐").category("헬스장").content("3개월 회원권 200000원").image("www.google.com").build());
//        shopRepo.save(Shop.builder().hide(false).name("[더미] 희우스").category("숙박").content("닭가슴살 100원").image("www.google.com").build());
//        shopRepo.save(Shop.builder().hide(false).name("[더미] 환여횟집").category("음식점").content("물회 18000원").image("www.google.com").build());
//
//        // 1~8
//        eventRepo.save(Event.builder().hide(false).title("[더미] 테스트 공지 A").start(LocalDateTime.now().minusDays(20L)).end(LocalDateTime.now().minusDays(10L)).build());
//        eventRepo.save(Event.builder().hide(true).title("[더미] 테스트 공지 B").start(LocalDateTime.now().minusDays(19L)).end(LocalDateTime.now().minusDays(9L)).build());
//        eventRepo.save(Event.builder().hide(false).title("[더미] 테스트 공지 C").start(LocalDateTime.now().minusDays(18L)).end(LocalDateTime.now().minusDays(8L)).build());
//        eventRepo.save(Event.builder().hide(true).title("[더미] 테스트 공지 D").start(LocalDateTime.now().minusDays(17L)).end(LocalDateTime.now().minusDays(7L)).build());
//        eventRepo.save(Event.builder().hide(false).title("[더미] 테스트 공지 E").start(LocalDateTime.now().minusDays(16L)).end(LocalDateTime.now().minusDays(6L)).build());
//        eventRepo.save(Event.builder().hide(true).title("[더미] 테스트 공지 F").start(LocalDateTime.now().minusDays(15L)).end(LocalDateTime.now().minusDays(5L)).build());
//        eventRepo.save(Event.builder().hide(false).title("[더미] 테스트 공지 G").start(LocalDateTime.now().minusDays(14L)).end(LocalDateTime.now().minusDays(4L)).build());
//        eventRepo.save(Event.builder().hide(true).title("[더미] 테스트 공지 H").start(LocalDateTime.now().minusDays(13L)).end(LocalDateTime.now().minusDays(3L)).build());
//
//        // 1~5
//        appUserRepo.save(AppUser.builder().auth(0).name("테스트 Banned").email("lukehongg0@handong.ac.kr").build());
//        appUserRepo.save(AppUser.builder().auth(1).name("테스트 Normal").email("lukehongg@handong.ac.kr").build());
//        appUserRepo.save(AppUser.builder().auth(2).name("테스트 Manager").email("lukehongg1@handong.ac.kr").build());
//        appUserRepo.save(AppUser.builder().auth(3).name("테스트 SUManager").email("lukehongg2@handong.ac.kr").build());
//        appUserRepo.save(AppUser.builder().auth(4).name("테스트 SuperManager").email("lukehongg3@handong.ac.kr").build());
//
//        return ResponseEntity.ok("Saved");
//    }

//    private final AttachFileRepo attachFileRepo;
//
//    @GetMapping("/dummies2")
//    public ResponseEntity<String> dummiesSecond(){
//
//        attachFileRepo.save(AttachFile.noticeFile("[더미] 기숙사신청서", "www.google.com", noticeService.find((1L))));
//        attachFileRepo.save(AttachFile.noticeFile("[더미] 장학금신청서", "www.google.com", noticeService.find((2L))));
//        attachFileRepo.save(AttachFile.noticeFile("[더미] 전공변경신청서", "www.google.com", noticeService.find((3L))));
//        attachFileRepo.save(AttachFile.noticeFile("[더미] RC변경신청서", "www.google.com", noticeService.find((3L))));
//        attachFileRepo.save(AttachFile.shareFile("[더미] 해커톤신청서", "www.google.com", shareNoticeService.find(1L)));
//        attachFileRepo.save(AttachFile.shareFile("[더미] 교환학생신청서", "www.google.com", shareNoticeService.find(2L)));
//        attachFileRepo.save(AttachFile.shareFile("[더미] 휴학신청서", "www.google.com", shareNoticeService.find(3L)));
//        attachFileRepo.save(AttachFile.shareFile("[더미] 복학신청서", "www.google.com", shareNoticeService.find(3L)));
//
//        return ResponseEntity.ok("Connected");
//    }

}
