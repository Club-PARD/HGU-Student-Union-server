package com.server.HGUStudentUnion_server.common;

import com.server.HGUStudentUnion_server.appUser.application.AppUserService;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AppUserRepo;
import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.attachFile.domain.repository.AttachFileRepo;
import com.server.HGUStudentUnion_server.auth.domain.LoginManager;
import com.server.HGUStudentUnion_server.auth.domain.LoginNormal;
import com.server.HGUStudentUnion_server.auth.domain.logins.ManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.logins.NormalLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSUManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSuperManagerLogin;
import com.server.HGUStudentUnion_server.event.application.EventService;
import com.server.HGUStudentUnion_server.event.domain.Event;
import com.server.HGUStudentUnion_server.event.domain.repository.EventRepo;
import com.server.HGUStudentUnion_server.event.presentation.request.EventRequest;
import com.server.HGUStudentUnion_server.event.presentation.request.EventUpdateRequest;
import com.server.HGUStudentUnion_server.inquiry.application.InquiryService;
import com.server.HGUStudentUnion_server.notice.application.NoticeService;
import com.server.HGUStudentUnion_server.shareNotice.application.ShareNoticeService;
import com.server.HGUStudentUnion_server.shareNotice.domain.ShareNotice;
import com.server.HGUStudentUnion_server.shop.application.ShopService;
import com.server.HGUStudentUnion_server.shop.domain.Shop;
import com.server.HGUStudentUnion_server.shop.domain.repository.ShopRepo;
import com.server.HGUStudentUnion_server.shop.presentation.request.ShopRequest;
import com.server.HGUStudentUnion_server.suggest.application.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.util.List;

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



    @GetMapping("/normal")
    @RequiredLogin
    public ResponseEntity<String> normalTest(@NormalLogin LoginNormal loginNormal){
        return ResponseEntity.ok("This is normal Method"+loginNormal.getId());
    }
    @GetMapping("/manager")
    @RequiredManagerLogin
    public ResponseEntity<String> managerTest(@ManagerLogin LoginManager loginManager){
        return ResponseEntity.ok("This is manager Method"+loginManager.getId());
    }
    @GetMapping("/sumanager")
    @RequiredSUManagerLogin
    public ResponseEntity<String> sumanagerTest(@ManagerLogin LoginManager loginManager){
        return ResponseEntity.ok("This is sumanager Method"+loginManager.getId());
    }
    @GetMapping("/supermanager")
    @RequiredSuperManagerLogin
    public ResponseEntity<String> supermanagerTest(@ManagerLogin LoginManager loginManager){
        return ResponseEntity.ok("This is supermanager Method, id:"+loginManager.getId());
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
