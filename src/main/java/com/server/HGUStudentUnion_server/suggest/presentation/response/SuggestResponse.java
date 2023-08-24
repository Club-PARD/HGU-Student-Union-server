package com.server.HGUStudentUnion_server.suggest.presentation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.HGUStudentUnion_server.suggest.domain.Suggest;
import com.server.HGUStudentUnion_server.suggest.domain.SuggestAppUser;
import com.server.HGUStudentUnion_server.suggest.domain.repository.SuggestAppUserRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class SuggestResponse {

    private Long id;
    private String writer; // writer
    private int recommendCnt;
    private Boolean hide;
    private Boolean access;
    private String title;
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    private String ansUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime ansTime;


    public static SuggestResponse of(Suggest suggest, int cnt){
        if(suggest.getAnsUser() != null){
            return new SuggestResponse(suggest.getId(),
                    suggest.getAppUser().getName(),
                    cnt,
                    suggest.getHide(),
                    suggest.getAccess(),
                    suggest.getTitle(),
                    suggest.getStatus(),
                    suggest.getCreatedAt(),
                    suggest.getAnsUser().getName(),
                    suggest.getAnsTime());
        } else {
            return new SuggestResponse(suggest.getId(),
                    suggest.getAppUser().getName(),
                    cnt,
                    suggest.getHide(),
                    suggest.getAccess(),
                    suggest.getTitle(),
                    suggest.getStatus(),
                    suggest.getCreatedAt(),
                    null,
                    null);
        }

    }
//
//    public static SuggestResponse ofNo(Suggest suggest) {
//        return new SuggestResponse(suggest.getId(),
//                suggest.getAppUser().getName(),
//                0,
//                suggest.getHide(),
//                suggest.getTitle(),
//                suggest.getStatus(),
//                suggest.getCreatedAt());
//    }
}
